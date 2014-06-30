from math import log

data = open('data.txt').readlines()

def to_tuple(item):
    if len(item) == 2 and float(item[1]) > 0:
        return int(item[0]),\
               float(item[1])
    else:
        return ()

data = dict(filter(lambda x: len(x) == 2, 
            map(lambda s: to_tuple(s.strip().split(', ')), data)))

print data

import matplotlib.pyplot as plt
plt.plot(data.keys(), data.values(), 'ro')

plt.ylabel('some numbers')

plt.yscale('log', basex=2)
plt.xscale('log', basex=2)

plt.show()

