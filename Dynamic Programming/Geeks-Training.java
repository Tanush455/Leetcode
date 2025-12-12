/**
 * Solution.java
 *
 * Multiple approaches to the Maximum Points (Ninja Training) problem:
 * - Pure recursion (exponential time)
 * - Memoization (top-down DP)
 * - Tabulation (bottom-up DP)
 * - Space-optimized tabulation (O(m) space)
 *
 * Problem (short):
 * You are given a matrix arr[][] of size n x m where arr[day][activity]
 * represents the points earned by performing a specific activity on that day.
 * You must choose exactly one activity per day, but you cannot perform the
 * same activity on two consecutive days.
 *
 * Goal:
 * Maximize the total points over all days while ensuring the activity chosen
 * on any day is different from the one chosen the previous day.
 *
 * Key implementation detail:
 * The parameter 'last' represents the activity performed on the previous day.
 * Since 'last' can be -1 for the first day, we map it to index m in the DP
 * tables. Therefore DP dimensions use (m + 1) columns.
 *
 * Approaches:
 * - Recursion explores all valid choices day by day.
 * - Memoization caches results for states (day, last).
 * - Tabulation builds a DP table from the last day upward.
 * - Space optimization replaces the 2D DP with two 1D arrays.
 *
 * Time & Space Complexity:
 * - Recursion:        O(m^n) time,   O(n) stack
 * - Memoization:      O(n * m^2) time, O(n * m) space
 * - Tabulation:       O(n * m^2) time, O(n * m) space
 * - Space optimized:  O(n * m^2) time, O(m) space
 */

class Solution {
    public int solveUsingRecursion(int[][] arr, int day, int last, int n, int m) {
        if (day == n - 1) {
            int maxi = Integer.MIN_VALUE;
            for (int i = 0; i < m; i++) {
                if (i != last) {
                    maxi = Math.max(maxi, arr[n - 1][i]);
                }
            }
            return maxi;
        }

        int val = Integer.MIN_VALUE;

        for (int i = 0; i < m; i++) { // Changed n to m in loop condition
            if (i != last) {
                int sum = arr[day][i] + solveUsingRecursion(arr, day + 1, i, n, m);
                val = Math.max(val, sum);
            }
        }

        return val;
    }

    public int solveUsingMemo(int[][] arr, int day, int last, int n, int m, int[][] dp) {
        if (day == n - 1) {
            int maxi = Integer.MIN_VALUE;
            for (int i = 0; i < m; i++) {
                if (i != last) {
                    maxi = Math.max(maxi, arr[n - 1][i]);
                }
            }
            return maxi;
        }

        if (dp[day][last + 1] != -1) { // Adjusted index for 'last'
            return dp[day][last + 1];
        }

        int val = Integer.MIN_VALUE;

        for (int i = 0; i < m; i++) { // Changed n to m in loop condition
            if (i != last) {
                int sum = arr[day][i] + solveUsingMemo(arr, day + 1, i, n, m, dp); // Changed to solveUsingMemo
                val = Math.max(val, sum);
            }
        }

        dp[day][last + 1] = val; // Adjusted index for 'last'
        return dp[day][last + 1];
    }

    public int solveUsingTabulation(int[][] arr, int n, int m) {
        int[][] dp = new int[n][m + 1]; // Size m+1 to accommodate 'last' as -1 (mapped to index m)

        // Base case: Fill the last row (n-1) of DP table
        for (int lastActivity = 0; lastActivity <= m; lastActivity++) {
            int maxi = Integer.MIN_VALUE;
            for (int currentActivity = 0; currentActivity < m; currentActivity++) {
                if (currentActivity != lastActivity) {
                    maxi = Math.max(maxi, arr[n - 1][currentActivity]);
                }
            }
            dp[n - 1][lastActivity] = maxi;
        }

        // Fill the DP table from n-2 down to 0
        for (int day = n - 2; day >= 0; day--) {
            for (int lastActivity = 0; lastActivity <= m; lastActivity++) {
                int maxPoints = Integer.MIN_VALUE;
                for (int currentActivity = 0; currentActivity < m; currentActivity++) {
                    if (currentActivity != lastActivity) {
                        int currentPoints = arr[day][currentActivity] + dp[day + 1][currentActivity];
                        maxPoints = Math.max(maxPoints, currentPoints);
                    }
                }
                dp[day][lastActivity] = maxPoints;
            }
        }

        return dp[0][m]; // Result for day 0, with 'last' as -1 (mapped to index m)
    }

    public int solveUsingSpaceOptimization(int[][] arr, int n, int m) {
        int[] prevDp = new int[m + 1]; // For previous day's results

        // Base case: Fill for the last day (n-1)
        for (int lastActivity = 0; lastActivity <= m; lastActivity++) {
            int maxi = Integer.MIN_VALUE;
            for (int currentActivity = 0; currentActivity < m; currentActivity++) {
                if (currentActivity != lastActivity) {
                    maxi = Math.max(maxi, arr[n - 1][currentActivity]);
                }
            }
            prevDp[lastActivity] = maxi;
        }

        // Iterate from n-2 down to 0
        for (int day = n - 2; day >= 0; day--) {
            int[] currentDp = new int[m + 1]; // For current day's results
            for (int lastActivity = 0; lastActivity <= m; lastActivity++) {
                int maxPoints = Integer.MIN_VALUE;
                for (int currentActivity = 0; currentActivity < m; currentActivity++) {
                    if (currentActivity != lastActivity) {
                        int currentPoints = arr[day][currentActivity] + prevDp[currentActivity];
                        maxPoints = Math.max(maxPoints, currentPoints);
                    }
                }
                currentDp[lastActivity] = maxPoints;
            }
            prevDp = currentDp; // Update prevDp to currentDp for the next iteration
        }

        return prevDp[m]; // Result for day 0, with 'last' as -1 (mapped to index m)
    }


    public int maximumPoints(int arr[][]) {
        int n = arr.length;
        int m = arr[0].length; // Assuming m will always be 3 for this problem context

        // Uncomment the desired solution approach:

        // return solveUsingRecursion(arr, 0, -1, n, m); // Initial call with day 0 and last activity -1

        // Memoization
        // int[][] dp = new int[n][m + 1]; // last + 1 because 'last' can be -1, mapping to index m
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j <= m; j++) {
        //         dp[i][j] = -1;
        //     }
        // }
        // return solveUsingMemo(arr, 0, -1, n, m, dp);

        // Tabulation
        // return solveUsingTabulation(arr, n, m);

        // Space Optimized Tabulation
        return solveUsingSpaceOptimization(arr, n, m);
    }
}