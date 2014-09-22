#!/usr/bin/python
from BaseUtil import msg
from BaseUtil import error
from BaseUtil import os_cmd
from AWS import add_sudoer

import sys
import logging

logging.basicConfig( level = logging.INFO )

(s,o) = os_cmd("checkport.py amazonhost 12345")

if s > 0 :
	msg("The amazon VM is not reachable, skipping this part of testing")
	sys.exit(0)

(s, pubKey) = os_cmd("cat ~/.ssh/id_rsa.pub")
pubKey.rstrip('\n')

#print "pubKey: " + pubKey
if s == 0:
    #addSudoer("54.80.11.60", "sudoer", "ALL", pubKey = pubKey )
    add_sudoer( 'ssh -i /Users/weilwu/ws/bluestorm_file-less/info/vault/TheGreatKeyPair.pem.unlocked ec2-user@amazonhost',
               "amazonhost", "sudoer",
               remove_user = True,
               #removeCurrentUser = False,
               root_services = "ALL", pub_key = pubKey )
