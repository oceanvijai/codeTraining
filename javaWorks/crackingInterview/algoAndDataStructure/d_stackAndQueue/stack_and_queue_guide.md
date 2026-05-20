# Stack & Queue Patterns — Quick Reference

A 2-minute-read cheat sheet for the 5 core patterns covering Monotonic Stack, Monotonic Queue (Deque), and their applications in FAANG interviews.

---

## Part 1 — The 5 Core Patterns at a Glance

| # | Pattern | Mental Model | How to Identify (Interview Signals) | Rules to Apply | How to Apply (Layer 1 → Layer 2) | Sample Problems |
|---|---|---|---|---|---|---|
| **1** | **Monotonic Stack** | A "waiting room" stack — elements wait until something resolves them, then get popped with their answer | • "Next/previous greater or smaller element"<br>• "Days until warmer / first taller building"<br>• "Streak / span ending at today"<br>• Brute force is O(n²), need O(n) | • Stack order is OPPOSITE of search target: greater → decreasing stack; smaller → increasing stack<br>• Direction: L→R finds NEXT boundary; R→L finds PREV boundary<br>• Store INDICES not values | **L1 — Plumbing:**<br>• Pick stack order (Rule 1)<br>• Pick scan direction (Rule 2)<br>• Pop trigger = `current vs top` comparison<br>**L2 — Semantics:**<br>• Pop-as-resolution: write answer for popped element<br>• Pop-as-aggregation: merge popped data into current (online streams) | LC #739, LC #496, LC #503, LC #901, LC #1019 |
| **2** | **Contribution Technique** (Prev + Next Smaller/Greater) | Instead of "min of each subarray", flip to "how many subarrays does each element bound" — count contributions, sum them | • "Sum/count over ALL subarrays"<br>• Aggregate depends on min/max of subarray<br>• `n ≥ 10^4` (rules out O(n²))<br>• Modulo 10^9+7 hint | • Need BOTH boundaries → two passes OR one pass + sentinel<br>• Strict `<` on one pass, non-strict `≤` on the other (tie-breaking)<br>• Formula: `arr[i] × left[i] × right[i]` | **L1 — Plumbing:**<br>• Pass 1 (L→R): `left[i]` = distance to prev smaller<br>• Pass 2 (R→L): `right[i]` = distance to next smaller-or-equal<br>**L2 — Semantics:**<br>• Each element bounds `left[i] × right[i]` subarrays<br>• Total = Σ (arr[i] × left[i] × right[i]) | LC #84, LC #85, LC #907, LC #2104, LC #1856 |
| **3** | **Sliding Window Deque** | A deque holding "useful candidates" — front is always the window max/min; evict from front by time, from back by value | • Fixed window size `k` mentioned<br>• "Max/min in every window of size k"<br>• "Every contiguous subarray of length k"<br>• Need O(n) over O(n×k) | • Store INDICES (need them for expiry check)<br>• Evict from FRONT when index < `i-k+1` (time)<br>• Evict from BACK when dominated (value)<br>• Maintain decreasing (for max) or increasing (for min) order | **L1 — Plumbing:**<br>• Two orthogonal invariants: order + window<br>• Front = window aggregate, O(1) lookup<br>**L2 — Semantics:**<br>• Direct: read front for window max/min<br>• Composed with prefix sums (LC #862) or DP (Pattern 5) | LC #239, LC #862, LC #1438, LC #1499, LC #480 |
| **4** | **Reduction Trick** | Transform a hard N-dimensional problem into M instances of a known (N-1)-dimensional problem — algorithm taste over algorithm invention | • Higher-dimensional problem (2D matrix, 3D tensor)<br>• A simpler 1D version is well-known<br>• Brute force is polynomial but slow<br>• "Fix one dimension, iterate" feels possible | • Identify the inner known problem<br>• Build the "input shape" the inner algo expects<br>• Reuse heights[] / state across iterations — don't rebuild | **L1 — Plumbing:**<br>• Pick the transformation (row→histogram, fix-2-rows→1D, etc.)<br>**L2 — Semantics:**<br>• Apply known O(n) or O(n log n) algorithm as a black box<br>• Aggregate results across iterations | LC #85, LC #221, LC #363, LC #1074, LC #2281 |
| **5** | **Deque as DP Optimizer** | A deque that operates on the DP array AS IT'S BEING BUILT — converts O(n×k) DP into O(n) by tracking window max/min over `dp[]` | • DP recurrence shape: `dp[i] = f(input[i]) + best(dp[i-k..i-1])`<br>• `best` is max or min<br>• Bounded look-back (not full history)<br>• Naïve O(n×k) too slow | • Deque stores INDICES into `dp[]`, not into `nums[]`<br>• Compute `dp[i]` FIRST (using deque's front), THEN evict from back<br>• Push current index after computing | **L1 — Plumbing:**<br>• Step 1: age-out front (index < i-k)<br>• Step 2: `dp[i] = f(input[i], dp[dq.front])`<br>• Step 3: evict back where `dp[back] ≤ dp[i]`<br>• Step 4: push i<br>**L2 — Semantics:**<br>• Self-referential loop: `dp[i]` becomes future input to the deque<br>• Same template as Pattern 3 but on `dp[]` | LC #1696, LC #1425, LC #1499, LC #918, LC #1340 |

---

## Decision Tree (60-second classifier)

Ask in order:

1. **Fixed-size window mentioned?** → Pattern 3 (deque on input) or Pattern 5 (deque on DP)
   - DP recurrence with bounded look-back → Pattern 5
   - Direct aggregate over input → Pattern 3
2. **Sum/count over ALL subarrays based on min/max?** → Pattern 2 (contribution)
3. **Find next/prev element satisfying a relative condition?** → Pattern 1 (monotonic stack)
4. **Higher-dimensional problem with a known 1D version?** → Pattern 4 (reduction), then re-ask 1–3 on the reduced problem

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
