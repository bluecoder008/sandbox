
data = """
   "key1",1,2.3
   "key2",4,5.6
   "key4",2,1.2
"""

def sort_by_col(col):
    dlist = data.split()
    def key_lambda(str):
        slist=str.split(",")
        return slist[col]
    results = sorted(dlist,
                     key=key_lambda)
    return results

def test_driver(col):
    results = sort_by_col(col)
    print("col={},results={}".format(col,results))

test_driver(0)
test_driver(1)







