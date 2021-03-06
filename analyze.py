from math import log
import sys
import re


if len(sys.argv) != 2:
    print('Usage: Pass the two-columned comma-separated txt file to draw a graph.')
    exit()

data = open(sys.argv[1]).readlines()

def to_tuple(item):
    if len(item) == 2 and float(item[1]) > 0:
        return int(item[0]),\
               float(item[1])
    else:
        return ()

data = dict(filter(lambda x: len(x) == 2, 
            map(lambda s: to_tuple(re.split(' +', s.strip())), data)))

print data

def draw(data):
    import matplotlib.pyplot as plt
    plt.plot(data.keys(), data.values(), 'ro')

    # plt.ylabel('some numbers')

    plt.yscale('log', basex=2)
    plt.xscale('log', basex=2)

    plt.show()

draw(data)
