
PYTHON_PATH := .:../scripts

check :
	PYTHONPATH=$(PYTHON_PATH) test/AWS_test.py
	PYTHONPATH=$(PYTHON_PATH) test/AWSResourceManager_test.py

clean :
	@rm -rf *.pyc
