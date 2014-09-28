
PYTHON_PATH := .:../scripts

check_base_test :
	PYTHONPATH=$(PYTHON_PATH) test/base_test.py

check_aws_test :
	PYTHONPATH=$(PYTHON_PATH) test/AWS_test.py

check_resource_mgr_test :
	PYTHONPATH=$(PYTHON_PATH) test/AWSResourceManager_test.py

check : check_base_test check_aws_test check_resource_mgr_test

clean :
	@rm -rf *.pyc
