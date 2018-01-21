from sys import stdin, stderr

# kapasiteter er den opprinnelige kapasitetsmatrisen, som inneholder n x n elementer (hvor n er antall noder).
# startrom er en liste med indeksene til nodene som tilsvarer startrommene.
# utganger er en liste med indeksene til nodene som tilsvarer utgangene.

def numb_isolated_paths(capacities, start_vertecies, exits):
    '''
    Uses the method find_flowpath

    capacities = original capacity_matrix with n x n elements
    start_vertex = list of indexes to the vertecies that are start vertecies
    exits = list of indexes to the vertecies that are exits
    
    :return numb_paths number of isolated paths
    '''
    c = extend(capacities, start_vertecies, exits)
    n = len(c)
    f = []
    for i in xrange(n):
        f.append([0] * n)
    numb_paths = 0
    super_source = 0
    super_sink = n - 1
    while True:
        path = find_flowpath(super_source, super_sink, f, c)
        if not path:
            return '%d' % numb_paths
        m = len(path)
        for i in xrange(m-1):
            f[path[i]][path[i+1]] += 1
            f[path[i+1]][path[i]] -= 1
        numb_paths += 1
        if numb_paths == len(start_vertecies):
            return '%d' % numb_paths
    
def extend(c, start_vertecies, exits):
    n = len(c)
    new_n = 2 * n + 2
    new_c = []

    for i in xrange(new_n):
        new_c.append([0] * new_n)

    for i in start_vertecies:
        new_c[0][i*2+1] = 1

    for i in xrange(n):
        for j in xrange(n):
            new_c[2*i+2][2*j+1] = c[i][j]

    for i in xrange(n):
        new_c[2*i+1][2*i+2] = 1

    for i in exits:
        new_c[2*i+2][new_n-1] = 1

    return new_c

# Find a path from vertex with index 'source' to vertex with index 'sink'
# with free capasity in a flow network with flow f and capasity c.
# Returns a list where the first element is the index of one of the start
# vertecies, last element is index to one of the exits, and the elements in
# between are indexes of the other nodes in the path that was found, in right
# order. Example: a path from start vertex 4 to vertex 3, vertex 9, vertex 7
# and at last the exit 12 will be represented as [4,3,9,7,12]
def find_flowpath(source, sink, f, c):
    n = len(f)
    discovered = [False] * n
    parents = [None] * len(f)
    q = [source]
    while q:
        vertex = q.pop(0)
        if vertex == sink:
            # We've found the sink, create array with appropriate vertecies
            path = []
            i = vertex
            while True:
                path.append(i)
                if i == source:
                    break
                i = parents[i]
            path.reverse()
            return path
        for neighbor in xrange(n):
            if not discovered[neighbor] and f[vertex][neighbor] < c[vertex][neighbor]:
                q.append(neighbor)
                discovered[neighbor] = True
                parents[neighbor] = vertex
    return None


vertex, _, _ = [int(x) for x in stdin.readline().split()]
start_vertecies = [int(x) for x in stdin.readline().split()]
exits = [int(x) for x in stdin.readline().split()]
neighbor_matrix = []
for line in stdin:
    neighbor_row = [int(neighbor) for neighbor in line.split()]
    neighbor_matrix.append(neighbor_row)
print numb_isolated_paths(neighbor_matrix, start_vertecies, exits)
