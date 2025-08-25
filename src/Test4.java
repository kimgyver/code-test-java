import java.util.Arrays;

public class Test4 {
    public static long countOfVowels(String input) {
        // 문자열에서 모음의 개수를 세는 함수
        // Input: "Hello World"
        // Output: 3
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }

        long count = 0;
        String vowels = "aeiouAEIOU";

        for (char c : input.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }

    public static boolean isPangram(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        input = input.toLowerCase();

        for (char c : alphabet.toCharArray()) {
            if (input.indexOf(c) == -1) {
                return false; // 알파벳이 하나라도 빠져있으면 false
            }
        }
        return true; // 모든 알파벳이 포함되어 있으면 true
    }

    private static String stringCompression(String input)
    // 문자열 압축
    // Input: "AAbbbccddddd"
    // Output: "A2b3c2d5"
    // Input: "aaabbbcc"
    // Output: "a3b3c2"
    // Input: "abc"
    // Output: "abc"
    {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }

        StringBuilder compressed = new StringBuilder();
        char currentChar = input.charAt(0);
        int count = 1;

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == currentChar) {
                count++;
            } else {
                compressed.append(currentChar);
                if (count > 1) {
                    compressed.append(count);
                }
                currentChar = input.charAt(i);
                count = 1;
            }
        }

        // Append the last character and its count
        compressed.append(input.charAt(input.length() - 1));
        if (count > 1) {
            compressed.append(count);
        }

        return compressed.toString();
    }

    public static int[] rotateArrayToRight(int[] input, int k)
    // 배열을 오른쪽으로 k번 회전
    // Input: [1, 2, 3, 4, 5, 6, 7], k = 3
    // Output: [5, 6, 7, 1, 2, 3, 4]
    // Input: [1, 2, 3, 4, 5], k = 2
    // Output: [4, 5, 1, 2, 3]
    // Input: [1, 2, 3], k = 0
    // Output: [1, 2, 3]
    {
        if (input == null || input.length == 0 || k < 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        int n = input.length;
        k = k % n; // Handle cases where k is greater than n

        if (k == 0) {
            return input; // No rotation needed
        }

        int[] leftPart = Arrays.copyOfRange(input, 0, n - k);
        int[] rightPart = Arrays.copyOfRange(input, n - k, n);

        int[] rotatedArray = new int[n];
        System.arraycopy(rightPart, 0, rotatedArray, 0, k);
        System.arraycopy(leftPart, 0, rotatedArray, k, n - k);
        return rotatedArray;
    }

    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        // 두 개의 정렬된 배열을 병합
        // Input: arr1 = [1, 3, 5], arr2 = [2, 4, 6]
        // Output: [1, 2, 3, 4, 5, 6]
        if (arr1 == null || arr2 == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        int n1 = arr1.length;
        int n2 = arr2.length;
        int[] mergedArray = new int[n1 + n2];

        int i = 0, j = 0, k = 0;

        while (i < n1 && j < n2) {
            if (arr1[i] <= arr2[j]) {
                mergedArray[k++] = arr1[i++];
            } else {
                mergedArray[k++] = arr2[j++];
            }
        }

        while (i < n1) {
            mergedArray[k++] = arr1[i++];
        }

        while (j < n2) {
            mergedArray[k++] = arr2[j++];
        }

        return mergedArray;
    }

    public static void main(String[] args) {
        // Example usage of the methods
        String input = "Hello World";
        System.out.println("Count of vowels: " + countOfVowels(input));

        String pangramInput = "The quick brown fox jumps over the lazy dog";
        System.out.println("Is pangram: " + isPangram(pangramInput));

        String compressionInput = "AAbbbccddddd";
        System.out.println("String compression: " + stringCompression(compressionInput));

        int[] rotateInput = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        System.out.println("Rotated array: " + Arrays.toString(rotateArrayToRight(rotateInput, k)));

        int[] arr1 = {1, 3, 5};
        int[] arr2 = {2, 4, 6};
        System.out.println("Merged sorted arrays: " + Arrays.toString(mergeSortedArrays(arr1, arr2)));
    }
}
