class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] result = new int[n];
        int level = 0;

        for (int i = 0; i < n; i++) {
            if (seq.charAt(i) == '(') {
                level++;
                result[i] = level & 1;
            } else {
                result[i] = level & 1;
                level--;
            }
        }

        return result;
    }
}