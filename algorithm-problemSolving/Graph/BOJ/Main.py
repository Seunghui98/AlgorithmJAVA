def find(x):
    if x == prt[x]:
        return x
        
    v = find(prt[x])
    prt[x] = v
    return v

def union(x, y):
    if y >= m:
        return
        
    x = find(x)
    y = find(y)
    prt[x] = y

def upper_bound(x):
    s, e = 0, m
    while s < e:
        mid = (s+e)//2
        if nums[mid] > x:
            e = mid
        else:
            s = mid + 1

    return e

n, m, k = map(int, input().split())
nums = sorted(map(int,input().split()))
prt = [i for i in range(m)]
seq = list(map(int, input().split()))
for num in seq:
    idx = upper_bound(num)
    idx = find(idx)
    print(nums[idx])

    union(idx, idx+1)
