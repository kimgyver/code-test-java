import java.util.ArrayList;
import java.util.List;

public class TestMergeIntervals {
    public static List<int[]> merge(List<int[]> intervals) {
        if (intervals == null || intervals.isEmpty()) {
            return intervals;
        }

        // Sort the intervals based on the start time
        intervals.sort((a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        int[] currentInterval = intervals.get(0);

        for (int i = 1; i < intervals.size(); i++) {
            int[] nextInterval = intervals.get(i);
            // If the current interval overlaps with the next interval
            if (currentInterval[1] >= nextInterval[0]) {
                // Merge them by updating the end time
                currentInterval[1] = Math.max(currentInterval[1], nextInterval[1]);
            } else {
                // No overlap, add the current interval to the result
                merged.add(currentInterval);
                currentInterval = nextInterval; // Move to the next interval
            }
        }
        // Add the last processed interval
        merged.add(currentInterval);

        return merged;
    }

    public static void main(String[] args) {
        List<int[]> intervals = new ArrayList<>();
        intervals.add(new int[]{1, 3});
        intervals.add(new int[]{2, 6});
        intervals.add(new int[]{8, 10});
        intervals.add(new int[]{15, 18});

        List<int[]> mergedIntervals = merge(intervals);
        for (int[] interval : mergedIntervals) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }
    }
}
