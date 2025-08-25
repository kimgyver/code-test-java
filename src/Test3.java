import java.util.*;

public class Test3 {
    public static int findMessingNumber(int[] input) {
        int n = input.length;
        int expectedSum = (n + 1) * (n + 2) / 2; // Sum of first n natural numbers
        int arraySum = Arrays.stream(input).sum();
        return expectedSum - arraySum; // The missing number
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagramsMap = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);
            if (!anagramsMap.containsKey(key)) {
                anagramsMap.put(key, new ArrayList<>());
            }
            anagramsMap.get(key).add(str);
        }
        return new ArrayList<List<String>>(anagramsMap.values());
    }

    public static int maxSubArray(int[] nums) {
        // Kadane's algorithm to find the maximum subarray sum
        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int x, TreeNode left, TreeNode right) {
            this.value = x;
            this.left = left;
            this.right = right;
        }
    }

    public static boolean validateBinarySearchTree(TreeNode root) {
        return validateBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static boolean validateBST(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.value <= min || node.value >= max) return false;
        return validateBST(node.left, min, node.value) && validateBST(node.right, node.value, max);
    }

    // main
    public static void main(String[] args) {
        // Example usage of the methods
        int[] input = {1, 2, 4, 5};
        System.out.println("Missing number: " + findMessingNumber(input));

        String[] strs = {"flower", "flow", "flight"};
        System.out.println("Longest common prefix: " + longestCommonPrefix(strs));

        String[] anagrams = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("Grouped anagrams: " + groupAnagrams(anagrams));

        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Maximum subarray sum: " + maxSubArray(nums));

        // Example of a binary search tree validation
        TreeNode root = new Test3().new TreeNode(2,
            new Test3().new TreeNode(1, null, null),
            new Test3().new TreeNode(3, null, null));
        System.out.println("Is valid BST: " + validateBinarySearchTree(root));
    }
}
