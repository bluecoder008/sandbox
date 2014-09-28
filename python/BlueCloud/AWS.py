#!/usr/bin/env python 
import boto.ec2
import boto.ses
from ResourceManager import ResourceManager
from BaseUtil import msg
from BaseUtil import debug_msg
from BaseUtil import os_cmd
from BaseUtil import ssh_cmd
import BaseDatetime

class AWSResourceManager(ResourceManager) :

	def __init__(self, region='') :
		if region == '' :
			self.connection = boto.connect_ec2()	
		else:
			self.connection = boto.ec2.connect_to_region(region)	

	def get_instance_list(self, key_name=''):
		insts = self.connection.get_all_instances()
		instances = []
		for res in insts :
			inst = res.instances[0]
			if key_name == '' or inst.key_name == key_name:
        		    host = inst.public_dns_name
			    if not host  :
				host = inst.ip_address
			    if host :
			        instances.append(host)
		return instances

	def launch_instance(self, ec2_ami, ec2_group, ec2_key_pair, ec2_tag):
	
		"""
		launch EC2 instance with given AMI, 
		the security group want to assign for the new generated VM, 
		the key pair name,
		and the tag,
		returns the new instance. 
		"""
		msg("... creating EC2 instance ...")
	
		try:
			group= self.connection.get_all_security_groups(groupnames=[ec2_group])[0]
			msg( " Security Groups: %s " % group )
		except conn.ResponseError, e:
			if e.code == 'InvalidGroup.Notfound':			
				msg( 'Creating Security Group: %s ' % ec2_group )
				group = self.connection.create_security_group(ec2_group)
			else:
				raise
	
		try:
			key = self.connection.get_all_key_pairs(keynames=[ec2_key_pair])[0]
		except conn.ResponseError, e:
			if e.code == 'InvalidKeyPair.NotFound':
				key_dir = '~/.ssh'
				msg( ' Creating a new key pair and save it to %s' % key_dir )
				key = self.connection.create_key_pair(ec2_key_pair)
				key.save(key_dir)
			else:
				raise
	
		reservation = self.connection.run_instances(ec2_ami,
							key_name=ec2_key_pair,
							instance_type=ec2_instancetype, 
							security_groups=[group])
			
		instance = reservation.instances[0]
		conn.create_tags([instance.id], {"Name":ec2_tag})

		while instance.state  == u'pending':
			msg( "Instance state: %s" % instance.state )
			BaseDatetime.sleep(10)
			instance.update()

		msg( "Instance.ID: %s: " % instance.id )
		msg( "Instance state: %s " % instance.state )
		msg( "Public DNS: %s" % instance.public_dns_name )

		return instance

	def start_instance(self, instance_id):
		"""
		start EC2 instance with given ID 
		"""
		msg( "... starting EC2 instance " + instance_id + " ..." )
		self.connection.start_instances(instance_ids=[instance_id])
		return self.wait_for_instance(instance_id, "running")

	def stop_instance(self, instance_id):
		"""
		stop EC2 instance with given ID 
		"""
		msg( "... stopping EC2 instance " + instance_id + " ..." )
		self.connection.stop_instances(instance_ids=[instance_id])
		self.wait_for_instance(instance_id, "stopped")

	def wait_for_instance(self, instance_id, state):
		"""
		wait for EC2 instance to reach a state
		"""
		msg( "... waiting EC2 instance " + instance_id + " to be in " + state + " state ..." )
		reached = False
		instance = None
		while not reached:
			insts = self.connection.get_all_instances([instance_id])
			for res in insts :
				instance = res.instances[0]
				debug_msg( "... current state: " + instance.state )
				if instance.state == state :
					reached = True
					break
			if not reached:
				BaseDatetime.sleep(10)
		return instance

	def terminate_instance(self, instance_id):
		"""
		terminate EC2 instance with given ID
		"""
		msg( "... terminating EC2 instance ..." )
		self.connection.terminate_instances(instance_ids=[instance_id])

class AWSConnectionFactory :
	
	def __init__(self, type = "EC2", aws_key = None, aws_secret = None ):
		
		if ( aws_key == None and aws_secret != None or
		     aws_key != None and aws_secret == None )   :
			errorMsg( "Please specify both AWS Cient Key ID _and_ " +
                                  " AWS Client Secret at the same time.")
		
		if aws_key == None :
			pass
		else :
			self.aws_key = aws_key
			self.aws_secret = aws_secret
		self.type = type

class AWSSimpleEmailService :
	
	def __init__(self, region, aws_key, aws_secret ):
		self.region = region
		self.aws_key = aws_key
		self.aws_secret = aws_secret

	def connect(self) :
		
		self.conn = boto.ses.connect_to_region(
				self.region,
                                aws_access_key_id=self.aws_key,
                                aws_secret_access_key=self.aws_secret )

		return ( self.conn != None )

	def sendEmailMessage(self, sender, to, subject, msg) :
	
		self.conn.send_email( sender, subject, msg, [ to ] )
	
	def verifyEmailAddress(self, emailAddress ) :

		if self.conn == None :
			return False

		self.conn.verify_email_address ( emailAddress )
		return True

#
# Add sudoer user to a given AWS VM host
#
def add_sudoer( ssh_cmdStr, host, sudo_user, remove_user = False, root_services='ALL', pub_key = ''):

    (sc, out) = ssh_cmd( ssh_cmdStr, "id -u " + sudo_user );

    if sc == 0 :
       msg("Sudoer user '" + sudo_user + "' already installed.")
       if remove_user and sudo_user:
		msg("removing currently present user: '" + sudo_user + "'...")
		ssh_cmd( ssh_cmdStr, "userdel " + sudo_user, sudo=True )
		ssh_cmd( ssh_cmdStr, "rm -rf /home/" + sudo_user, sudo=True )
		ssh_cmd( ssh_cmdStr, "rm -rf /etc/sudoers.d/" + sudo_user, sudo=True )
       else :
		return True

    msg("Installing the '" + sudo_user + "' user on host '" + host + "' ..." )
    dotSSHDir = "/home/" + sudo_user + "/.ssh"

    ssh_cmd( ssh_cmdStr, "useradd " + sudo_user, sudo=True  )
    msg("\t==> user added" )

    ### Construct the sudoer filer
  
    sudoFile = "/tmp/" + sudo_user
    ssh_cmd( ssh_cmdStr, "cp /dev/null " + sudoFile, sudo=True  )
    ssh_cmd( ssh_cmdStr, "chmod 666 " + sudoFile, sudo=True )
    ssh_cmd( ssh_cmdStr, "\"printf 'Defaults: " + sudo_user + " !requiretty\n' >> " 
               + sudoFile + "\"", sudo = True )
    ssh_cmd( ssh_cmdStr, "\"printf '" + sudo_user  + " ALL=(ALL) NOPASSWD: ALL\n' >> "
               + sudoFile + "\"", sudo = True )
    ssh_cmd( ssh_cmdStr, "\"printf '" + sudo_user  + " ALL=(root) NOPASSWD: " 
               + root_services +  "\n' >> " + sudoFile + "\"", sudo = True )
    msg("\t==> sudoer file constructed" )

    ### Validate the sudoer file
    (s, o) = ssh_cmd( ssh_cmdStr, "visudo -c -f " + sudoFile, sudo=True)
    if s == 0 :
           ssh_cmd( ssh_cmdStr, "chmod 440 " + sudoFile, sudo=True )
	   ssh_cmd( ssh_cmdStr, "mv " + sudoFile + " /etc/sudoers.d", sudo=True )
    else :
           ssh_cmd( ssh_cmdStr, "rm -rf " +sudoFile, sudo=True )
	   return False
    msg("\t==> sudoer file '" + sudoFile + "' verified on syntax and generated at: /etc/sudoers.d directory" )

    ssh_cmd( ssh_cmdStr, "rm -rf " + sudo_user + ";ssh-keygen -t rsa -N '' -f " + sudo_user )
    ssh_cmd( ssh_cmdStr, "mkdir -p " + dotSSHDir, sudo = True )
    #ssh_cmd( ssh_cmdStr, "chown " + sudo_user + ":" + sudo_user + " " + sudo_user, sudo = True )
    #ssh_cmd( ssh_cmdStr, "chown " + sudo_user + ":" + sudo_user + " " + sudo_user + ".pub", sudo = True )
    ssh_cmd( ssh_cmdStr, "mv " + sudo_user + " " + dotSSHDir + "/id_rsa", sudo = True )
    ssh_cmd( ssh_cmdStr, "mv " + sudo_user + ".pub " + dotSSHDir + "/id_rsa.pub", sudo = True )
    ssh_cmd( ssh_cmdStr, "chown -R " + sudo_user + ":" + sudo_user + " " + dotSSHDir, sudo = True )
    ssh_cmd( ssh_cmdStr, "chmod 700 -R " + dotSSHDir, sudo = True )
    msg("\t==> RSA key pair generated for user '" + sudo_user + "'" )
    if not pub_key :
	(s, pub_key) = os_cmd("cat ~/.ssh/id_rsa.pub")
	if s != 0 :
		error("Error when reading ~/.ssh/id_rsa.pub - make sure the RSA public key is present?")

    authKeys = "/tmp/authorized_keys"
    ssh_cmd( ssh_cmdStr, "rm -rf " + authKeys, sudo = True )
    ssh_cmd( ssh_cmdStr, "touch " + authKeys, sudo = True )
    ssh_cmd( ssh_cmdStr, "\"bash -c 'echo \\\"" + pub_key + "\n\\\" >> " + authKeys + "'\"", sudo = True )
    ssh_cmd( ssh_cmdStr, "chmod 600 " + authKeys, sudo = True )
    ssh_cmd( ssh_cmdStr, "chown " + sudo_user + ":" + sudo_user + " " + authKeys, sudo = True )
    ssh_cmd( ssh_cmdStr, "mv " + authKeys + " " + dotSSHDir, sudo = True )
    msg("\t==> authorized_keys added to .ssh for user '" + sudo_user + "'" )
    msg("All done. Verifying new account: ")
    (s,o) = os_cmd( "ssh " + sudo_user + "@" + host + " -t 'sudo hostname'", output = True )
    return s == 0
