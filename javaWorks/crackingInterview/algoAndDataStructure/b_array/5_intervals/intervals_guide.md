# Interval Patterns — Complete Interview Guide (Java)

> Built from first principles across 7 patterns, covering medium and hard problems
> from Google, Meta, Amazon and other top-tier interviews.

---

## Thumb Rule — When to Apply Interval Technique

**Apply it when:**
- Input is pairs `[start, end]` representing time ranges, index ranges, or numeric spans
- Problem asks about overlaps, gaps, merging, or coverage
- You need to count concurrent events (meetings, bookings, passengers)
- Words in the problem: "earliest", "minimum rooms", "free slot", "overlap", "cover"
- You have range queries on a numeric axis

**Do NOT apply it when:**
- Input has no inherent start/end boundaries
- Problem is about contiguous subarrays → use Sliding Window
- You are searching a sorted array → use Binary Search
- Ranges span the whole array trivially → use Prefix Sum
- Problem is purely combinatorial (permutations, subsets)

---

## Pattern Summary Table

| # | Pattern Name | Mental Model & How to Identify | Rules & Steps |
|---|---|---|---|
| 1 | **Merge Overlapping Intervals** | Intervals pile up and you need to consolidate them. Signal: "merge", "combine", "union of ranges". LC 56, LC 252 | 1. Sort by start. 2. Walk left→right. 3. If curr.start ≤ last.end → merge (take max end). 4. Else push new interval. |
| 2 | **Insert & Merge** | Sorted list exists, insert one new interval and re-merge. Signal: "insert into", "add range". LC 57, LC 715 | 1. Phase 1: copy all intervals ending before new one starts. 2. Phase 2: merge all overlapping with new. 3. Phase 3: copy remainder. No sort needed — O(n). |
| 3 | **Min Resources (Rooms/CPUs)** | Concurrent events compete for limited slots. Signal: "minimum rooms", "maximum overlap", "capacity". LC 253, LC 1094, LC 759 | 1. Sort by start. 2. Min-heap of end-times. 3. If heap.peek() ≤ curr.start → recycle (poll). 4. Always push curr.end. 5. heap.size() = answer. |
| 4 | **Greedy Removal / Selection** | Keep as many non-overlapping intervals as possible. Signal: "minimum removal", "maximum non-overlapping", "minimum arrows". LC 435, LC 452, LC 646 | 1. Sort by END (not start). 2. Walk left→right. 3. If curr.start >= prevEnd → keep (update prevEnd). 4. Else → remove (count++). prevEnd stays. |
| 5 | **Sweep Line (Event Counting)** | Convert intervals to events, count active at each point. Signal: "how many overlap at point X", "peak concurrency", "difference array". LC 253 alt, LC 2251, LC 1854 | 1. For each interval: add (time, +1) and (time, -1). 2. Sort events: by time, END before START on tie. 3. Sweep, maintain running counter. 4. max(counter) = answer. |
| 6 | **Interval + Offline Query** | Per-query answers over intervals. Signal: separate `queries[]` array asking best interval per point. LC 1851, LC 2251, LC 2736 | 1. Sort intervals by start. 2. Sort queries by value (attach original index). 3. For each query: add all intervals with start ≤ query into heap. 4. Evict dead (end < query). 5. heap.peek() = answer. |
| 7 | **Skyline / Height Sweep** | Intervals carry a value (height). Track live maximum as intervals open and close. Signal: "outline", "silhouette", "maximum height at each point". LC 218, LC 850, LC 699 | 1. Build events: (x, START, height) and (x, END, height). 2. Sort: by x; START before END on tie. 3. Max-heap with lazy deletion. 4. After each event: evict dead tops, emit point if max changes. |

---

## Pattern 1 — Merge Overlapping Intervals

### Mental Model
Think of intervals as paint strokes on a number line. Overlapping strokes merge into one longer stroke. After sorting by start, you only ever need to compare the current interval against the last merged one.

### How to Identify
- Problem gives unsorted intervals and asks you to consolidate
- Keywords: "merge", "union", "combine ranges"
- Single output: a cleaned list with no overlaps

### LC Examples
- LC 56 — Merge Intervals (Medium) — Google, Meta
- LC 252 — Meeting Rooms I (Easy) — can all meetings fit without overlap?

### Rules & Steps
- Sort by **start time**
- Walk left to right maintaining a result list
- **Overlap condition:** `curr.start <= last.end` (note: ≤ not <)
- On overlap: extend last interval's end to `max(last.end, curr.end)`
- On no overlap: push current as a new interval
- **Tie-breaking:** check whether intervals are open or closed — changes `<` vs `<=`

### Do's
- Always sort first — unsorted input breaks the linear scan
- Use `Math.max` for the end — the current interval might be fully contained inside the last

### Don'ts
- Don't assume `curr.end > last.end` on overlap — interval could be nested
- Don't sort by end — that's Pattern 4, different purpose

### Template (LC 56)
```java
public int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);       // sort by start
    List<int[]> res = new ArrayList<>();
    for (int[] cur : intervals) {
        if (res.isEmpty() || res.get(res.size()-1)[1] < cur[0]) {
            res.add(cur);                                  // no overlap — push
        } else {
            res.get(res.size()-1)[1] =
                Math.max(res.get(res.size()-1)[1], cur[1]); // merge
        }
    }
    return res.toArray(new int[0][]);
    // Time: O(n log n)   Space: O(n)
}
```

---

## Pattern 2 — Insert & Merge

### Mental Model
The list is already sorted and clean. You drop in one new interval and patch exactly the affected region. Three phases: left untouched | merged zone | right untouched.

### How to Identify
- Input is **already sorted** (explicit in problem)
- You insert one interval and return the updated list
- Keywords: "insert", "add range", "update"

### LC Examples
- LC 57 — Insert Interval (Medium) — Google, LinkedIn
- LC 715 — Range Module (Hard) — Google — design with addRange/removeRange/queryRange

### Rules & Steps
- **Phase 1:** copy all intervals where `iv.end < new.start` (entirely left of new)
- **Phase 2:** while `iv.start <= new.end` → merge into new interval (expand new's bounds)
- **Phase 3:** copy all remaining intervals
- No sort needed — O(n)
- For LC 715 (dynamic): use a `TreeMap<start, end>` instead of an array

### Do's
- Three phases must be done in order — don't mix them
- Phase 2 must update BOTH `new.start` (min) and `new.end` (max)

### Don'ts
- Don't sort — input is already sorted, sorting wastes O(n log n)
- Don't try to do it in one pass with complex conditionals — three phases keeps it clean

### Template (LC 57)
```java
public int[][] insert(int[][] intervals, int[] newInterval) {
    List<int[]> res = new ArrayList<>();
    int i = 0, n = intervals.length;

    // Phase 1: entirely left — no overlap
    while (i < n && intervals[i][1] < newInterval[0])
        res.add(intervals[i++]);

    // Phase 2: overlap — keep merging into newInterval
    while (i < n && intervals[i][0] <= newInterval[1]) {
        newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
        newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
        i++;
    }
    res.add(newInterval);

    // Phase 3: entirely right
    while (i < n) res.add(intervals[i++]);

    return res.toArray(new int[0][]);
    // Time: O(n)   Space: O(n)
}
```

---

## Pattern 3 — Minimum Resources (Rooms / CPUs)

### Mental Model
Imagine a hotel. Each interval is a guest with check-in and check-out times. You want the minimum number of rooms. A room is free if its current guest checked out before the next one checks in. The heap holds the check-out times of every occupied room — the minimum tells you which room frees up first.

### How to Identify
- Problem asks for minimum number of slots/rooms/threads/CPUs
- Equivalently: maximum number of intervals overlapping at any point
- Keywords: "minimum rooms", "maximum concurrent", "at most k active"
- No separate query array (that's Pattern 6)

### LC Examples
- LC 253 — Meeting Rooms II (Medium) — Google, Amazon
- LC 1094 — Car Pooling (Medium) — Google — intervals carry passenger weight
- LC 759 — Employee Free Time (Hard) — Google — find gaps across multiple schedules
- LC 1229 — Meeting Scheduler (Medium) — find earliest common free slot

### Rules & Steps
- Sort intervals by **start time**
- Min-heap stores **end times** of currently occupied rooms
- For each interval: if `heap.peek() <= curr.start` → recycle (poll first, then push new end)
- Always push `curr.end` into heap
- `heap.size()` at the end = minimum rooms needed
- For weighted variants (Car Pooling): heap stores running sum, not count

### Do's
- Sort by START (not end — that's Pattern 4)
- Poll before pushing on recycle — keeps heap size accurate
- For LC 759: merge all employee schedules, find gaps where global count = 0

### Don'ts
- Don't use heap size mid-loop as the answer — measure it only at the end
- Don't forget that heap.peek() ≤ start means the room is FREE (the guest already left)
- Don't use Pattern 3 when there's a separate queries array — that's Pattern 6

### Template (LC 253)
```java
public int minMeetingRooms(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);       // sort by start
    PriorityQueue<Integer> heap = new PriorityQueue<>();   // min-heap of end times

    for (int[] iv : intervals) {
        if (!heap.isEmpty() && heap.peek() <= iv[0])
            heap.poll();           // recycle: earliest-ending room is now free
        heap.offer(iv[1]);         // occupy a room until iv[1]
    }

    return heap.size();            // rooms currently occupied = answer
    // Time: O(n log n)   Space: O(n)
}
```

---

## Pattern 4 — Greedy Removal / Selection

### Mental Model
You want to keep as many intervals as possible without any overlap. The interval that ends earliest "gets out of the way" fastest, leaving maximum room for future intervals. This is the classic Activity Selection problem — always pick the interval that finishes first.

### How to Identify
- Problem asks for minimum removals to make intervals non-overlapping
- OR maximum number of non-overlapping intervals you can keep
- OR minimum "arrows/pierces" to cover/burst all intervals
- Keywords: "minimum removal", "non-overlapping", "minimum arrows"

### LC Examples
- LC 435 — Non-overlapping Intervals (Medium) — Google, Meta
- LC 452 — Min Number of Arrows to Burst Balloons (Medium) — Google, Amazon
- LC 646 — Maximum Length of Pair Chain (Medium) — Google

### Rules & Steps
- Sort by **END time** (not start — this is the most common mistake)
- Walk left to right, track `prevEnd`
- **Keep condition:** `curr.start >= prevEnd` → no overlap → keep, update `prevEnd = curr.end`
- **Remove condition:** `curr.start < prevEnd` → overlap → `count++`, `prevEnd` stays unchanged
- No heap needed — pure O(n) scan after sort

### Do's
- Sort by END — exchange argument proves this is optimal
- Keep `prevEnd` unchanged on removal — you are keeping the earlier-ending interval (already processed)
- Use `>=` for LC 435 (touching is NOT overlapping)
- Use `>` for LC 452 (touching balloons share one arrow)

### Don'ts
- Don't sort by start — breaks on nested intervals like `[1,100],[2,3],[4,5]`
- Don't use `Math.min` on overlap — if you find yourself doing that, switch to sort-by-end
- Don't update `prevEnd` on removal — that's the key greedy invariant

### Template (LC 435)
```java
public int eraseOverlapIntervals(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> a[1] - b[1]);   // sort by END
    int count = 0, prevEnd = intervals[0][1];

    for (int i = 1; i < intervals.length; i++) {
        if (intervals[i][0] >= prevEnd) {             // >= : touching is fine
            prevEnd = intervals[i][1];                // keep — update boundary
        } else {
            count++;                                  // remove — prevEnd stays
        }
    }
    return count;
    // Time: O(n log n)   Space: O(1)
}

// LC 452 — one character different
public int findMinArrowShots(int[][] points) {
    Arrays.sort(points, (a, b) -> a[1] - b[1]);      // sort by END
    int arrows = 1, end = points[0][1];

    for (int i = 1; i < points.length; i++) {
        if (points[i][0] > end) {                     // > : touching shares arrow
            arrows++;
            end = points[i][1];
        }
    }
    return arrows;
}
```

---

## Pattern 5 — Sweep Line (Event Counting)

### Mental Model
Convert every interval into two point-events on a timeline: +1 at start, -1 at end. Sort and sweep. Your running counter tells you exactly how many intervals are active at any moment. The peak is your answer.

### How to Identify
- Need to know how many intervals are active at a specific point or across all points
- No per-query answering needed (that's Pattern 6)
- Keywords: "how many X at time T", "peak load", "difference array"
- Useful when intervals have weights (passengers, not just ±1)

### LC Examples
- LC 253 — Meeting Rooms II (alternate sweep approach)
- LC 2251 — Number of Flowers in Full Bloom (Hard) — Google
- LC 1854 — Maximum Population Year (Easy-Medium)
- LC 1094 — Car Pooling (weighted sweep variant)

### Rules & Steps
- For each interval: push `(start, +1)` and `(end, -1)` into events list
- Sort events: by time first; on tie, **END (-1) before START (+1)**
- Sweep: `counter += event.delta`; track `max(counter)`
- Tie-breaking is critical: end-before-start ensures back-to-back intervals don't double-count

### Do's
- Always handle the tie-break — it's the most common bug
- For weighted problems (Car Pooling): use `+passengers` and `-passengers` as delta
- For gap detection (LC 759): emit a gap when counter transitions 0→positive

### Don'ts
- Don't forget tie-breaking — `(time, -1)` before `(time, +1)` on same timestamp
- Don't use this when you need per-query answers — that's O(n+q) vs O(n×q) with Pattern 6

### Template (peak concurrency)
```java
public int maxConcurrent(int[][] intervals) {
    List<int[]> events = new ArrayList<>();
    for (int[] iv : intervals) {
        events.add(new int[]{iv[0], +1});   // start
        events.add(new int[]{iv[1], -1});   // end
    }
    // Sort: by time; on tie END(-1) sorts before START(+1)
    events.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

    int cur = 0, max = 0;
    for (int[] e : events) {
        cur += e[1];
        max = Math.max(max, cur);
    }
    return max;
    // Time: O(n log n)   Space: O(n)
}
```

---

## Pattern 6 — Interval + Offline Query

### Mental Model
You have intervals and a separate list of query points. For each query, you want the "best" interval (smallest, largest, max sum) that covers the query point. The trick: sort both intervals and queries, sweep queries left to right, and maintain a heap of live candidates. One dimension is eliminated by the sort; the heap handles the other.

### How to Identify
- Problem has a **separate `queries[]` array** — this is the single fastest signal
- Each query is answered independently
- Keywords: "for each query find...", "answer queries", "at each point"

### LC Examples
- LC 1851 — Minimum Interval to Include Each Query (Hard) — Google
- LC 2251 — Number of Flowers in Full Bloom (Hard) — Google
- LC 2736 — Maximum Sum Queries (Hard) — Google

### Rules & Steps
1. Sort intervals by **start**
2. Sort queries by value, **attach original index** to restore order later
3. For each query q (in sorted order):
   - **Add** all intervals with `start <= q` into the heap (keyed by answer value)
   - **Evict** from heap top while `heap.peek().end < q` (interval no longer covers q)
   - **Answer** = `heap.peek().value` or -1 if empty
4. Store answer at `ans[originalIndex]`

### Do's
- Always attach original index to queries before sorting
- Add first, then evict — heap must have candidates before cleaning
- Evict condition: `end < query` (strict less-than — touching counts as covering)
- Heap key = the value you want to optimise (size for LC 1851, sum for LC 2736)

### Don'ts
- Don't evict with `<=` — an interval ending exactly at query point still covers it
- Don't forget to restore original query order via the stored index
- Don't process queries unsorted — the whole point is the sorted sweep

### Template (LC 1851)
```java
public int[] minInterval(int[][] intervals, int[] queries) {
    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);   // sort intervals by start

    // Attach original index to queries, then sort by value
    int q = queries.length;
    int[][] qs = new int[q][2];
    for (int i = 0; i < q; i++) qs[i] = new int[]{queries[i], i};
    Arrays.sort(qs, (a, b) -> a[0] - b[0]);

    // Min-heap keyed by interval size {size, end}
    PriorityQueue<int[]> heap =
        new PriorityQueue<>((a, b) -> a[0] - b[0]);
    int[] ans = new int[q];
    int pi = 0;                                       // pointer into sorted intervals

    for (int[] qr : qs) {
        int qVal = qr[0], origIdx = qr[1];

        // Add all intervals whose start <= current query
        while (pi < intervals.length && intervals[pi][0] <= qVal) {
            int size = intervals[pi][1] - intervals[pi][0] + 1;
            heap.offer(new int[]{size, intervals[pi][1]});
            pi++;
        }

        // Evict intervals that ended before this query
        while (!heap.isEmpty() && heap.peek()[1] < qVal)
            heap.poll();

        ans[origIdx] = heap.isEmpty() ? -1 : heap.peek()[0];
    }
    return ans;
    // Time: O((n + q) log n)   Space: O(n + q)
}
```

---

## Pattern 7 — Skyline / Height Sweep

### Mental Model
Each interval carries a height. As you sweep left to right, buildings open (height enters) and close (height leaves). You need the maximum height at every point — and you only emit a key point when that maximum changes. Lazy deletion in a max-heap avoids O(n) removals.

### How to Identify
- Intervals carry a VALUE (height, weight, area) — not just start/end
- You need the running MAXIMUM (not count) of active interval values
- Output is a list of (x, value) change-points
- Keywords: "skyline", "silhouette", "outline", "maximum at each point"

### LC Examples
- LC 218 — The Skyline Problem (Hard) — Google, Amazon
- LC 850 — Rectangle Area II (Hard) — Google — area of union of rectangles
- LC 699 — Falling Squares (Hard) — Google — cumulative max height

### Rules & Steps
1. Build events: `(x, START, height)` and `(x, END, right)` for each building
2. Sort: by x; on tie **START before END** (opposite of Pattern 5 — prevents false drops)
3. Max-heap stores `{height, right}` — height is the key, right is needed for lazy deletion
4. For each event:
   - START → push `{height, right}` into heap
   - END → do nothing (lazy deletion handles it)
   - After event: evict dead heap tops (`heap.peek().right <= current x`)
   - Current max = `heap.peek().height` (or 0 if empty)
   - If max changed from previous → emit `[x, newMax]`

### Do's
- START before END on tie — prevents incorrectly dropping to 0 when one building ends exactly where another starts
- Store `right` in heap element — needed to check if a building has ended during lazy deletion
- Always push ground level `{0, Integer.MAX_VALUE}` into heap initially — simplifies empty-heap check
- Emit `[x, 0]` at the end of the last building naturally (heap empties → max = 0)

### Don'ts
- Don't eagerly remove buildings from the heap — lazy deletion is what makes this O(n log n)
- Don't emit a point unless the max actually changed — consecutive same-height events produce no output
- Don't use Pattern 5's END-before-START tie-break here — skyline needs the opposite

### Template — Sample 1: Eager Eviction (LC 218)
> On every END event, immediately remove the building from the heap.
> Uses `heap.remove()` which is O(n) per call — making worst case O(n²).
> Simpler to reason about but not optimal for large inputs.

```java
public List<List<Integer>> getSkyline(int[][] buildings) {
    List<int[]> events = new ArrayList<>();
    for (int[] b : buildings) {
        // Store x which can be start/end time, height, if its start or end, and the endTime for this interval
        events.add(new int[]{b[0], b[2], 0, b[1]});  // {x, height, start=0, endTime}
        events.add(new int[]{b[1], b[2], 1, b[1]});  // {x, height, end=1,   endTime}
    }

    // Sort: by x; on tie START(0) before END(1); among STARTs taller first
    events.sort((a, b) -> {
        if (a[0] != b[0]) return a[0] - b[0];      // sort by x
        if (a[2] != b[2]) return a[2] - b[2];      // START(0) before END(1)
        if (a[2] == 0)    return b[1] - a[1];      // among STARTs: taller first
        return 0;
    });

    // Max-heap: based on height
    PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
    List<List<Integer>> res = new ArrayList<>();
    int prevMax = 0;

    for (int[] e : events) {
        int currentTime = e[0], height = e[1], currentEndTime = e[3];
        boolean isStart = e[2] == 0;

        if (isStart) {
            heap.offer(e);                          // START: add building to heap
        } else {
            // EAGER: immediately find and remove this building from heap
            // heap.remove() scans the entire heap — O(n) per call
            heap.removeIf(el -> el[1] == height && el[3] == currentEndTime);
        }

        // Current max = top of heap after eager removal
        int currentMax = heap.isEmpty() ? 0 : heap.peek()[1];
        if (currentMax != prevMax) {
            res.add(List.of(currentTime, currentMax));
            prevMax = currentMax;
        }
    }

    return res;
    // Time: O(n²) worst case due to heap.removeIf()   Space: O(n)
}
```

### Template — Sample 2: Lazy Eviction (LC 218)
> END events are sweep points only — nothing is removed immediately.
> Dead buildings accumulate in the heap and are evicted only when they
> surface to the top during the eviction loop after each event.
> O(n log n) amortized — each building is pushed once and popped once.

```java
public List<List<Integer>> getSkyline(int[][] buildings) {
    List<int[]> events = new ArrayList<>();
    for (int[] b : buildings) {
        // Store x which can be start/end time, height, if its start or end, and the endTime for this interval
        events.add(new int[]{b[0], b[2], 0, b[1]});  // {x, height, start=0, endTime}
        events.add(new int[]{b[1], b[2], 1, b[1]});  // {x, height, end=1,   endTime}
    }

    // Sort: by x; on tie START(0) before END(1); among STARTs taller first
    events.sort((a, b) -> {
        if (a[0] != b[0]) return a[0] - b[0];      // sort by x
        if (a[2] != b[2]) return a[2] - b[2];      // START(0) before END(1)
        if (a[2] == 0)    return b[1] - a[1];      // among STARTs: taller first
        return 0;
    });

    // Max-heap: based on height
    PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
    List<List<Integer>> res = new ArrayList<>();
    int prevMax = 0;

    for (int[] e : events) {
        int currentTime = e[0], height = e[1], currentEndTime = e[3];
        boolean isStart = e[2] == 0;

        // offer the element to the heap
        // If its a START offer it, so only starts are added to the heap
        if (isStart) heap.offer(e);

        // LAZY: END events do nothing here — the building stays in the heap
        // Dead buildings are purged only when they reach the top

        // purge any start interval whose end is not relevant anymore
        // A building is dead if its endTime <= current x position
        while (!heap.isEmpty() && heap.peek()[3] <= currentTime)
            heap.poll();

        // Current heap top = tallest live building
        int currentMax = heap.isEmpty() ? 0 : heap.peek()[1];
        if (currentMax != prevMax) {
            res.add(List.of(currentTime, currentMax));
            prevMax = currentMax;
        }
    }

    return res;
    // Time: O(n log n) amortized — each building pushed once, popped once
    // Space: O(n)
}
```

---

## Quick Reference — Pattern Decision Tree

```
Does the problem have a separate queries[] array?
  YES → Pattern 6 (Offline Query + Heap)
  NO  ↓

Do intervals carry a VALUE (height, weight) and you need running MAX?
  YES → Pattern 7 (Skyline / Height Sweep)
  NO  ↓

Do you need to COUNT concurrent active intervals?
  YES → Pattern 3 (Min Rooms) or Pattern 5 (Sweep Line)
        Use Pattern 3 when you need per-slot state or assignment
        Use Pattern 5 when you just need the peak count or gap detection
  NO  ↓

Are you inserting ONE interval into an already-sorted list?
  YES → Pattern 2 (Insert & Merge)
  NO  ↓

Are you selecting / removing intervals to eliminate overlaps?
  YES → Pattern 4 (Greedy — sort by END)
  NO  ↓

Are you consolidating a messy list of intervals?
  YES → Pattern 1 (Merge — sort by START)
```

---

## Tie-Breaking Cheat Sheet

| Pattern | Same-x tie-break | Why |
|---|---|---|
| Pattern 4 (Greedy) | END before START | Back-to-back intervals (touch at point) should NOT count as overlapping |
| Pattern 5 (Sweep) | END (-1) before START (+1) | A meeting ending at t=5 frees a room before the t=5 meeting takes it |
| Pattern 6 (Query) | No tie-break needed | Queries are points, not intervals |
| Pattern 7 (Skyline) | START before END | Prevents false height-drop when one building ends where another starts |

---

*All solutions in Java. Time complexities assume comparison-based sort O(n log n).*
