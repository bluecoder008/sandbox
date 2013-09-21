# Examine the following function descriptions and fill in the function
# definition for get_pool_by_ip.
# If desired, you may also supply a definition for one of get_pools_by_cidr or 
#
# Allowed Assumptions:
#  - No two pools have the same IP address
#  - Internal consistency (all uids map to valid references)
# Notes:
#  - All uids are non-zero positive integers
#  - IP addresses are strings in dotted decimal form, e.g.: '192.168.1.10'
#

#
# WWu -- helper function: Test if an ip address is in the subnet
# e.g. >>> print addressInNetwork('10.9.8.7', '10.9.1.0/16')
#      True
#
def addressInNetwork(ip, net):
    import socket,struct
    ipaddr = int(''.join([ '%02x' % int(x) for x in ip.split('.') ]), 16)
    netstr, bits = net.split('/')
    netaddr = int(''.join([ '%02x' % int(x) for x in netstr.split('.') ]), 16)
    mask = (0xffffffff << (32 - int(bits))) & 0xffffffff
    return (ipaddr & mask) == (netaddr & mask)

class Example(object):
    def get_all_pools(self):
        # List all pools.
        # Returns a list of pool uids, or [] if no pools
        # [
        #  <pool uid 1>,
        #  <pool uid 2>,
        #  ...
        # ]
        pass

    def get_pool(self, uid):
        # Get the contents of a specific pool by uid.
        # Returns a dict of pool attributes:
        # {
        #  'active': <integer enumerating state>,
        #  'datasource_id': <datasource uid>,
        #  'managed': <boolean indicating managed status>,
        #  'name': <string describing the pool>,
        #  'uid': <pool uid>
        # }
        pass

    def get_datasource(self, uid):
        # Get the contents of a specific datasource by uid.
        # Returns a dict of datasource attributes:
        # {
        #  'search_order': <integer>,
        #  'type': 'DS',
        #  'uid': <datasource uid>,
        #  'name': <string describing the datasource>,
        #  'properties': <property list (see below)>
        # }
        # Where property list is an *unordered list* of key-value pairs (not
        # a dictionary) as follows:
        # [
        #  ['master_address_1', <ip address as string>],
        #  ['password', <password as string>],
        #  ['username', <username as string>]
        # ]
        pass

    def get_pool_by_ip(self, ip):
        # Get the uid of the pool which has the given IP address
        # Returns the uid, or None if no such pool exists
        #pass

	# try using a 24-bit subnet mask
	subnetAddr = ip.split('.')[0] + ".0.0.0"
	pools_by_cidr = self.get_pools_by_cidr(subnetAddr, 24)
	if ip in pools_by_cidr:
		return pools_by_cidr[ip]
	return None

    def get_pools_by_cidr(self, ip, mask):
        # Get pools in the specified CIDR block (ip/mask), where mask is an int
        # Returns a dict of {<ip>: <uid>, ...}, {} if no pools match
        #
        # For example, passing ('10.0.0.0', 24) might return {'10.0.0.3': 9999}
        #pass

	networkMask = ip + "/" + str(mask)
	pool_map = {}

	pools = self.get_all_pools()
	for pool_id in pools:
		pool = self.get_pool(pool_id)	
		if 'datasource_id' not in pool:
			continue
		ds_id = pool['datasource_id']
		ds = self.get_datasource(ds_id)
		ip_address = ''
		for kv in ds[properties] :
			if kv[0] == 'master_address_1':
				ip_address = kv[1]
				break
		if ip_address == '':
			continue
		if addressInNetwork( ip_address, networkMask ):
			pool_map[ip_address] = pool_id
	return pool_map;	

    def get_pools_by_content(self, desc={}):
        # Get pools which match all of the fields described in dictionary desc
        # Allowed fields include any of the attributes of a pool (no need to
        # support datasource attributes).
        # Returns a list of uids, or [] if no pools match
        #
        # For example, passing {'managed': True, 'active': 3} might
        # return [10, 1234, 5555, 678], where each pool with that uid is
        # both managed and inactive.  Passing {} would return all pool uuids.
        pass

