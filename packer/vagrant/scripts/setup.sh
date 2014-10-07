#!/bin/bash
 
set -e
 
#echo "Instaling for rof"
echo "Installing vagrant keys"
 
# Installing vagrant keys
mkdir -p ~/.ssh
chmod 700 ~/.ssh
#wget --no-check-certificate 'https://raw.github.com/mitchellh/vagrant/master/keys/vagrant.pub' -O authorized_keys
#wget --no-check-certificate 'https://199.27.79.133/mitchellh/vagrant/master/keys/vagrant.pub' -O authorized_keys
wget --no-check-certificate 'https://raw.githubusercontent.com/mitchellh/vagrant/master/keys/vagrant.pub' -O ~/.ssh/authorized_keys

chmod 600 ~/.ssh/authorized_keys
chown -R vagrant ~/.ssh

# Guard for empty authorized_keys file, wget with error creates an empty file
if [ ! -s ~/.ssh/authorized_keys ]; then
    echo "Unable to download Vagrant public key"
    exit 1
fi
 
# Node.js Setup
#wget --retry-connrefused -q -O - https://raw.github.com/creationix/nvm/master/install.sh | sh
#source ~/.nvm/nvm.sh
 
#nvm install 0.10.18
#nvm alias default 0.10.18
 
#echo "source ~/.nvm/nvm.sh" >> ~/.bash_profile
 
# RVM Install
#wget --retry-connrefused -q -O - https://get.rvm.io | bash -s stable
#source /home/vagrant/.rvm/scripts/rvm
 
#rvm autolibs read-fail
 
#rvm install 2.0.0-p195
 
#gem install bundler zeus


