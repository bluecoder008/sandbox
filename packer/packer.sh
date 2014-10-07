#!/bin/bash -eu

if [ $# -lt 1 ]; then
    printf "\n$0 %s \n\n\t%s\t%s\n\t\t%s%s\n\n" \
           "<vm-type> [<provider>=aws]" \
           "where" "<vm-type>: [ami|ubuntu|centos]" "<provider>:" "[aws|vagrant]"
    exit -1
fi

mkdir -p tmp

#export PACKER_LOG=1
VM_TYPE=$1
BS_HOME=`cd ../../; pwd`

PROVIDER=aws
if [ $# -eq 2 ]; then
	PROVIDER=$2
fi

SVN_PASS=`grep svn_password $HOME/.boto | awk '{ print $3 } '`
CMD=$(echo sed -e 's#\{\{ThePassword\}\}#'$SVN_PASS'#' ./bbs-box-prov.shf '>' tmp/bbs_box_prov.sh )
bash -c "$CMD"

ENV_DIR="$BS_HOME/_env"
TARGET_DIR="$PROVIDER"
CMD=$(echo cat $ENV_DIR/bootstrap/bootstrap_pkg.sh tmp/bbs_box_prov.sh  '>' $TARGET_DIR/packer_prov_$PROVIDER.sh)
bash -c "$CMD"

if [ "$PROVIDER" = "aws" ]; then

    AWS_ACCESS_KEY_ID=`grep aws_access_key_id $HOME/.boto | awk '{ print $3 } '`
    AWS_SECRET_ACCESS_KEY=`grep aws_secret_access_key $HOME/.boto | awk '{ print $3 } '`

    if [ "$VM_TYPE" != "ami" -a "$VM_TYPE" != "ubuntu" ]; then
	printf "Invalid vm-type \"$VM_TYPE\" specified.\n"
	exit -1
    fi

    NOW=$(date +"%Y-%m-%d-%H-%M-%S")
    CMD=$(echo cd $TARGET_DIR\; AWS_ACCESS_KEY_ID=$AWS_ACCESS_KEY_ID AWS_SECRET_ACCESS_KEY=$AWS_SECRET_ACCESS_KEY \
      packer build -var "build_time=$NOW" -var-file=var_aws_${VM_TYPE}.json packer-build-aws.json)
    #echo $CMD
    bash -c "$CMD"
    rm $TARGET_DIR/packer_prov_$PROVIDER.sh tmp/bbs_box_prov.sh
fi

if [ "$PROVIDER" = "vagrant" ]; then
   mkdir -p vagrant/packer
   rm -rf "VirtualBox VMs/packer-virtualbox-iso/packer-virtualbox-iso.vbox" || true
   rm -rf vagrant/output-virtualbox-iso ||   true
   rm -f vagrant/packer/packer_virtualbox_virtualbox.box || true
   if [ "$VM_TYPE" = "ubuntu" ]; then
		cd vagrant &&  packer build -only=virtualbox-iso -force packer_ubuntu.json
		#vagrant box remove vagrant_ubuntu || true
 	    vagrant box add -f vagrant_ubuntu packer_ubuntu_virtualbox.box
   elif [ "$VM_TYPE" = "centos" ]; then
		#cd vagrant && PACKER_LOG=1 packer --debug build -only=virtualbox-iso -force packer_centos.json
		cd vagrant && packer build -force -only=virtualbox-iso packer_centos.json
		#vagrant box remove vagrant_centos || true
   		vagrant box add -f vagrant_centos packer_centos_virtualbox.box
	fi
   cd -   
fi
