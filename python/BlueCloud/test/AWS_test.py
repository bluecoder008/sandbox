#!/usr/bin/python

# To enable debug => set debug=True in BaseUtil

import os
import sys
import BaseUtil

from BaseUtil import msg
from BaseUtil import error
from BaseUtil import os_cmd
from AWS import AWSResourceManager
from AWS import add_sudoer

BaseUtil.set_debug(True)

mgr = AWSResourceManager('us-east-1') 

instance = mgr.start_instance('i-ba73ff54')

ip_address = instance.ip_address

while True:

    (s,o) = os_cmd("checkport.py " + ip_address + " 12345")
    if s == 0 :
        break
    BaseUtil.sleep(20)

if s > 0 :
	msg("The amazon VM is not reachable - please make sure AWS instances are running and "
            + "'amazonhost' is configured properly.")
        msg("Skipping this part of testing")
	sys.exit(0)

(s, pubKey) = os_cmd("cat ~/.ssh/id_rsa.pub")
pubKey.rstrip('\n')

#print "pubKey: " + pubKey
if s == 0:
    #addSudoer("54.80.11.60", "sudoer", "ALL", pubKey = pubKey )
    add_sudoer( 'ssh -i ' + os.environ['BS_HOME'] + '/info/vault/TheGreatKeyPair_East.pem.unlocked ubuntu@amazonhost',
               "amazonhost", "sudoer",
               remove_user = True,
               root_services = "ALL",
               pub_key = pubKey )

mgr.stop_instance('i-ba73ff54')

