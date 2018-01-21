from sys import stdin

class Vertex():
    '''
    p = probability to get to this node
    n = list of neighbors
    d = distance or rather total probability from start vertex to this
    previous = the vertex wich we previously came from in search
    '''
    def __init__(self, number, p):
        self.number = number
        self.p = p
        self.k = None
        self.n = []
        self.previous = None
        self.finished = False

def best_path(v):
    q = list(v)
    n = len(q)

    for vertex in q:
        vertex.k = None
        vertex.previous = None
        vertex.finished = False

    if n == 1: return '0'

    target = q[-1]
    start = q[0]
    start.k = start.p
    best_node = start
    while q:
        i = q.index(best_node)
        current = q.pop(i)
        current.finished = True
        
        if current == target: 
            break

        greatest_k = -1

        for neighbor in current.n:
            if not neighbor.finished:
                alt = current.k * neighbor.p
                if not neighbor.k or alt > current.k:
                    neighbor.k = alt
                    neighbor.previous = current
                if neighbor.k > greatest_k:
                    best_node = neighbor
                    greatest_k = neighbor.k

    
    if target.k == 0: return '0'
    
    best_path = [target.number]
    current = target
    print target.k
    while current.previous:
        current = current.previous
        best_path.insert(0, current.number)
    return '-'.join(str(x) for x in best_path)
    


n = int(stdin.readline())
probs = [float(x) for x in stdin.readline().split()]
vertecies = []
for i in xrange(n):
    vertecies.append(Vertex(i, probs[i]))
    i += 1

for i in xrange(n):
    neighbors_numb = [int(neighbor) for neighbor in stdin.readline().split()]
    for vertex in vertecies:
        if vertex.number in neighbors_numb:
            vertecies[i].n.append(vertex)

print best_path(vertecies)
