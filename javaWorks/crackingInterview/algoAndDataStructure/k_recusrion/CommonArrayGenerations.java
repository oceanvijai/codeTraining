public class CommonArrayGenerations {

    /**
     * Sub set with duplicates
     */

     // one way to Do is  "take or not take" , 
     // other way is the same as combinations, except we look for a sum shown below
    

    /**
     * Sub set without duplicates
     */

     // one way to Do is  "take or not take" , 
     // other way is the same as combinations, except we look for a sum shown below

    /**
     * combination with duplicates
     */

    /**
     * Combination without duplicates
     */

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        solve(candidates, new ArrayList<Integer>(), target, 0);
        return ans;
    }

    private void solve(int[] candidates, List<Integer> list, int target, int start) {
        if (target == 0) {
            ans.add(new ArrayList<Integer>(list));
        }

        if (target < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            list.add(candidates[i]);
            solve(candidates, list, target - candidates[i], i);
            list.remove(list.size() - 1);
        }
    }

    /**
     * Permutation with duplicates
     */

    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> A) {
        permute(A, 0);
        return ans;
    }

    private void permute(ArrayList<Integer> A, int swapIndex) {
        if (swapIndex == A.size()) {
            ans.add(new ArrayList<Integer>(A));
        }

        for (int i = swapIndex; i < A.size(); i++) {
            swap(A, swapIndex, i);
            permute(A, swapIndex + 1);
            swap(A, i, swapIndex);
        }
    }

    private void swap(ArrayList<Integer> A, int a, int b) {
        int tmp = A.get(a);
        A.set(a, A.get(b));
        A.set(b, tmp);
    }

    /**
     * Permutation without duplicates
     */
}