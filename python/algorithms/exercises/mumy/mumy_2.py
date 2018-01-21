from sys import stdin

class Vertex():
    def __init__(self, number, prob):
        self.number = number
        self.prob = prob
        self.prev = None
        self.total = 0
        self.neighbors = []

def generate_neighbors(vertex, neighbor_list, prob):
    v = []
    n = len(neighbor_list)
    for i in xrange(n):
        if neighbor_list[i] and i is not vertex.number:
            v.append(Vertex(i, prob[i]))
    vertex.neighbors = v

def generate_vertecies(nm, prob):
    v = []
    n = len(nm)
    for i in xrange(n):
        v.append(Vertex(i, prob[i]))
    for i in xrange(n):
        for j in xrange(n):
            if nm[i][j]:
                v[i].neighbors.append(v[j])
    return v

def best_path(nm, prob, n):
    
    q = generate_vertecies(nm, prob)
    finished = []

    target = q[-1]
    start = q[0]
    start.total = prob[0]

    best_vertex = start
    while q:
        i = q.index(best_vertex)
        vertex = q.pop(i)
        finished.append(vertex)

        if vertex == target:
            break

        greatest_total_prob = -1.0

        for neighbor in vertex.neighbors:
            if not neighbor in finished:
                alt = vertex.total * neighbor.prob
                if alt > neighbor.total:
                    neighbor.total = alt
                    neighbor.prev = vertex
                if neighbor.total > greatest_total_prob:
                    best_vertex = neighbor
                    greatest_total_prob = neighbor.total

    if target.total == 0.0:
        return '0'

    path = []
    current = target
    while current.prev:
        path.insert(0, current)
        current = current.prev
    path.insert(0, start)

    return '-'.join(str(x.number) for x in path)

n = int(stdin.readline())
prob = [float(x) for x in stdin.readline().split()]
nm = []
for line in stdin:
    neighbor_row = [0] * n
    neighbors = [int(nabo) for nabo in line.split()]
    for neighbor in neighbors:
        neighbor_row[neighbor] = 1
    nm.append(neighbor_row)

print best_path(nm, prob, n)
