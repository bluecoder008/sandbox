#!/usr/bin/env python

import os
import sys

from os import listdir
from os import path

def list_dir(dir_path):

	paths = []
	abs_dir_path = path.abspath(dir_path)

	for entry in listdir(abs_dir_path):
		entry_path = abs_dir_path + "/" + entry
		if os.access(entry_path, os.R_OK):
			if path.isdir(entry_path):
				paths.extend(list_dir(entry_path))
			if path.isfile(entry_path):
				paths.append(entry_path)
	return paths

print "\n".join(list_dir(sys.argv[1]))

