#!/usr/bin/python

import sys
import unittest
from AWS import AWSResourceManager
from BaseUtil import msg

class AWSResourceManager_test(unittest.TestCase):
	
    def setUp(self):
	self.mgr = AWSResourceManager('us-east-1') # east coast

    def tearDown(self):
        pass

    def test_get_instance_list(self):
	vm_list = self.mgr.get_instance_list()
	print "%d instances" % len(vm_list)
	for inst in vm_list :
		if inst:
			msg("\tinst: " + inst)
		else:
			msg("\tinst: <null>")

    def test_stop_instance(self):
	self.mgr.stop_instance("i-5b4ff1b5")
	pass

    def test_start_instance(self):
	self.mgr.start_instance("i-5b4ff1b5")
	pass

    def test_get_instance_list_key_name(self):
	vm_list = self.mgr.get_instance_list(key_name='bogus')
	assert len(vm_list) == 0

if __name__ == '__main__':
    sys.exit(unittest.main())


	
