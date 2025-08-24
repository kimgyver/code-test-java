import java.util.*;

public class Test2 {

    public static List<Integer> fibonacci(int n) {
        if (n == 0 || n < 0) {
            return Collections.emptyList();
        }
        List<Integer> fibList = new ArrayList<>();
        fibList.add(0);
        fibList.add(1);

        for (int i = 2; i < n; i++) {
            int nextFib = fibList.get(i - 1) + fibList.get(i - 2);
            fibList.add(nextFib);
        }
        return fibList;
    }

    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Negative numbers do not have a factorial.");
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static List<String> uniqueElements(List<String> input) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < input.size(); i ++) {
            if (input.lastIndexOf(input.get(i)) == i) {
                result.add(input.get(i));
            }
        }
        return result;
    }

    public static long countOfVowel(String input) {
        long count = 0;
        char[] inputArray = input.toUpperCase().toCharArray();
        for (char ch : inputArray) {
            if ("AEIOU".indexOf(ch) >= 0) {
                count += 1;
            }
        }
        return count;
    }

    public static List<Integer> generatePrimeNumbers(int n) {
        List<Integer> primeNumbers = new ArrayList<>();
        for (int i = 2; i < n; i ++) {
            boolean isPrimeNumber = true;
            for (int j = 2; j < i / 2; j ++) {
                if (i % j == 0 && i != j) {
                    isPrimeNumber = false;
                    break;
                }
            }
            if (isPrimeNumber) primeNumbers.add(i);
        }
        return primeNumbers;
    }

    public static String rewriteStringToCharCount(String input)
    // Returns a string that contains each character and its count in the input string
    // strings should be sorted alphabetically
    // 예를 들어, "Hello World"가 주어지면 "d1e1h1l3o2r1w1"을 반환합니다.
    {
        Map<Character, Integer> charCountMap = new TreeMap<>(); // ordered by character
        for (char ch : input.toLowerCase().toCharArray()) {
            if (Character.isLetter(ch)) {
                charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
            }
        }
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            result.append(entry.getKey()).append(entry.getValue());
        }
        return result.toString();
    }

    // Returns the common elements between two lists.
    // 예를 들어, [1, 2, 3]과 [2, 3, 4]가 주어지면 [2, 3]을 반환합니다
    public static List<Integer> commonElements(List<Integer> a, List<Integer> b) {
        List<Integer> commonElements = new ArrayList<>();
        for (Integer element : a) {
            if (b.contains(element)) {
                commonElements.add(element);
            }
        }
        return commonElements;
    }

    public static String caesarCipher(String input, int shift) {
        StringBuilder result = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                int shifted = (ch - base + shift) % 26 + base;
                result.append((char) shifted);
            } else {
                result.append(ch); // Non-letter characters are not shifted
            }
        }
        return result.toString();
    }

    // Reverses the vowels in a string.
    // For example, "hello" becomes "holle", and "leetcode" becomes "leotcede".
    // For example, "aeiou" becomes "uoiea".
    public static String reverseVowels(String s) {
        String vowels = "aeiouAEIOU";
        Stack<Character> vowelStack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (vowels.indexOf(ch) != -1) {
                vowelStack.push(ch);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (vowels.indexOf(ch) != -1) {
                s = s.substring(0, i) + vowelStack.pop() + s.substring(i + 1);
            }
        }
        return s;
    }

    public static String reverseWords(String s) {
        String[] words = s.split(" ");
        StringBuilder reversed = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]);
            if (i != 0) {
                reversed.append(" ");
            }
        }
        return reversed.toString();
    }

    public static int[] productExceptSelf(int[] nums)
    // 주어진 배열에서 각 요소를 제외한 나머지 요소들의 곱을 포함하는 배열을 반환합니다.
    // 예를 들어, [1, 2, 3, 4]가 주어지면 [24, 12, 8, 6]을 반환합니다.
    {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int product = 1;
            for (int j = 0; j < nums.length; j++) {
                if (i != j) {
                    product *= nums[j];
                }
            }
            result[i] = product;
        }
        return result;
    }

    // Checks if there exists a triplet (i, j, k) such that nums[i] < nums[j] < nums[k] and i < j < k.
    // 주어진 배열에서 i < j < k를 만족하는 nums[i] < nums[j] < nums[k]인 삼중항이 존재하는지 확인합니다.
    // 예를 들어, [1, 2, 3, 4, 5]가 주어지면 (1, 2, 3)이라는 유효한 삼중항이 존재하므로 true를 반환합니다.
    // [5, 4, 3, 2, 1]이 주어지면 유효한 삼중항이 없으므로 false를 반환합니다.
    // 이 함수는 O(n) 시간 복잡도로 실행되어야 합니다.
    // 입력 배열이 비어 있거나 3개 미만의 요소를 가지면 false를 반환해야 합니다.
    // 예를 들어, []가 주어지면 false를 반환합니다.
    // [1, 2]가 주어지면 false를 반환합니다.
    // [1, 2, 3]이 주어지면 true를 반환합니다. (1, 2, 3)이 유효한 삼중항입니다.
    public static boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false; // Not enough elements for a triplet
        }

        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= first) {
                first = num; // Update the smallest number
            } else if (num <= second) {
                second = num; // Update the second smallest number
            } else {
                return true; // Found a number greater than both first and second
            }
        }
        return false; // No increasing triplet found
    }

    // 주어진 문자 배열을 압축하고 새로운 길이를 반환합니다.
    // 예를 들어, ['a', 'a', 'b', 'b', 'c', 'c', 'c']가 주어지면, 배열을 ['a', '2', 'b', '2', 'c', '3']로 수정하고 6을 반환합니다.
    // 입력 배열은 제자리에서 수정되므로 원래 배열이 변경됩니다.
    // 압축된 문자열이 원래 문자열보다 길면 원래 문자열은 변경되지 않습니다.
    // 예를 들어, ['a', 'b', 'c']가 주어지면 3을 반환하고 배열은 변경되지 않습니다.
    // 입력 배열이 비어 있으면 0을 반환하고 배열은 변경되지 않습니다.
    public static int compress(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0; // Return 0 for empty input
        }

        int writeIndex = 0; // Index to write compressed characters
        int readIndex = 0; // Index to read characters

        while (readIndex < chars.length) {
            char currentChar = chars[readIndex];
            int count = 0;

            // Count occurrences of the current character
            while (readIndex < chars.length && chars[readIndex] == currentChar) {
                readIndex++;
                count++;
            }

            // Write the character and its count if greater than 1
            chars[writeIndex++] = currentChar;
            if (count > 1) {
                for (char digit : String.valueOf(count).toCharArray()) {
                    chars[writeIndex++] = digit;
                }
            }
        }

        return writeIndex; // Return the new length of the compressed array
    }

    // 주어진 두 배열에서 서로 다른 요소를 찾아 반환합니다.
    // 예를 들어, [1, 2, 3]과 [2, 3, 4]가 주어지면 [1]과 [4]를 반환합니다.
    // [1, 2, 3]과 [1, 2, 3]가 주어지면 빈 배열을 반환합니다.
    // [1, 2, 3]과 []가 주어지면 [1, 2, 3]을 반환합니다.
    // []와 []가 주어지면 빈 배열을 반환합니다.
    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();

        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }

        List<Integer> uniqueInNums1 = new ArrayList<>();
        List<Integer> uniqueInNums2 = new ArrayList<>();

        for (int num : set1) {
            if (!set2.contains(num)) {
                uniqueInNums1.add(num);
            }
        }
        for (int num : set2) {
            if (!set1.contains(num)) {
                uniqueInNums2.add(num);
            }
        }

        result.add(uniqueInNums1);
        result.add(uniqueInNums2);
        return result;
    }

    // Checks if the number of occurrences of each element in the array is unique.
    // 주어진 배열에서 각 요소의 발생 횟수가 고유한지 확인합니다.
    // 예를 들어, [1, 2, 2, 1, 1, 3]가 주어지면 1은 3번, 2는 2번, 3은 1번 발생하므로 true를 반환합니다.
    // [1, 2, 2, 3, 3]가 주어지면 1은 1번, 2는 2번, 3은 2번 발생하므로 false를 반환합니다.
    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> occurrenceMap = new HashMap<>();
        for (int num : arr) {
            occurrenceMap.put(num, occurrenceMap.getOrDefault(num, 0) + 1);
        }

        Set<Integer> occurrences = new HashSet<>();
        for (int count : occurrenceMap.values()) {
            if (!occurrences.add(count)) {
                return false; // If the count is already in the set, it's not unique
            }
        }
        return true; // All counts are unique
    }

    // 주어진 문자열에서 별표(*)를 제거하고, 별표가 나타날 때마다 이전 문자를 제거합니다.
    // 예를 들어, "leet**cod*e"가 주어지면 "lecoe"를 반환합니다.
    // "erase*****"가 주어지면 ""를 반환합니다.
    // "a*b*c"가 주어지면 "c"를 반환합니다.
    // "abc"가 주어지면 "abc"를 반환합니다.
    public static String removeStars(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '*') {
                if (!stack.isEmpty()) {
                    stack.pop(); // Remove the last character if '*' is encountered
                }
            } else {
                stack.push(ch); // Push the character onto the stack
            }
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop()); // Build the result from the stack
        }
        return result.reverse().toString(); // Reverse the result to maintain original order
    }

    public static String RemoveNonAlphanumeric(String input)
    // 주어진 문자열에서 알파벳과 숫자를 제외한 모든 문자를 제거합니다.
    // 예를 들어, "abc123!@#"가 주어지면 "abc123"을 반환합니다.
    // "Hello, World!"가 주어지면 "HelloWorld"를 반환합니다.
    // "1234567890"가 주어지면 "1234567890"을 반환합니다.
    {
        StringBuilder result = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                result.append(ch); // Append only alphanumeric characters
            }
        }
        return result.toString(); // Return the cleaned string
    }

    public static void main(String[] args) {
        // Example usage of the methods
        System.out.println("Fibonacci: " + fibonacci(10));
        System.out.println("Factorial: " + factorial(5));
        System.out.println("Unique Elements: " + uniqueElements(Arrays.asList("apple", "banana", "apple", "orange")));
        System.out.println("Count of Vowels: " + countOfVowel("Hello World"));
        System.out.println("Prime Numbers: " + generatePrimeNumbers(20));
        System.out.println("Rewrite String to Char Count: " + rewriteStringToCharCount("Hello World"));
        System.out.println("Common Elements: " + commonElements(Arrays.asList(1, 2, 3), Arrays.asList(2, 3, 4)));
        System.out.println("Caesar Cipher: " + caesarCipher("Hello", 3));
        System.out.println("Reverse Vowels: " + reverseVowels("hello"));
        System.out.println("Reverse Words: " + reverseWords("Hello World"));
        System.out.println("Product Except Self: " + Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));
        System.out.println("Increasing Triplet: " + increasingTriplet(new int[]{1, 2, 3, 4, 5}));
        char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        System.out.println("Compress: " + compress(chars) + ", Resulting Array: " + Arrays.toString(chars));
        System.out.println("Find Difference: " + findDifference(new int[]{1, 2, 3}, new int[]{2, 3, 4}));
        System.out.println("Unique Occurrences: " + uniqueOccurrences(new int[]{1, 2, 2, 1, 1, 3}));
        System.out.println("Remove Stars: " + removeStars("leet**cod*e"));
        System.out.println("Remove Non-Alphanumeric: " + RemoveNonAlphanumeric("abc123!@#"));
    }
}
