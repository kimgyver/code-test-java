public class TestCharacterDiff {
    public static boolean have1DifferentCharacter(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false; // 길이가 다르면 다른 문자 1개로 바꿀 수 없음
        }

        int diffCount = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                diffCount++;
                if (diffCount > 1) {
                    return false; // 다른 문자가 2개 이상이면 false
                }
            }
        }
        return diffCount == 1; // 다른 문자가 정확히 1개이면 true
    }

    public static boolean have1CharacterMoreOrLess(String str1, String str2) {
        if (Math.abs(str1.length() - str2.length()) != 1) {
            return false; // 길이 차이가 1이 아니면 false
        }

        String longer = str1.length() > str2.length() ? str1 : str2;
        String shorter = str1.length() > str2.length() ? str2 : str1;

        int diffCount = 0;
        int j = 0;

        for (int i = 0; i < longer.length(); i++) {
            if (j < shorter.length() && longer.charAt(i) == shorter.charAt(j)) {
                j++;
            } else {
                diffCount++;
                if (diffCount > 1) {
                    return false; // 다른 문자가 2개 이상이면 false
                }
            }
        }
        return true; // 다른 문자가 정확히 1개이면 true
    }

    public static void main(String[] args) {
        // 테스트 케이스
        System.out.println(have1DifferentCharacter("abc", "abd")); // true
        System.out.println(have1DifferentCharacter("abc", "ab")); // false
        System.out.println(have1DifferentCharacter("abc", "abcd")); // false
        System.out.println(have1DifferentCharacter("abc", "xyz")); // false

        System.out.println(have1CharacterMoreOrLess("abc", "ab")); // true
        System.out.println(have1CharacterMoreOrLess("abc", "abcd")); // true
        System.out.println(have1CharacterMoreOrLess("abc", "xyz")); // false
        System.out.println(have1CharacterMoreOrLess("abc", "a")); // false
    }
}
