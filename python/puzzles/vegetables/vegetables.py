from sys import stdin


def vegetables(T, veg):

	magic_numb = 0
	c = 0
	cuts = 0
	break_ = 0

	for i in range(min(veg), 0, -1):
		for v in veg:
			c = v % i
			if not(i/(i+c) > T):
				break_ = 1
				break
		if break_ == 0:
			magic_numb = i
			break

	print 'mg' + str(magic_numb)

	
	# min_ = min(veg)
	# max_ = max(veg)

	# cuts = 0	

	# while (min_/max_ < T):
	# 	for v in veg:
	# 		cuts += (v - (v%magic_numb))/magic_numb

	return cuts




line_1 = stdin.readline().split()
veg = [int(x) for x in stdin.readline().split()]

print vegetables(line_1[0], veg)

