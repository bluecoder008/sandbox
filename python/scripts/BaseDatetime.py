#!/usr/bin/env python
import time, datetime, calendar
import BaseUtil

class BaseDatetime:
    ### Base class for Datetime utilities 

	def __init__(self):
		pass

	def today(self):
        	return datetime.datetime.today().strftime("%m-%d-%y")

	def now(self, arg='epoch'):
		if arg == "epoch":
			return time.time()
		# taken: http://crazytechthoughts.blogspot.com/2012/02/get-current-utc-timestamp-in-python.html
		if arg == "epoch_utc" :
			return calendar.timegm(datetime.datetime.utcnow().utctimetuple()) 
        	if arg == "hour":
                	return int(time.strftime("%H", time.localtime()))
        	if arg == "minute":
                	return int(time.strftime("%M", time.localtime()))
        	if arg == "date":
                	return datetime.date.today().strftime("%m-%d-%y")
        	if arg == "year":
                	return datetime.date.today().strftime("%Y")
        	dt = datetime.datetime.now()
        	return self.today() + " " + dt.strftime("%H:%M:%S") + '.%03d' % (dt.microsecond/1000) + " "

	def time_str_to_seconds(self,tmStr):
		tmStr = tmStr.lower()
		find_index = tmStr.find("day")
		if find_index > -1 :
			return int(tmStr[0:find_index]) * 3600 * 24
		find_index = tmStr.find("hour")
		if find_index > -1 :
			return int(tmStr[0:find_index]) * 3600
		find_index = tmStr.find("month")
		if find_index > -1 :
			return int(tmStr[0:find_index]) * 30 * 3600 * 24
		find_index = tmStr.find("minute")
		if find_index > -1 :
			return int(tmStr[0:find_index]) * 60
		find_index = tmStr.find("second")
		if find_index > -1 :
			return int(tmStr[0:find_index])
		else :
			return int(tmStr)

	def seconds_to_hour(self,seconds) :
		SECONDS_PER_HOUR = 60 * 60  
		return str( "%.2f" % ( seconds/SECONDS_PER_HOUR) )

	def seconds_to_time_str(self,seconds) :
		SECONDS_PER_MONTH = 3600 * 24 * 30 
		if seconds >= SECONDS_PER_MONTH:
			if seconds == SECONDS_PER_MONTH:
				return "one month"
			return str( seconds/SECONDS_PER_MONTH ) + " months"
		SECONDS_PER_DAY   = 3600 * 24
		if seconds >= SECONDS_PER_DAY:
			if seconds == SECONDS_PER_DAY:
				return "one day"
			return str( seconds/SECONDS_PER_DAY) + " days"
		return str("%.2f" % seconds) + " seconds"

	def timeStr(self,arg):
		return time.strftime('%Y-%m-%d %H:%M:%S.%f', time.localtime(arg))

	def sleep(self, seconds):
		time.sleep(seconds)

	def sleepUntil(self,hour):
		current = now('hour')
		while current < hour: 
			# check every 10 minutes
			time.sleep( 600 )
			current = now('hour')

# global Datetime obj
DatetimeObj = BaseDatetime()

def now(arg):
	return DatetimeObj.now(arg)

def time_str_to_seconds(tmStr):
	return DatetimeObj.time_str_to_seconds(tmStr)

def secondToTimeStr(arg):
	return DatetimeObj.seconds_to_time_str(arg)

def secondsToHour(arg):
	return DatetimeObj.seconds_to_hour(arg)

def timeStr(arg):
	return DatetimeObj.timeStr(arg)

def sleepUntil(hour):
	return DatetimeObj.sleepUntil(hour)

def sleep(seconds):
	BaseUtil.msg("sleep %d seconds" % seconds)
	DatetimeObj.sleep(seconds)

