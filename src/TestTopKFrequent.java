import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
                .collect(Collectors.toList());
//                .toList();
        // difference b/w .collect(Collectors.toList()) and .toList()
        // .toList() is available from Java 16 onwards and returns an unmodifiable list
        // 그 애기는 .collect()를 더 일반적으로 쓸 수 있다는 거네?
        // .collect(Collectors.toList())는 Java 8부터 사용 가능하며, 반환된 리스트는 수정 가능
        // 따라서, Java 16 이상을 사용하고 반환된 리스트를 수정할 필요가 없다면 .toList()를 사용하는 것이 더 간단할 수 있습니다.
        // stream() 메서드는 Java 8부터 도입되었으며, 컬렉션이나 배열에서 스트림을 생성하는 데 사용됩니다.

        // 윗 부분 코드를 람다식 없이 작성하면 아래와 같습니다.
//        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(frequencyMap.entrySet());
//        entryList.sort(new Comparator<Map.Entry<Integer, Integer>>() {
//            @Override
//            public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) {
//                return e2.getValue().compareTo(e1.getValue()); // 내림차순 정렬
//            }
//        });
//        List<Integer> result = new ArrayList<>();
//        for (int i = 0; i < k && i < entryList.size(); i++) {
//            result.add(entryList.get(i).getKey());
//        }
//        return result;

    }

    public static void main(String[] args) {
        TestTopKFrequent test = new TestTopKFrequent();
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        List<Integer> result = test.getResult(nums, k);
        System.out.println(result); // Should print the top k frequent elements
    }
}
