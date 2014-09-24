#one-off set up
vagrant box list | grep centos64
if [ $? != 0 ]; then
    vagrant box add centos64  https://github.com/2creatives/vagrant-centos/releases/download/v6.4.2/centos64-x86_64-20140116.box
fi

rm -rf Vagrantfile
vagrant init centos64  
vagrant up
vagrant ssh

