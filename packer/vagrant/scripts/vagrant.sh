#!/usr/bin/env bash

mkdir /tmp/virtualbox
VERSION=$(cat /home/vagrant/.vbox_version)
mount -o loop /home/vagrant/VBoxGuestAdditions_$VERSION.iso /tmp/virtualbox
sh /tmp/virtualbox/VBoxLinuxAdditions.run
umount /tmp/virtualbox
rmdir /tmp/virtualbox
rm /home/vagrant/*.iso

#!/usr/bin/env bash

if type apt-get >/dev/null 2>&1; then
    apt-get clean
fi

if type yum >/dev/null 2>&1; then
    yum clean all
fi

dd if=/dev/zero of=/junk bs=1M
rm -f /junk

sync

