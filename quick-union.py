id = {0:0,
      1:1,
      2:2,
      3:3,
      4:4,
      5:5,
      6:6,
      7:7,
      8:8,
      9:9}

def find(p):
    while p != id[p]:
        p = id[p]
    return p

def union(p, q):
    i = find(p)
    j = find(q)

    if i == j:
        return

    id[i] = j

    pprint(((p, q), id.values()))


from pprint import pprint

# operations = ((4, 3), (3, 8), (6, 5), (9, 4), (2, 1),
#              (8, 9), (5, 0), (7, 2), (6, 1), (1, 0), (6, 7))

operations = ((2,5), (0,1), (2,0), (6,4), (4,9), (4,8), (6,3), (3,0), (2,7))

for operation in operations:
    union(operation[0], operation[1])



