import java.util.Arrays;

public class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            } else {
                return Integer.compare(b[1], a[1]);
            }
        });

        int remainingCount = 0;
        int maxEnd = 0;

        for (int[] interval : intervals) {
            int currentEnd = interval[1];

            if (currentEnd > maxEnd) {
                remainingCount++;
                maxEnd = currentEnd;
            }
        }

        return remainingCount;
    }
}
