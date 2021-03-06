from pprint import pprint

__author__ = 'oleksandr'


class WeightedQuickUnion():
    def __init__(self, N):
        self.count = N
        self.ID = {}
        self.sz = {}
        for i in range(N):
            self.ID[i] = i
            self.sz[i] = 1

    def connected(self, p, q):
        return self.find(p) == self.find(q)

    def find(self, p):
        while p != self.ID[p]:
            p = self.ID[p]
        return p

    def union(self, p, q):
        i = self.find(p)
        j = self.find(q)

        if i == j:
            return

        if self.sz[i] < self.sz[j]:
            self.ID[i] = j
            self.sz[j] += self.sz[i]
        else:
            self.ID[j] = i
            self.sz[i] += self.sz[j]

        self.count -= 1

        pprint(self.ID.values())


wqi = WeightedQuickUnion(10)

operations = ((6,9), (6,8), (3,4), (2,6), (2,1), (0,5), (3,5), (9,3), (3,7))

for o in operations:
    wqi.union(o[0], o[1])


