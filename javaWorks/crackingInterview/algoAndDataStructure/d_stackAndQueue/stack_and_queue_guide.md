# Stack & Queue Patterns — Quick Reference

A 2-minute-read cheat sheet for the 5 core patterns covering Monotonic Stack, Monotonic Queue (Deque), and their applications in FAANG interviews.

---

## Part 1 — The 5 Core Patterns at a Glance

| Pattern | Mental Model | When You See It → Examples | Rules + How to Apply |
|---|---|---|---|
| **Monotonic Stack** | A "waiting room" stack — elements wait until something resolves them, then get popped with their answer. | **Signals:** "next/prev greater or smaller", "days until warmer", "first taller building", "streak/span ending today". Brute force is O(n²), need O(n).<br><br>**Examples:** LC #739, #496, #503, #901, #1019 | **Rule 1 — Stack order:** OPPOSITE of search target. Greater → decreasing stack; smaller → increasing stack.<br>**Rule 2 — Direction:** L→R finds NEXT boundary; R→L finds PREV boundary.<br>**Rule 3 — Always store INDICES**, not values.<br><br>**Pop semantic:** either write answer for the popped element (offline), or aggregate popped data into the current element (online streams). |
| **Contribution Technique** (Prev + Next Smaller/Greater) | Instead of "min of each subarray", flip to **"how many subarrays does each element bound as the min/max"** — count contributions, sum them. | **Signals:** "sum/count over ALL subarrays", aggregate depends on min/max, n ≥ 10⁴, modulo 10⁹+7 hint.<br><br>**Examples:** LC #84, #85, #907, #2104, #1856 | **Need BOTH boundaries** → two passes (one L→R, one R→L) OR one L→R pass + sentinel.<br>**Tie-breaking:** strict `<` on one pass, non-strict `≤` on the other — otherwise duplicates double-count.<br><br>**Steps:**<br>1. `left[i]` = # of left-endpoint choices = `i − prev_smaller_idx`<br>2. `right[i]` = # of right-endpoint choices = `next_smaller_idx − i`<br>3. Answer = Σ `arr[i] × left[i] × right[i]` |
| **Sliding Window Deque** | A deque holding "useful candidates" — front is always the window max/min. Evict from front by **time** (out of window), from back by **value** (dominated). | **Signals:** fixed window size `k` mentioned, "max/min in every window of size k", "every contiguous subarray of length k". Need O(n) over O(n×k).<br><br>**Examples:** LC #239, #862, #1438, #1499, #480 | **Two orthogonal invariants:** decreasing order (for max) + all indices within window.<br>**Store INDICES** — needed for expiry check.<br><br>**Steps for each `i`:**<br>1. Evict FRONT if `dq.peekFirst() < i − k + 1` (time)<br>2. Evict BACK while `nums[dq.peekLast()] ≤ nums[i]` (value)<br>3. Push `i`<br>4. Read `nums[dq.peekFirst()]` once window is full = answer |
| **Reduction Trick** | Transform a hard N-dimensional problem into M instances of a known (N−1)-dimensional problem. **Algorithm taste over algorithm invention.** | **Signals:** higher-dimensional problem (2D matrix), simpler 1D version is well-known, "fix one dimension, iterate" feels possible.<br><br>**Examples:** LC #85, #221, #363, #1074, #2281 | **Steps:**<br>1. Identify the inner known problem (e.g., row → histogram → LC #84).<br>2. Build the input shape the inner algorithm expects.<br>3. Reuse state across iterations — don't rebuild from scratch.<br>4. Call the known O(n) algorithm as a black box per iteration.<br>5. Aggregate results (e.g., `max` across all rows). |
| **Deque as DP Optimizer** | A deque operating on the **DP array as it's being built** — converts O(n×k) DP into O(n) by tracking window max/min over `dp[]`. | **Signals:** DP recurrence shape `dp[i] = f(input[i]) + best(dp[i-k..i-1])` where `best` is max/min, bounded look-back, naïve O(n×k) too slow.<br><br>**Examples:** LC #1696, #1425, #1499, #918, #1340 | **Deque stores INDICES into `dp[]`**, not into `nums[]`.<br><br>**Steps for each `i`:**<br>1. Age-out front: pop while `dq.peekFirst() < i − k`<br>2. **Compute `dp[i]` FIRST** using `dp[dq.peekFirst()]`<br>3. **Then** evict back while `dp[dq.peekLast()] ≤ dp[i]`<br>4. Push `i`<br><br>**Key insight:** self-referential — `dp[i]` becomes a future input to the deque. |

---

## Decision Tree (60-second classifier)

Ask in order:

1. **Fixed-size window?** → Pattern 3 (deque on input) or Pattern 5 (deque on DP)
   - DP recurrence with bounded look-back → **Pattern 5**
   - Direct aggregate over input → **Pattern 3**
2. **Sum/count over ALL subarrays based on min/max?** → **Pattern 2** (contribution)
3. **Find next/prev element satisfying a relative condition?** → **Pattern 1** (monotonic stack)
4. **Higher-dimensional problem with a known 1D version?** → **Pattern 4** (reduction), then re-ask 1–3 on the reduced problem

---

## Part 2 — Code Examples for All 5 Patterns

### Pattern 1 — Monotonic Stack (Daily Temperatures, LC #739)

**Sample Input:** `temps = [73, 74, 75, 71, 69, 72, 76, 73]`
**Expected Output:** `[1, 1, 4, 2, 1, 1, 0, 0]` (days until warmer)

**Manual walkthrough:**
- Day 0 (73°): waits 1 day → day 1 (74°) is warmer. answer[0]=1
- Day 2 (75°): waits 4 days through 71°, 69°, 72° until day 6 (76°). answer[2]=4
- Days 6, 7: no warmer day ahead. answer = 0

The stack acts as a "waiting room" of decreasing temps. When a warmer day arrives, it pops out everyone waiting for it.

```java
public int[] dailyTemperatures(int[] t) {
    int n = t.length;
    int[] ans = new int[n];                          // default 0 — correct for unresolved days
    Deque<Integer> stack = new ArrayDeque<>();       // stores INDICES, decreasing temps
    
    for (int i = 0; i < n; i++) {
        // Pop trigger: current day strictly warmer → resolves waiting day(s)
        while (!stack.isEmpty() && t[i] > t[stack.peek()]) {
            int waitingIdx = stack.pop();
            ans[waitingIdx] = i - waitingIdx;        // gap = days waited
        }
        stack.push(i);                                // current day joins the queue
    }
    return ans;                                       // O(n) — each element pushed/popped once
}
```

---

### Pattern 2 — Contribution Technique (Sum of Subarray Minimums, LC #907)

**Sample Input:** `arr = [3, 1, 2, 4]`
**Expected Output:** `17` (sum of mins over all 10 subarrays)

**Manual walkthrough:** For each element, count subarrays where it's the minimum:
- arr[0]=3: min of [3] only → contributes 3×1 = 3
- arr[1]=1: min of [3,1], [1], [3,1,2], [1,2], [3,1,2,4], [1,2,4] → 6 subarrays → contributes 1×6 = 6
- arr[2]=2: min of [2], [2,4] → contributes 2×2 = 4
- arr[3]=4: min of [4] only → contributes 4×1 = 4

Total = 3 + 6 + 4 + 4 = 17. Formula: `arr[i] × left[i] × right[i]` per element.

```java
public int sumSubarrayMins(int[] arr) {
    int n = arr.length, MOD = 1_000_000_007;
    int[] left = new int[n], right = new int[n];
    Deque<Integer> st = new ArrayDeque<>();
    
    // Pass 1 (L→R): left[i] = # of subarray-left choices where arr[i] is min
    //               = distance to previous STRICTLY smaller element
    for (int i = 0; i < n; i++) {
        while (!st.isEmpty() && arr[st.peek()] >= arr[i]) st.pop();
        left[i] = st.isEmpty() ? i + 1 : i - st.peek();
        st.push(i);
    }
    st.clear();
    
    // Pass 2 (R→L): right[i] = distance to NEXT smaller-or-equal (non-strict, breaks ties)
    for (int i = n - 1; i >= 0; i--) {
        while (!st.isEmpty() && arr[st.peek()] > arr[i]) st.pop();
        right[i] = st.isEmpty() ? n - i : st.peek() - i;
        st.push(i);
    }
    
    long sum = 0;
    for (int i = 0; i < n; i++)
        sum = (sum + (long) arr[i] * left[i] * right[i]) % MOD;
    return (int) sum;
}
```

---

### Pattern 3 — Sliding Window Deque (Sliding Window Maximum, LC #239)

**Sample Input:** `nums = [1, 3, -1, -3, 5, 3, 6, 7], k = 3`
**Expected Output:** `[3, 3, 5, 5, 6, 7]`

**Manual walkthrough:** Slide a window of size 3 across, take the max each time.
- Windows: [1,3,-1]→3, [3,-1,-3]→3, [-1,-3,5]→5, [-3,5,3]→5, [5,3,6]→6, [3,6,7]→7

The deque holds "still useful" indices in decreasing value order. Front is always the answer.

```java
public int[] maxSlidingWindow(int[] nums, int k) {
    int n = nums.length;
    int[] result = new int[n - k + 1];
    Deque<Integer> dq = new ArrayDeque<>();          // stores INDICES, decreasing nums
    
    for (int i = 0; i < n; i++) {
        // Evict FRONT — by TIME (out of window)
        if (!dq.isEmpty() && dq.peekFirst() < i - k + 1)
            dq.pollFirst();
        
        // Evict BACK — by VALUE (dominated by current element)
        while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i])
            dq.pollLast();
        
        dq.offerLast(i);
        
        // Front is always the window max
        if (i >= k - 1)
            result[i - k + 1] = nums[dq.peekFirst()];
    }
    return result;                                    // O(n) — each index added/removed once
}
```

---

### Pattern 4 — Reduction Trick (Maximal Rectangle, LC #85)

**Sample Input:**
```
matrix = [
  ['1','0','1','0','0'],
  ['1','0','1','1','1'],
  ['1','1','1','1','1'],
  ['1','0','0','1','0']
]
```
**Expected Output:** `6` (largest rectangle of 1s)

**Manual walkthrough:** Convert each row into a histogram where `heights[j]` = consecutive 1s ending at this row in column j. Then apply LC #84 to each histogram.
- Row 0: heights = [1,0,1,0,0] → max rect = 1
- Row 1: heights = [2,0,2,1,1] → max rect = 3
- Row 2: heights = [3,1,3,2,2] → max rect = 6 ← answer
- Row 3: heights = [4,0,0,3,0] → max rect = 4

```java
public int maximalRectangle(char[][] matrix) {
    if (matrix.length == 0) return 0;
    int cols = matrix[0].length, maxArea = 0;
    int[] heights = new int[cols];                   // reuse across rows
    
    for (char[] row : matrix) {
        // Reduction: row → histogram (extend streak or reset to 0)
        for (int j = 0; j < cols; j++)
            heights[j] = (row[j] == '1') ? heights[j] + 1 : 0;
        // Black-box call to known O(n) algorithm
        maxArea = Math.max(maxArea, largestRectangleArea(heights));
    }
    return maxArea;                                   // O(rows × cols)
}

private int largestRectangleArea(int[] h) {           // LC #84
    Deque<Integer> st = new ArrayDeque<>();
    int max = 0, n = h.length;
    for (int i = 0; i <= n; i++) {
        int cur = (i == n) ? 0 : h[i];               // sentinel 0 flushes stack
        while (!st.isEmpty() && h[st.peek()] > cur) {
            int height = h[st.pop()];
            int width = st.isEmpty() ? i : i - st.peek() - 1;
            max = Math.max(max, height * width);
        }
        st.push(i);
    }
    return max;
}
```

---

### Pattern 5 — Deque as DP Optimizer (Jump Game VI, LC #1696)

**Sample Input:** `nums = [1, -1, -2, 4, -7, 3], k = 2`
**Expected Output:** `7`

**Manual walkthrough:** DP recurrence: `dp[i] = nums[i] + max(dp[i-k..i-1])`.
- dp[0] = 1
- dp[1] = -1 + max(dp[0]) = -1 + 1 = 0
- dp[2] = -2 + max(dp[0], dp[1]) = -2 + 1 = -1
- dp[3] = 4 + max(dp[1], dp[2]) = 4 + 0 = 4
- dp[4] = -7 + max(dp[2], dp[3]) = -7 + 4 = -3
- dp[5] = 3 + max(dp[3], dp[4]) = 3 + 4 = 7 ← answer

Naïve inner `max` is O(k). The deque tracks window max over `dp[]` in O(1) per step.

```java
public int maxResult(int[] nums, int k) {
    int n = nums.length;
    int[] dp = new int[n];
    dp[0] = nums[0];
    
    Deque<Integer> dq = new ArrayDeque<>();          // stores INDICES into dp[], not nums[]
    dq.offerLast(0);
    
    for (int i = 1; i < n; i++) {
        // Step 1: age-out front (jump range = [i-k, i-1])
        while (!dq.isEmpty() && dq.peekFirst() < i - k)
            dq.pollFirst();
        
        // Step 2: compute dp[i] FIRST using deque's front (the O(1) window-max lookup)
        dp[i] = nums[i] + dp[dq.peekFirst()];
        
        // Step 3: THEN evict dominated indices from back (after dp[i] is finalized)
        while (!dq.isEmpty() && dp[dq.peekLast()] <= dp[i])
            dq.pollLast();
        
        // Step 4: push current index — it becomes a future window-max candidate
        dq.offerLast(i);
    }
    return dp[n - 1];                                 // O(n) total
}
```

---

## Final Tips

- **Always store indices** in the stack/deque — you'll need them for distance math and window expiry checks.
- **Strict vs non-strict comparisons matter.** When in doubt, work an example with duplicates. Off-by-one bugs hide in ties.
- **Sentinels are your friend** — append a `0` (for min/area problems) or `Integer.MAX_VALUE` (for max problems) to flush the stack at the end.
- **In Java, prefer `Deque<Integer> dq = new ArrayDeque<>()`** over `Stack` (which is synchronized and slow) or `LinkedList` (cache-unfriendly).
- **Recognize the pattern first, write code second.** 60 seconds of classification saves 20 minutes of dead-end coding.
