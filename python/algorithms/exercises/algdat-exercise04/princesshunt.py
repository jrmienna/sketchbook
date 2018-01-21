'''
----------------------------------
INPUT:
First line = V
Next V lines = neighbor matrix
Last 2 lines = # of the startnodes

6
011000
000100
000110
000001
010101
000100
1
2
----------------------------------
OUTPUT:
Density of subgraphs T=E/V**2 with 3 decimals places.
Subgraph consits of vertices that are out of reach from start_vertex.
'''
from sys import stdin, stderr 
import traceback
from collections import deque

def T(E, V):
    return float(E) / float(V**2)

def subgrafdensity(neighbor_matrix, start_vertex):
    n = len(neighbor_matrix)

    inside = [False] * n
    inside[start_vertex] = True
    q = deque()
    q.append(start_vertex)

    while q:
        edge_from = q.popleft()
        for edge_to in range(0, n):
            if neighbor_matrix[edge_from][edge_to] and not inside[edge_to]:
                inside[edge_to] = True
                q.append(edge_to)
    
    vertices = 0
    edges = 0
    for edge_from in range(0, n):
        if not inside[edge_from]:
            vertices += 1
            for edge_to in range(0, n):
                if neighbor_matrix[edge_from][edge_to] and not inside[edge_to]:
                    edges += 1
 
    if vertices == 0:
        return 0.0
    else:
        return float(edges) / float(vertices**2)

try:
    n = int(stdin.readline())
    neighbor_matrix = [None] * n #rows
    for i in range(0, n):
        neighbor_matrix[i] = [False] * n #columns
        line = stdin.readline()
        for j in range(0, n):
            neighbor_matrix[i][j] = (line[j] == '1')
    for line in stdin:
        start = int(line)
        print "%.3f" % (subgrafdensity(neighbor_matrix, start) + 1E-12)
except:
    traceback.print_exc(file=stderr)
