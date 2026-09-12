class Solution {
    public int[] maximumWeight(java.util.List<java.util.List<Integer>> intervals) {
        int n = intervals.size();
        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        java.util.Arrays.sort(a, (x, y) -> {
            if (x[1] != y[1]) return Integer.compare(x[1], y[1]);
            return Integer.compare(x[0], y[0]);
        });

        long[][] dp = new long[n + 1][5];
        int[][][] path = new int[n + 1][5][];

        for (int i = 1; i <= n; i++) {
            int j = prev(a, i - 1);

            for (int k = 1; k <= 4; k++) {
                long skip = dp[i - 1][k];
                long take = a[i - 1][2] + dp[j + 1][k - 1];

                if (take > skip) {
                    dp[i][k] = take;
                    path[i][k] = add(path[j + 1][k - 1], a[i - 1][3]);
                } else if (take < skip) {
                    dp[i][k] = skip;
                    path[i][k] = path[i - 1][k];
                } else {
                    dp[i][k] = take;

                    int[] x = add(path[j + 1][k - 1], a[i - 1][3]);
                    int[] y = path[i - 1][k];

                    path[i][k] = better(x, y);
                }
            }
        }

        return path[n][4] == null ? new int[0] : path[n][4];
    }

    int prev(int[][] a, int i) {
        int l = 0, r = i - 1;

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (a[m][1] < a[i][0])
                l = m + 1;
            else
                r = m - 1;
        }

        return r;
    }

    int[] add(int[] arr, int x) {
        int n = arr == null ? 0 : arr.length;
        int[] res = new int[n + 1];

        int p = 0;
        while (p < n && arr[p] < x) {
            res[p] = arr[p];
            p++;
        }

        res[p] = x;

        while (p < n) {
            res[p + 1] = arr[p];
            p++;
        }

        return res;
    }

    int[] better(int[] a, int[] b) {
        if (a == null) return b;
        if (b == null) return a;

        for (int i = 0; i < Math.min(a.length, b.length); i++) {
            if (a[i] != b[i])
                return a[i] < b[i] ? a : b;
        }

        return a.length <= b.length ? a : b;
    }
}