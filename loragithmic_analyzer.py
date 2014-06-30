from math import log
import sys
import re

data = open('/home/oleksandr/data.txt').readlines()


# data = open(sys.argv[1]).readlines()

def to_tuple(item):
    if len(item) == 2 and float(item[1]) > 0:
        return int(item[0]),\
               float(item[1])
    else:
        return ()

data = dict(filter(lambda x: len(x) == 2, 
            map(lambda s: to_tuple(re.split('\s+', s.strip())), data)))

print data

data = data.values()

i = 0
while i < len(data)-2:
    if data[i] > 0:
        print log(data[i+1]/data[i], 2)
    i += 1

