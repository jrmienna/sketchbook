def bin_search(a, value):
    left = 0
    right = len(a) - 1
    while left <= right:
        mid = (left + right) >> 1
        if a[mid] < value:  # value not in left branch
            left = mid + 1
        elif value < a[mid]: # value not in right branch
            right = mid - 1
        else: # fount it
            return "Found it!" 
    return "Wasn't here"

a_sorted = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

print bin_search(a_sorted, 2)
print bin_search(a_sorted, 8)
print bin_search(a_sorted, 20)
