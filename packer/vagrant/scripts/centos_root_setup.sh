#!/bin/bash
 
set -xe
 
# Updating and Upgrading dependencies
sudo yum update -y  > /dev/null
 
# Install necessary libraries for guest additions and Vagrant NFS Share
sudo yum install -y -q kernel-headers  dkms nfs-utils nfs-utils-lib

# Install necessary dependencies
sudo yum install -y -q install curl wget git tmux firefox xvfb vim
 
# Setup sudo to allow no-password sudo for "admin"
groupadd -r admin
usermod -a -G admin vagrant
cp /etc/sudoers /etc/sudoers.orig
sed -i -e '/Defaults\s\+env_reset/a Defaults\texempt_group=admin' /etc/sudoers
sed -i -e 's/%admin ALL=(ALL) ALL/%admin ALL=NOPASSWD:ALL/g' /etc/sudoers

#wget -c http://download.virtualbox.org/virtualbox/4.1.8/VBoxGuestAdditions_4.1.8.iso
sudo umount /mnt ||  true
#sudo mount ./VBoxGuestAdditions_4*.iso -o loop /mnt
#sudo sh /mnt/VBoxLinuxAdditions.run || true

