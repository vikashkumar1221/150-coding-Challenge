class Solution {
    public int totalNumbers(int[] digits) {

        int[] freq = new int[10];

        // Frequency of each digit
        for (int d : digits) {
            freq[d]++;
        }

        int count = 0;

        // Hundreds digit: 1-9 (0 allowed nahi)
        for (int i = 1; i <= 9; i++) {

            // Tens digit: 0-9
            for (int j = 0; j <= 9; j++) {

                // Units digit: even only
                for (int k = 0; k <= 8; k += 2) {

                    // Check required copies
                    int[] used = new int[10];
                    used[i]++;
                    used[j]++;
                    used[k]++;

                    boolean possible = true;

                    for (int d = 0; d <= 9; d++) {
                        if (used[d] > freq[d]) {
                            possible = false;
                            break;
                        }
                    }

                    if (possible) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}