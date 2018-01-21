from sys import stdin
from heapq import heappush, heappop
Inf = float(1e3000)

def mst(n,mat):
    unseen=[1]*n
    count=1
    heaviest=0
    heappath=[]
    minpath=[Inf]*n
    for i in mat[0]:
        heappush(heappath,(mat[0][i],i))
        minpath[i] = mat[0][i]
    unseen[0]=0
    while count<n:
        m, ind=heappop(heappath)
        while not unseen[ind]:
            m, ind=heappop(heappath)
        unseen[ind]=0
        minpath[ind]=Inf
        heaviest=max(heaviest, m)
        for i in mat[ind]:
            if unseen[i]:
                if mat[ind][i]<minpath[i]:
                    minpath[i]=mat[ind][i]
                    heappush(heappath,(mat[ind][i],i))
        count+=1
    return heaviest

def main():
    lines = stdin.readlines()
    n = len(lines)
    neighbormatrix = [0] * n
    for i in xrange(n):
        neighbormatrix[i]={}
    node = 0
    for line in lines:
        s=line.split()
        s.reverse()
        for k in s:
            neighbor, edge = k.split(':')
            neighbor = int(neighbor)
            if neighbor<=node:  break;
            edge = int(edge)
            neighbormatrix[node][neighbor] = edge
            neighbormatrix[neighbor][node] = edge
        node += 1
    print mst(n,neighbormatrix)

main()

