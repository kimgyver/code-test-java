import java.util.*;
import java.util.stream.Collectors;

public class Test1 {
    private static String mostFrequentWord(String input) {
        String[] words = input.split("\\s+");
        Map<String, Integer> wordCount = new HashMap<>();

        for (String word : words) {
            word = word.toLowerCase();
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        return wordCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    private static boolean isAnagram(String input1, String input2) {
        if (input1.length() != input2.length()) {
            return false;
        }

        // using map to count character occurrences
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char c : input1.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }
        for (char c : input2.toCharArray()) {
            if (!charCountMap.containsKey(c) || charCountMap.get(c) == 0) {
                return false;
            }
            charCountMap.put(c, charCountMap.get(c) - 1);
        }
        // check if all counts are zero
        for (int count : charCountMap.values()) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    private static String reverseEachWordInAString(String input) {
        String[] words = input.split("\\s+");
        StringBuilder reversedString = new StringBuilder();

        for (String word : words) {
            StringBuilder reversedWord = new StringBuilder(word);
            reversedString.append(reversedWord.reverse().toString()).append(" ");
        }

        return reversedString.toString().trim();
    }

    private static char firstNonRepeatedCharacter(String input) {
        for (int index = 0; index < input.length(); index++)
        {
            if (input.lastIndexOf(input.charAt(index)) == index)
                return input.charAt(index);
        }
        return '\0'; // return null character if no non-repeated character found
    }

    private static List<Integer> findDuplicateNumbers(List<Integer> input) {
//        Map<Integer, Integer> numberCount = new HashMap<>();
        Set<Integer> duplicates = new HashSet<>();

        // using lastIndexOf to find duplicates
        for (int i = 0; i < input.size(); i++) {
            if (i != input.lastIndexOf(input.get(i))) {
                duplicates.add(input.get(i));
            }
        }

        return duplicates.stream()
                .collect(Collectors.toList());
    }

    private static boolean isPalindromeNumber(int input) {
        String inputString = String.valueOf(input);
        int length = inputString.length();

        for (int i = 0; i < length / 2; i++) {
            if (inputString.charAt(i) != inputString.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    private static int secondLargestNumber(List<Integer> input) {
        if (input.size() < 2) {
            throw new IllegalArgumentException("List must contain at least two elements.");
        }

        int firstLargest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int number : input) {
            if (number > firstLargest) {
                secondLargest = firstLargest;
                firstLargest = number;
            } else if (number > secondLargest && number != firstLargest) {
                secondLargest = number;
            }
        }

        if (secondLargest == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("No second largest number found.");
        }

        return secondLargest;
    }

    private static boolean isBalancedParentheses(String input) {
        Map<Character, Character> pairs = new HashMap<>();
        pairs.put('(', ')');
        pairs.put('{', '}');
        pairs.put('[', ']');

        Stack<Character> stack = new Stack<>();
        for (char ch : input.toCharArray()) {
            if (pairs.containsKey(ch)) {
                stack.push(ch); // Push opening brackets onto the stack
            } else if (pairs.containsValue(ch)) {
                if (stack.isEmpty() || pairs.get(stack.pop()) != ch) {
                    return false; // Mismatched or unbalanced closing bracket
                }
            }
        }
        return stack.isEmpty(); // Ensure no unmatched opening brackets remain
    }

    private static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        return frequencyMap.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private static boolean isValidInvoicCode(String code) {
        // 정확히 3개의 대문자 로 시작합니다
        // 정확히 4자리 숫자가 뒤따릅니다.
        // 선택적으로 "-NZ" 로 끝남
        // 유효한 코드의 예:
        // INV1234, ABC5678-NZ
        // 유효하지 않은 코드의 예:
        // AB1234, abc5678,XYZ12-NZ

        String regex = "^[A-Z]{3}\\d{4}(-NZ)?$";
        return code.matches(regex);
    }

    public static void main(String[] args) {
        // Test cases for each method
        System.out.println("Most Frequent Word: " + mostFrequentWord("This is a test. This test is only a test."));
        System.out.println("Is Anagram: " + isAnagram("listen", "silent"));
        System.out.println("Reverse Each Word: " + reverseEachWordInAString("Hello World"));
        System.out.println("First Non-Repeated Character: " + firstNonRepeatedCharacter("swiss"));
        System.out.println("Duplicate Numbers: " + findDuplicateNumbers(Arrays.asList(1, 2, 3, 2, 4, 5, 1)));
        System.out.println("Is Palindrome Number: " + isPalindromeNumber(12321));
        System.out.println("Second Largest Number: " + secondLargestNumber(Arrays.asList(10, 20, 30, 20, 40)));
        System.out.println("Is Balanced Parentheses: " + isBalancedParentheses("{[()]}"));
        System.out.println("Top K Frequent: " + topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2));
        System.out.println("Is Valid Invoice Code: " + isValidInvoicCode("INV1234-NZ"));
    }
}
