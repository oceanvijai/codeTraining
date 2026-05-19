# Binary Search — Complete Interview Guide (Java)

---

## Part 1 — Core Intuitions for Implementation

---

### 1a. Correct `mid` Calculation

Always use the overflow-safe formula. Here is a full evaluation of every variant:

| Formula | Correct? | Overflow Safe? | Verdict |
|---|---|---|---|
| `(left + right) / 2` | ✅ | ❌ Overflows when left + right > Integer.MAX_VALUE | Avoid in production |
| `left + (right - left) / 2` | ✅ | ✅ Subtraction stays small | ✅ **Use this always** |
| `(right - left) / 2` | ❌ | ✅ | Never use — gives offset not index |
| `(left + right) >> 1` | ✅ | ❌ Same overflow risk as option 1 | Same risk as option 1 |
| `(left + right) >>> 1` | ✅ | ✅ Unsigned shift avoids sign-bit issue | Valid alternative (used in Java's Arrays.sort) |

**Why `(right - left) / 2` is wrong:**
```
left = 3, right = 7
Wrong:   (7 - 3) / 2 = 2        ← just an offset, not an index
Correct: 3 + (7 - 3) / 2 = 5    ← actual middle index
```

**Why overflow matters:**
```
left = 1_000_000_000, right = 1_500_000_000
left + right = 2_500_000_000  →  overflows int  →  becomes negative  →  ArrayIndexOutOfBoundsException
```

**The safe standard:**
```java
int mid = left + (right - left) / 2;
```

---

### 1b. The Two Core Intuitions (from research notes)

These two intuitions explain every binary search decision you'll ever make.

#### Intuition 1 — Never Exclude the Answer

> When you eliminate the left or right side of the array, make sure the answer is not part of what you threw away.

- When you write `right = mid - 1`, you are saying: "mid is definitely NOT the answer."
  - This is only safe when you have already confirmed (`nums[mid] == target`) or you know mid cannot be the boundary.
- When you write `right = mid`, you are saying: "mid might still be the answer, keep it."
  - This pairs with `while(low < high)` so the loop still makes progress.
- Save the answer in a variable (`ans = mid`) before shrinking the window — this is how you avoid losing it.

```
If eliminating right half:   right = mid - 1   →  mid is ruled out
                             right = mid        →  mid is still a candidate
```

#### Intuition 2 — Know Where the Loop Exits

> After the loop ends, think about where each pointer will be sitting. Pick the pointer that lands on the correct side.

There are two exit scenarios depending on your loop condition:

**Case A — `while(low <= high)` with `mid ± 1`:**
- Pointers **cross** each other on exit: `...high | low...`
- Last valid state: `low == high`
- Final move: either `high = mid - 1` (high moves left) or `low = mid + 1` (low moves right)
- On exit: `low` is one past `high`
- ✅ Return `low` for first element `>= target` (lower bound)
- ✅ Return `high` for last element `<= target` (upper bound)

**Case B — `while(low < high)` with `high = mid`:**
- Pointers **converge** (meet at same index) on exit: `low == high`
- Last valid state: `low = high - 1`, mid = low (integer division pulls toward low)
- Either `high = mid = low` (they meet) or `low = mid + 1 = high` (they meet)
- ✅ Return `low` or `high` — they are identical

**The Golden Rule:**

| Loop Condition | high Movement | Return |
|---|---|---|
| `low <= high` | `mid - 1` | `low` (lower bound) or `high` (upper bound) |
| `low < high` | `mid` | `low` or `high` (same value at exit) |

> **Never mix** `while(low < high)` with `high = mid - 1` — this creates infinite loops or skips the answer entirely.

---

### 1c. Which Pointer to Return and When

**Scenario: Pointers cross (`while low <= high`, `mid ± 1`)**

```
Index:  0   1   2   3   4
        1   3   5   7   9      target = 6

Step 1: low=0, high=4, mid=2 → arr[2]=5 < 6  → low = 3
Step 2: low=3, high=4, mid=3 → arr[3]=7 >= 6 → high = 2

Exit:   high=2, low=3   (crossed)
              ↑   ↑
            high  low  ← low points to first element >= target (lower bound)
                       ← high points to last element <= target (upper bound)
```

- Return `low` → lower bound (first position where element `>= target`)
- Return `high` → upper bound (last position where element `<= target`)

**Scenario: Pointers meet (`while low < high`, `high = mid`)**

```
Index:  0   1   2   3   4
        1   3   5   7   9      target = 6

Step 1: low=0, high=4, mid=2 → arr[2]=5 < 6  → low = 3
Step 2: low=3, high=4, mid=3 → arr[3]=7 >= 6 → high = 3

Exit:   low=3, high=3   (same spot)
              ↑
         both here  ← return either
```

- Return `low` or `high` (they are the same value)

**Quick Interview Cheat Sheet:**

```
high = mid - 1  →  must use  while(low <= high)  →  return low (or high)
high = mid      →  must use  while(low < high)   →  return low (or high, same)

Aggressive high (mid-1): high jumps over answer → trust low
Cautious high (mid):     both converge → return either
```

---

### 1d. Worst Case Scenarios in Binary Search

Binary search is `O(log n)` — but when does it take the *maximum* number of steps?

**Answer: When the element is at the boundaries (first or last index), or not present at all.**

**Example trace:** `nums = [1, 2, 3, 4, 5, 6, 7, 8]` (length 8, so worst case = log₂(8) = 3 steps)

| Target | Position | Steps Taken | Why |
|---|---|---|---|
| `4` or `5` | Middle | 1–2 steps | Hit on first or second comparison |
| `1` | Left boundary | 3 steps | Must halve three times to reach leftmost |
| `8` | Right boundary | 3 steps | Must halve three times to reach rightmost |
| `9` | Not present | 3 steps + exits | Exhausts all halvings before confirming absence |

**The intuition:**
```
Every iteration halves the search space:
n → n/2 → n/4 → ... → 1

Number of halvings = log₂(n) = worst case O(log n)
```

The **middle element is the best case O(1)** — it's hit on the very first comparison.
The **boundary elements and missing elements are worst case O(log n)** — they survive every round of halving.

> **Interview tip:** Always test your binary search implementation with: first element, last element, and an element not in the array. These are exactly where bugs surface (off-by-one in mid calculation, wrong pointer return, infinite loop).

---

## Part 2 — Standard Binary Search Templates

Based on this corrected foundation:

```java
public int search(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
        int mid = left + ((right - left) / 2);  // ✅ overflow-safe
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            right = mid - 1;                     // ✅ mid ruled out
        } else {
            left = mid + 1;
        }
    }
    return -1;
}
```

---

### Template A — Lower Bound (First occurrence / first index >= target)

```java
// Returns the first index where nums[index] >= target
// If all elements < target, returns nums.length
public int lowerBound(int[] nums, int target) {
    int low = 0, high = nums.length - 1, ans = nums.length;
    while (low <= high) {
        int mid = low + (high - low) / 2;
        if (nums[mid] >= target) {
            ans = mid;       // mid is a candidate, save it
            high = mid - 1;  // try to find an earlier one
        } else {
            low = mid + 1;
        }
    }
    return ans;
}
```

**Exit state:** `low` has crossed `high`. `ans` holds the leftmost valid index.
**Use when:** "Find first occurrence", "search insert position", "first element >= target"

---

### Template B — Upper Bound (Last occurrence / last index <= target)

```java
// Returns the last index where nums[index] <= target
// If all elements > target, returns -1
public int upperBound(int[] nums, int target) {
    int low = 0, high = nums.length - 1, ans = -1;
    while (low <= high) {
        int mid = low + (high - low) / 2;
        if (nums[mid] <= target) {
            ans = mid;       // mid is a candidate, save it
            low = mid + 1;   // try to find a later one
        } else {
            high = mid - 1;
        }
    }
    return ans;
}
```

**Exit state:** `low` has crossed `high`. `ans` holds the rightmost valid index.
**Use when:** "Find last occurrence", "last element <= target"

---

### Template C — Converging (while low < high, high = mid)

```java
// Returns the first index where condition is satisfied
// Loop exits when low == high (they meet at the answer)
public int convergingSearch(int[] nums, int target) {
    int low = 0, high = nums.length; // note: high = length, not length-1
    while (low < high) {
        int mid = low + (high - low) / 2;
        if (nums[mid] < target) {
            low = mid + 1;   // mid is ruled out
        } else {
            high = mid;      // mid is still a candidate, keep it
        }
    }
    return low; // low == high at exit, both point to answer
}
```

**Exit state:** `low == high`, both point to the answer.
**Use when:** Peak finding, rotated array minimum, any problem where `high = mid` is safer.

---

## Part 3 — The 10 Essential Binary Search Patterns for Interviews

---

### Pattern 1 — Lower / Upper Bound (Finding Boundaries)

**Problem:** [LC 34 — Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/) 🟡

**The trick:** Two separate binary searches — one lower bound (first occurrence), one upper bound (last occurrence).

```java
public int[] searchRange(int[] nums, int target) {
    return new int[]{lowerBound(nums, target), upperBound(nums, target)};
}

private int lowerBound(int[] nums, int target) {
    int lo = 0, hi = nums.length - 1, ans = -1;
    while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;
        if (nums[mid] >= target) { ans = mid; hi = mid - 1; }
        else lo = mid + 1;
    }
    return (ans != -1 && nums[ans] == target) ? ans : -1;
}

private int upperBound(int[] nums, int target) {
    int lo = 0, hi = nums.length - 1, ans = -1;
    while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;
        if (nums[mid] <= target) { ans = mid; lo = mid + 1; }
        else hi = mid - 1;
    }
    return (ans != -1 && nums[ans] == target) ? ans : -1;
}
```

**Time:** O(log n) | **Space:** O(1)

**Interview signal:** Anytime you see "first/last occurrence", "count occurrences", "search insert position" → this pattern.

---

### Pattern 2 — Search in Rotated Sorted Array

**Problem:** [LC 33 — Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/) 🟡

**The trick:** At every `mid`, one half is **always** fully sorted. Figure out which half is sorted, then check if the target lies inside it. Decide which half to discard based on that.

```java
public int search(int[] nums, int target) {
    int lo = 0, hi = nums.length - 1;
    while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;
        if (nums[mid] == target) return mid;

        if (nums[lo] <= nums[mid]) {          // left half is sorted
            if (nums[lo] <= target && target < nums[mid])
                hi = mid - 1;                 // target is in the sorted left half
            else
                lo = mid + 1;
        } else {                              // right half is sorted
            if (nums[mid] < target && target <= nums[hi])
                lo = mid + 1;                 // target is in the sorted right half
            else
                hi = mid - 1;
        }
    }
    return -1;
}
```

**Time:** O(log n) | **Space:** O(1)

**Interview signal:** "Rotated", "pivoted", "circular" sorted array → always identify which half is sorted first.

**Related:** LC 81 (with duplicates — worst case degrades to O(n) when `nums[lo] == nums[mid]`, shrink both pointers by 1)

---

### Pattern 3 — Peak Finding (No Sorted Guarantee)

**Problem:** [LC 162 — Find Peak Element](https://leetcode.com/problems/find-peak-element/) 🟡

**The trick:** The array doesn't need to be sorted. The monotonic property is: if `arr[mid] < arr[mid+1]`, a peak must exist to the right. Use `while(lo < high)` + `high = mid` to safely converge on a peak.

```java
public int findPeakElement(int[] nums) {
    int lo = 0, hi = nums.length - 1;
    while (lo < hi) {
        int mid = lo + (hi - lo) / 2;
        if (nums[mid] < nums[mid + 1])
            lo = mid + 1;   // slope goes up, peak is to the right
        else
            hi = mid;       // slope goes down, mid itself could be the peak
    }
    return lo;              // lo == hi at exit, both point to a peak
}
```

**Time:** O(log n) | **Space:** O(1)

**Interview signal:** "Find any peak", "local maximum/minimum" with O(log n) requirement → binary search on slope direction, not values.

---

### Pattern 4 — Binary Search on 2D Matrix

**Problem:** [LC 74 — Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/) 🟡

**The trick:** Treat the entire `m x n` matrix as a flattened sorted array of size `m*n`. Map the flat index `mid` back to row and column using `mid/n` and `mid%n`.

```java
public boolean searchMatrix(int[][] matrix, int target) {
    int m = matrix.length, n = matrix[0].length;
    int lo = 0, hi = m * n - 1;
    while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;
        int val = matrix[mid / n][mid % n];    // key index mapping
        if (val == target) return true;
        else if (val < target) lo = mid + 1;
        else hi = mid - 1;
    }
    return false;
}
```

**Time:** O(log(m*n)) | **Space:** O(1)

**Interview signal:** "Search in matrix", "sorted rows where first element of each row > last of previous" → flatten and binary search. If rows are only sorted independently (LC 240), use staircase search instead.

---

### Pattern 5 — Binary Search on Answer Space ⭐ (Most Important Pattern)

**Problem:** [LC 875 — Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/) 🟡

**The trick:** You are NOT searching an array — you are searching over all *possible answer values*. Write a `canDo(mid)` predicate that checks feasibility. Binary search the minimum (or maximum) valid answer.

The key insight: `canDo(x)` creates a boolean array `[F, F, F, T, T, T, T]`. You want the first `T`.

```java
// Template: find MINIMUM x such that canDo(x) is true
public int minEatingSpeed(int[] piles, int h) {
    int lo = 1;
    int hi = Arrays.stream(piles).max().getAsInt(); // max possible answer
    while (lo < hi) {
        int mid = lo + (hi - lo) / 2;
        if (canFinish(piles, mid, h))
            hi = mid;        // mid works, try a smaller value
        else
            lo = mid + 1;    // mid too slow, need a larger value
    }
    return lo;               // lo == hi, the minimum valid answer
}

private boolean canFinish(int[] piles, int speed, int h) {
    int hours = 0;
    for (int p : piles) hours += (p + speed - 1) / speed;  // ceiling division
    return hours <= h;
}
```

**Time:** O(n log(max)) | **Space:** O(1)

**Interview signal:** "Minimum/maximum value such that a condition holds", "feasible within constraints", "optimize a rate/capacity/speed" → binary search on the answer range. This is the #1 pattern Google loves.

**Same pattern, more problems:**
- [LC 1011 — Capacity to Ship Packages](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/) 🟡
- [LC 1482 — Minimum Number of Days to Make m Bouquets](https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/) 🟡
- [LC 1283 — Find the Smallest Divisor Given a Threshold](https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/) 🟡

---

### Pattern 6 — Find Minimum in Rotated Array (Pivot Finding)

**Problem:** [LC 153 — Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) 🟡

**The trick:** Compare `mid` with `hi` (not `lo`). If `nums[mid] > nums[hi]`, the rotation pivot (and minimum) is in the right half. Use `high = mid` (cautious) because mid could itself be the minimum.

```java
public int findMin(int[] nums) {
    int lo = 0, hi = nums.length - 1;
    while (lo < hi) {
        int mid = lo + (hi - lo) / 2;
        if (nums[mid] > nums[hi])
            lo = mid + 1;   // minimum is in the right half
        else
            hi = mid;       // mid could be the minimum, keep it
    }
    return nums[lo];        // lo == hi, pointing at minimum
}
```

**Time:** O(log n) | **Space:** O(1)

**Interview signal:** Always solve this before attempting Pattern 2 (rotated array search). Knowing the pivot unlocks all rotation problems.

**Why compare with `hi` not `lo`?** If the array is not rotated, `nums[mid] <= nums[hi]` always, and `hi` converges correctly. Comparing with `lo` breaks on non-rotated arrays.

---

### Pattern 7 — Kth Smallest in Sorted Matrix (Binary Search on Value Range)

**Problem:** [LC 378 — Kth Smallest Element in a Sorted Matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/) 🟡

**The trick:** Binary search over the *value range* `[min, max]`, not over indices. For each candidate `mid`, count how many elements in the matrix are `<= mid` using a staircase walk. Find the smallest value where count `>= k`.

```java
public int kthSmallest(int[][] matrix, int k) {
    int n = matrix.length;
    int lo = matrix[0][0], hi = matrix[n-1][n-1];
    while (lo < hi) {
        int mid = lo + (hi - lo) / 2;
        if (countLessOrEqual(matrix, mid, n) >= k)
            hi = mid;
        else
            lo = mid + 1;
    }
    return lo;
}

private int countLessOrEqual(int[][] matrix, int mid, int n) {
    int count = 0, row = n - 1, col = 0;
    while (row >= 0 && col < n) {
        if (matrix[row][col] <= mid) { count += row + 1; col++; }
        else row--;
    }
    return count;
}
```

**Time:** O(n log(max-min)) | **Space:** O(1)

**Interview signal:** "Kth smallest/largest" in a sorted matrix or merged sorted structure → binary search on value, not on index. The monotonic predicate is the count function.

---

### Pattern 8 — Time-Based / Versioned Search

**Problem:** [LC 981 — Time Based Key-Value Store](https://leetcode.com/problems/time-based-key-value-store/) 🟡

**The trick:** Binary search is not always on a plain array — sometimes you search over *timestamps* or *versions*. You want the latest entry whose timestamp is `<= given timestamp` (upper bound on time axis).

```java
class TimeMap {
    Map<String, List<int[]>> store = new HashMap<>(); // key -> [[timestamp, value_index]]
    Map<String, List<String>> values = new HashMap<>();

    public void set(String key, String value, int timestamp) {
        store.computeIfAbsent(key, k -> new ArrayList<>()).add(new int[]{timestamp});
        values.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }

    public String get(String key, int timestamp) {
        if (!store.containsKey(key)) return "";
        List<int[]> times = store.get(key);
        // Binary search for largest timestamp <= given timestamp (upper bound)
        int lo = 0, hi = times.size() - 1, ans = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (times.get(mid)[0] <= timestamp) { ans = mid; lo = mid + 1; }
            else hi = mid - 1;
        }
        return ans == -1 ? "" : values.get(key).get(ans);
    }
}
```

**Time:** O(log n) per get | **Space:** O(n)

**Interview signal:** "Most recent valid entry", "latest before timestamp", "versioned key-value", "snapshot at time T" → binary search on time or version axis using upper bound.

---

### Pattern 9 — Median of Two Sorted Arrays (Hard Classic)

**Problem:** [LC 4 — Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/) 🔴

**The trick:** Binary search on the *partition point* of the smaller array. The partition splits both arrays so that the entire left half has exactly `(m+n)/2` elements. Validate the partition by checking that the max of the left side <= min of the right side.

```java
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    // Always binary search on the smaller array
    if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);
    int m = nums1.length, n = nums2.length;
    int lo = 0, hi = m;

    while (lo <= hi) {
        int cut1 = lo + (hi - lo) / 2;          // partition in nums1
        int cut2 = (m + n + 1) / 2 - cut1;      // partition in nums2

        int l1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1 - 1];
        int l2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2 - 1];
        int r1 = cut1 == m ? Integer.MAX_VALUE : nums1[cut1];
        int r2 = cut2 == n ? Integer.MAX_VALUE : nums2[cut2];

        if (l1 <= r2 && l2 <= r1) {
            // Valid partition found
            if ((m + n) % 2 == 0)
                return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
            else
                return Math.max(l1, l2);
        } else if (l1 > r2) {
            hi = cut1 - 1;   // cut1 too far right
        } else {
            lo = cut1 + 1;   // cut1 too far left
        }
    }
    return 0;
}
```

**Time:** O(log(min(m,n))) | **Space:** O(1)

**Interview signal:** "Median of merged arrays", "kth element of two sorted arrays" → binary search on partition. This tests whether you know the technique cold. Practice until it's muscle memory.

---

### Pattern 10 — Minimize the Maximum / Maximize the Minimum

**Problem:** [LC 410 — Split Array Largest Sum](https://leetcode.com/problems/split-array-largest-sum/) 🔴

**The trick:** A harder variant of Pattern 5. "Minimize the maximum subarray sum when splitting into k parts." Binary search on the answer (the allowed max sum), validate using a greedy `canSplit` check that counts how many parts are needed.

```java
public int splitArray(int[] nums, int k) {
    // lo = must be at least the max element (every piece needs to fit)
    // hi = sum of all elements (one piece case)
    int lo = Arrays.stream(nums).max().getAsInt();
    int hi = Arrays.stream(nums).sum();

    while (lo < hi) {
        int mid = lo + (hi - lo) / 2;
        if (canSplit(nums, k, mid))
            hi = mid;        // this max sum works, try smaller
        else
            lo = mid + 1;    // too restrictive, relax the max sum
    }
    return lo;
}

private boolean canSplit(int[] nums, int k, int maxSum) {
    int parts = 1, current = 0;
    for (int n : nums) {
        if (current + n > maxSum) {
            parts++;         // start a new part
            current = 0;
        }
        current += n;
    }
    return parts <= k;       // feasible if we needed at most k parts
}
```

**Time:** O(n log(sum)) | **Space:** O(1)

**Interview signal:** "Minimize the maximum", "maximize the minimum", "distribute fairly among k groups", "allocate books/tasks/packages" → binary search on answer with greedy validation. Google frequently asks this as a follow-up to simpler problems.

**Same pattern, more problems:**
- [LC 1011 — Capacity to Ship Packages](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/) 🟡
- [LC 1231 — Divide Chocolate](https://leetcode.com/problems/divide-chocolate/) 🔴 (maximize the minimum)
- [LC 774 — Minimize Max Distance to Gas Station](https://leetcode.com/problems/minimize-max-distance-to-gas-station/) 🔴

---

## Quick Reference — All 10 Patterns

| # | Pattern | Key Problem | Difficulty | Google Frequency |
|---|---|---|---|---|
| 1 | Lower / Upper Bound | LC 34 | 🟡 Medium | High |
| 2 | Rotated Array Search | LC 33 | 🟡 Medium | Very High |
| 3 | Peak Finding | LC 162 | 🟡 Medium | High |
| 4 | 2D Matrix Search | LC 74 | 🟡 Medium | High |
| 5 | Binary Search on Answer | LC 875 | 🟡 Medium | ⭐ Very High |
| 6 | Find Pivot / Minimum | LC 153 | 🟡 Medium | High |
| 7 | Kth Smallest (Value Range) | LC 378 | 🟡 Medium | Medium |
| 8 | Time-Based / Versioned Search | LC 981 | 🟡 Medium | Medium |
| 9 | Median of Two Sorted Arrays | LC 4 | 🔴 Hard | High |
| 10 | Minimize the Maximum | LC 410 | 🔴 Hard | ⭐ Very High |

---

## Master Decision Tree

```
Is the array sorted (or partially sorted)?
│
├── YES — Standard binary search or rotated variant
│         Is it rotated?
│         ├── YES → Pattern 2 (search) or Pattern 6 (find min/pivot)
│         ├── Looking for boundary? → Pattern 1 (lower/upper bound)
│         └── Looking for peak? → Pattern 3
│
├── YES but it's a MATRIX → Pattern 4 (flatten) or Pattern 7 (value range)
│
├── YES but on TIMESTAMPS → Pattern 8 (time-based search)
│
└── NO sorted array, but optimizing a value?
          Does the problem say "minimize X" or "maximize X"?
          ├── YES → Pattern 5 or Pattern 10 (binary search on answer)
          │         Write a canDo(mid) predicate and binary search the range
          └── Hard partitioning? → Pattern 9 (median of two arrays)
```

> **Priority order for interview prep:** Master Patterns 2, 5, and 10 first — these are the patterns Google interviewers reach for most consistently based on real interview reports. Patterns 5 and 10 share the same template with different `canDo()` functions, so mastering one unlocks a whole family of hard problems.
