import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestTopKFrequent {
    public List<Integer> getResult(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        return frequencyMap.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(k)
                .map(Map.Entry::getKey)
                .toList();
    }

    public static void main(String[] args) {
        TestTopKFrequent test = new TestTopKFrequent();
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        List<Integer> result = test.getResult(nums, k);
        System.out.println(result); // Should print the top k frequent elements
    }
}
