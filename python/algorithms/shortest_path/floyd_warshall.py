from copy import deepcopy

'''
Pseudokode:

Floyd-Warshall(W)
    n = W.rows
    D[0] = W
    for k = 1 to n:
        let D[k] = d_ij[k] be a new n x n matrix
        for i = 1 to n
            for j = 1 to n
                d_ij[k] = min(d_ij[k-1]
'''

# The Floyd-Warshall Algorithm, Distances Only 
def floyd_warshall(G):
    D = deepcopy(G) # No intermediates yet
    for k in G: # Look for shortcuts with k
        for u in G:
            for v in G:
                D[u][v] = min(D[u][v], D[u][k] + D[k][v])
                return D

# The Floyd-Warshall Algorithm
def floyd_warshall(G):
    D, P = deepcopy(G), {}
    for u in G:
        for v in G:
            if u == v or G[u][v] == inf:
                P[u,v] = None
            else:
                P[u,v] = u
    for k in G:
        for u in G:
            for v in G:
                shortcut = D[u][k] + D[k][v] 
                if shortcut < D[u][v]:
                    D[u][v] = shortcut 
                    P[u,v] = P[k,v]
    return D, P
