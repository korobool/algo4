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
    return id[p]

def union(p, q):
    pID = find(p)
    qID = find(q)

    if pID == qID:
        return

    for i in [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]:
        if id[i] == pID:
            id[i] = qID


from pprint import pprint

pprint(id.values())

operations = ((5,6),(1,8),(7,9),
              (0,3),(1,2),(6,2))

for operation in operations:
    union(operation[0], operation[1])

pprint(id.values())

