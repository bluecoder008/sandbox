import commands
import csv
import sys
import time
import struct
import httplib
import json
import logging
import os

import BaseDatetime
from BaseDatetime import sleep
from optparse import OptionParser
#
# The list of modules that are included by Python 2.7 library:
# http://docs.python.org/2/library/index.html
#
# e.g.
# re, os, datetime, urllib, socket

debug  = False
dryrun = False
LOGGER_NAME = "~~((~~ bluestorm ~~))~~"
basedatetime = BaseDatetime.BaseDatetime()

logging.basicConfig( level=logging.INFO,
                     format='%(asctime)s %(name)-12s %(levelname)-8s %(message)s',
                     datefmt='%Y-%m-%dT%H:%M:%S')

#
# Basic System Releated Utilities
#
def parse_cmd_opts( usage, cmd_opts_map, args_min=0 ):
        parser = OptionParser(usage)
        for option in cmd_opts_map:
                parser.add_option( option[0], option[1], dest=option[2], action=option[3],
                                   help=option[4], default=option[5] )
	(opts, args) = parser.parse_args()
	if hasattr(opts, "debug") :
		debug = opts.debug
	else :
		debug = False
	if debug :
		logging.getLogger(LOGGER_NAME).setLevel(logging.DEBUG)
	debug_msg( "options:\n" + str(opts) )
	if ( len(args) < args_min ) :
		print ""
		parser.print_help()
		print ""
		sys.exit(-1)
        return (opts, args, parser)

def debug_on():
	logging.getLogger(LOGGER_NAME).setLevel(logging.DEBUG)

def bytes(size):
        if re.match(r'.*MB$',size) or re.match(r'.*M$',size):
                size = size.replace("M","").replace("B","")
                return int(size) * 1024 * 1024
        if re.match(r'.*KB$',size) or re.match(r'.*K$',size):
                size = size.replace("K","").replace("B","")
                return int(size) * 1024
        if re.match(r'.*GB$',size) or re.match(r'.*G$',size):
                size = size.replace("G","").replace("B","")
                return int(size) * 1024 *1024 * 1024
        return int(size)

def os_cmd(cmd, output = False):
	if not dryrun:
		(s, o) = commands.getstatusoutput(cmd)
		o = o.rstrip('\r')
		if output:
			print "xxxx cmd = %s"      % cmd
			print "xxxx status = %d"   % s
			print "xxxx output = '%s'" % o	
		return (s, o)
	else:
		return (0, "")

def ssh_cmd(sshStr, cmd, sudo=False):
        if sudo :
                sshStr = sshStr + " -t "
                cmd = "sudo " + cmd
	else :
		if '"' not in cmd:
			cmd = '"'+ cmd + '"'
        remoteCmd = sshStr + " " + cmd + " -o StrictHostKeyChecking=no ; echo $?"
        logging.debug( "xxxx remote command= %s " % remoteCmd )
        (s, o) = commands.getstatusoutput( remoteCmd )
	
        logging.debug( "xxxx remote status = %d " % s )
        logging.debug( "xxxx remote output = %s " % o )
        if s != 0 :
            logging.error("Error executing ssh command (" + cmd + "), sshStr (" + sshStr + ")")
        # s == 0
        lines = o.split("\n")
        out = "\n".join(lines[:-1])
	logging.debug("out: " + out)
        stat = int(lines[-1:][0])
        return (stat, out)
	
def is_port_num(str) :
	if not str.isdigit():
		return False
	if '.' in str :
		return False
	port = int(str)
	if port <= 0 :
		return False
	return True

def os_type():
	(s, o) = commands.getstatusoutput('uname -s')
	return o

def os_system(cmd):
	if debug:
		print basedatetime.now(None) + "executing cmd => '" + cmd + "'"
	return os_cmd(cmd)

def check_loadavg(percent) :
       load1 = os.getloadavg()[0]
       while load1 >= percent :
               sleep(10)
               load1 = os.getloadavg()[0]
#
# Use this pattern as:
#
# <code> 
#    Numbers = enum(ONE=1, TWO=2, THREE='three')
# </code>
#
def enum(**enums):
    return type('Enum', (), enums)

def msg(str):
	#print basedatetime.now(None) + msg
	logging.getLogger(LOGGER_NAME).info(str)
	#logging.getLogger(LOGGER_NAME).handlers[0].flush()

def debug_msg(str):
	#if debug:
		#msg(msg_str)
	logging.getLogger(LOGGER_NAME).debug(str)

def error(str):
	#msg("ERROR " + str)	
	logging.getLogger(LOGGER_NAME).error(str)
	sys.exit(-1)

def continue_yN(msg):
	ch = ' '
	while ch != 'Y' and ch != 'y' and ch != 'N' and ch != 'n':
		print "{} [Y|N]? ".format(msg)
		ch = sys.stdin.readline()[0]
	return ( ch == 'Y' or ch == 'y' )
