__author__ = 'ilya'


try:
    raise ValueError("this is exaption")
except:
    print "Was chatched"
else:
    print "in any cause with out ex"
finally:
    print "in any cause"
