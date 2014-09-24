#!/bin/bash -xe
OS=`uname -s`
printf "OS: %s" $OS

if [ -f /etc/lsb-release ]; then
    sudo add-apt-repository "deb http://archive.ubuntu.com/ubuntu $(lsb_release -sc) universe"
    sudo apt-get install -f
    sudo dpkg --configure -a
    sudo apt-get clean
    sudo apt-get update
    sleep 25
    sudo apt-get install -y -q libboost-all-dev
    sudo apt-get install -y libcppunit-dev
    sudo apt-get install -y ant
    sudo apt-get install -y subversion
    sudo apt-get install -y valgrind
    sudo apt-get install -y make
    sudo apt-get install -y openjdk-7-jdk
    sudo apt-get install -y libssl-dev
    # hard coded to g++-4.6
    sudo apt-get remove gcc -y
    sudo apt-get install gcc-4.6 g++-4.6 -y
    sudo rm -f /usr/bin/gcc /usr/bin/g++
    sudo ln -s /usr/bin/gcc-4.6 /usr/bin/gcc
    sudo ln -s /usr/bin/g++-4.6 /usr/bin/g++
    export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64/
    RC_LOCAL=/etc/rc.local
    sudo sed -i -e 's#exit 0##' $RC_LOCAL
 
    # get latest go version (current 1.2)
    sudo apt-get install -y golang bzr git-core
    export GOPATH=/tmp/godeb
    mkdir -p $GOPATH
    go get launchpad.net/godeb
    $GOPATH/bin/godeb --help
    sudo apt-get remove golang -y
    sudo apt-get autoremove -y
    $GOPATH/bin/godeb install
    sudo touch /etc/GraceIloveYa
    printf "Linux packages bootstrap done.\n"
elif [ "$OS" = "Darwin" ]; then
    ruby -e "$(curl -fsSL https://raw.github.com/mxcl/homebrew/go)"
    brew install boost
    brew install cppunit
    brew install gmp
    brew install mpfr
    brew install valgrind
    brew install maven
    brew install wget

    wget https://storage.googleapis.com/golang/go1.3.darwin-amd64-osx10.8.pkg -O /tmp/go1.3.darwin-amd64-osx10.8.pkg
    sudo  installer -pkg /tmp/go1.3.darwin-amd64-osx10.8.pkg -target / 
    rm /tmp/go1.3.darwin-amd64-osx10.8.pkg

    sudo easy_install pip
    sudo pip install boto
    python -c "import boto; print boto.Version"
    printf "Mac OS-X packages bootstrap done.\n"
elif [ -f /etc/centos-release ]; then
    sudo yum -y install gcc gcc-c++ boost-devel telnet golang openssl-devel
    sudo yum -y install libboost-all-dev
    sudo yum -y install libcppunit-dev
    sudo yum -y install ant
    sudo yum -y install subversion
    sudo yum -y install valgrind
    cd /tmp && wget http://sourceforge.net/projects/cppunit/files/cppunit/1.12.0/cppunit-1.12.0.tar.gz && tar -xvf cppunit-1.12.0.tar.gz \
      && cd cppunit-1.12.0 && ./configure LDFLAGS='-ldl' && make && sudo make install
    export JAVA_HOME=`find /usr/lib/jvm -name "java-1.7.0-openjdk*" \! -type l`
    RC_LOCAL=/etc/rc.d/rc.local
    printf "Linux packages bootstrap done.\n"
else # default case?
    printf "No known OS type found.\n"
fi
