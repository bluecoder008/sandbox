#!/usr/bin/python

import sys
import unittest
from AWS import AWSResourceManager

class AWSResourceManager_test(unittest.TestCase):
	
    def setUp(self):
	self.mgr = AWSResourceManager('us-west-2') #oregon

    def tearDown(self):
        pass

    def test_get_instance_list(self):
	vm_list = self.mgr.get_instance_list()
	print "%d instances" % len(vm_list)
	for inst in vm_list :
		print "\tvm inst: " + inst	

    def test_get_instance_list_key_name(self):
	vm_list = self.mgr.get_instance_list(key_name='bogus')
	assert len(vm_list) == 0

if __name__ == '__main__':
    sys.exit(unittest.main())


	
