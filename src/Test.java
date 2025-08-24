import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {
    private static final int DEFAULT_MAX =  Integer.MIN_VALUE;// -999;

    public static void main(String[] args) {
        String line1 = "1 2 3 4 5";
        String line2 = "4 3 5 7 8";
        List<Integer> listA = new ArrayList<Integer>();
        List<Integer> listB = new ArrayList<Integer>();
        for (var num : line1.split(" "))
            listA.add(Integer.parseInt(num));
        for (var num : line2.split(" "))
            listB.add(Integer.parseInt(num));

        var commonElementsResult = commonElements(listA, listB);
        System.out.println("commonElementsResult => " + commonElementsResult);


        var charCountResult = getCharacterCount("Hello");
        System.out.println("charCountResult : " + charCountResult);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers.stream().reduce(numbers.get(0), Integer::max);
        System.out.println(sum);

        List<String> words = Arrays.asList("Hello", "World", "a", "AA", "Stream", "Win","Worldcup", "Ex-PM", "Example");
        Map<Object, Long> countMap = words.stream()
                .collect(
                        Collectors.groupingBy(a -> ((String)a).toUpperCase().charAt(0),
                                Collectors.counting())
                );
        System.out.println("count" + countMap);
        Map<Object, String> resultMap = words.stream()
                .collect(
                        Collectors.groupingBy(a -> ((String)a).toUpperCase().charAt(0),
                                Collectors.mapping(String::valueOf, Collectors.joining(" -- ", "[", "]")))
//                                Collectors.mapping(String::length, Collectors.summingInt(Integer::intValue)))
                );
        System.out.println("" + resultMap);


        // Calculate the average age of people whose names start with 'E'
//        double averageAge = people.stream()
//                .filter(person -> person.getName().startsWith("E"))
//                .collect(
//                        Collectors.mapping(Person::getAge, Collectors.averagingInt(Integer::intValue))
//                );
//
//        System.out.println("Average age of people whose names start with 'E': " + averageAge);


//        System.out.println(fibo(9));

//        System.out.println(frequency("1234ABCDAaaa123"));
//
//        System.out.println(countOfVowel("AHJKFKIIIKAKEEEADPPP"));
//
//        System.out.println(uniqueElements(List.of("1", "2", "3", "1", "11", "4", "11", "5")));
//
//        System.out.println(areAnagrams("AEIOU", "OIAEU"));
//
//        System.out.println(getMax(Arrays.asList(0,1,2,3,4,5,6,7)));
//        System.out.println(factorial(3));


        boolean a;
        a = isPalinedrom("abcdefg");
        System.out.println(a);
        a = isPalinedrom("abcdcba");
        System.out.println(a);

//        a  = isPalinedrom("1235321");
//        System.out.println(a);
//        a  = isPalinedrom("123321");
//        System.out.println(a);
//        a  = isPalinedrom("123321 ");
//        System.out.println(a);


//        List<Integer> primes = generatePrimeNumbers(50);
//        System.out.println(primes);
    }

    public static List<Integer> fibo(int n) {
        List<Integer> result = new ArrayList<>(Arrays.asList(0, 1));
        for (int i = 2; i < n; i++) {
            result.add(result.get(i - 1) + result.get(i - 2));
        }
        return result;
    }

    public static boolean isPalinedrom(String input) {
        int halfLength = input.length() / 2;
        int extraLength = input.length() % 2;   // 1/3/5 -> 1, 2/4/6 -> 0

        String firstHalf = input.substring(0, halfLength);
        String secondHalf = input.substring(halfLength + extraLength, halfLength + extraLength + halfLength);

        // reverse string using collection.reverser
//        List<String> secondList = Arrays.asList(secondHalf.split(""));
//        Collections.reverse(secondList);
//        String reversOfSecond = secondList.stream()
//                .collect(Collectors.joining());

        String reversOfSecond = reverseString(secondHalf);

        return firstHalf.equals(reversOfSecond);
    }

    private static String reverseString(String input) {
//        return IntStream.range(0, input.length())
//                .map(i ->input.length() - 1 - i)
//                .mapToObj(i -> input.charAt(i))
//                .map(String::valueOf)
//                .collect(Collectors.joining());
          return Stream.iterate(input.length() - 1, n -> n >= 0, n -> n -1)
                .map(i -> input.charAt(i))
                .map(String::valueOf)
                .collect(Collectors.joining());

//        return input.chars()
//                .mapToObj(ch -> (char)ch)
//                .collect(StringBuilder::new,
//                        (b, c) -> b.insert(0, (char)c), (b1, b2) -> b1.insert(0, b2))
//                .toString();

//        return input.chars()
//                .mapToObj(c -> (char)c)
//                .reduce("", (s,c) -> c+s, (s1,s2) -> s2+s1);


//        StringBuilder reversed = new StringBuilder();
//        for (int i = 0; i < input.length(); i++) {
//            reversed.insert(0, input.charAt(i));
//        }
//        return reversed.toString();
    }

    public static long factorial(int n) {
        return (n == 0 || n == 1) ? 1 : n * factorial(n - 1);
    }

    public static int getMax(List<Integer> numbers) {
        return numbers.stream()
                .reduce(numbers.size() > 0 ? numbers.get(0) : DEFAULT_MAX,
                        (acc, el) -> acc > el ? acc : el);
    }

    public static boolean areAnagrams(String a, String b) {
//        return a.chars().sorted().boxed().collect(Collectors.toList())
//                .equals(b.chars().sorted().boxed().collect(Collectors.toList()));

        var aChars = a.toCharArray();
        var bChars = b.toCharArray();
        Arrays.sort(aChars);
        Arrays.sort(bChars);
        return Arrays.equals(aChars, bChars);

//        var al = Stream.of(a.split(""));
//        var bl = Stream.of(b.split(""));
//        return al.sorted().collect(Collectors.toList())
//                .equals(bl.sorted().collect(Collectors.toList()));

    }

    public static List<String> uniqueElements(List<String> input) {
        return IntStream.range(0, input.size())
                .filter(i -> input.indexOf(input.get(i)) == i)
                .mapToObj(i -> input.get(i))
                .collect(Collectors.toList());

//        List<String> result = new ArrayList<>();
//        for(int i = 0; i < input.size(); i ++) {
//            if (input.indexOf(input.get(i)) == i) {
//                result.add(input.get(i));
//            }
//        }
//        return result;
    }

    public static long countOfVowel(String input) {
//        return input.toUpperCase().chars()
//                .mapToObj(ch -> String.valueOf((char) ch))
//                .peek(System.out::print)
//                .filter(ch -> "AEOIU".contains(ch)).count();

        var inputSplit = input.toUpperCase().split("");
        int count = 0;
        for (String ch : inputSplit) {
            if ("AEIOU".contains(ch)) count ++;
        }
        return count;
    }

    public static Map<Character, Integer> frequency(String input) {
//        return null;
//        input.chars()
//                .reduce({}, (acc, el) -> { acc[el] ? acc[el] ++ : (acc[el] = 1); })

        Map<Character, Integer> map = new HashMap<>();
        input.chars()
                .mapToObj(c -> (char) c)
                .forEach(ch -> map.put(ch, map.getOrDefault(ch, 0) + 1));
        return map;
    }


    public static List<Integer> generatePrimeNumbers(int n) {
        List<Integer> primeNumbers = new ArrayList<>();
        boolean isPrime;
        for (int i = 2; primeNumbers.size() < n; i++) {
            isPrime = true;
            for (int j = 2; j <= i / 2; j++) {
                if (i != j && i % j == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    public static String getCharacterCount(String input) {
        Map<Character, Integer> countMap = new TreeMap<>(Collections.reverseOrder());

        input.toLowerCase().chars()
                .forEach(ch -> countMap.put((char) ch, countMap.getOrDefault((char) ch, 0) + 1));

        StringBuilder result = new StringBuilder();
        for (Character key : countMap.keySet()) {
//            result.append((char) key).append(countMap.get(key));
            result.append(String.format("%c", key)).append(countMap.get(key));
        }

        return result.toString();


//
////        Map<Character, Integer> countMap = new HashMap<Character, Integer>();
//        Map<Character, Integer> countMap = new TreeMap<Character, Integer>();
//
//        input.toLowerCase().chars()
//                .forEach(ch ->
//                    countMap.put((char)ch, countMap.getOrDefault((char)ch, 0) + 1)
//                );
////        List<Character> keyList = new ArrayList<>(countMap.keySet());
////        Collections.sort(keyList);
////        String result = "";
////        for (var key : keyList) {
////            result = result + (char)key + countMap.get(key);
////        }
////        return result;
//
//        StringBuilder result = new StringBuilder();
//        for (Character key : countMap.keySet()) {
//            result.append( (char)key + countMap.get(key));
//        }
//
//        return result.toString();
    }

    public static List<Integer> commonElements(List<Integer> a, List<Integer> b) {
        List<Integer> result = new ArrayList<>();
        for (var aEl : a) {
            if (b.indexOf(aEl) != -1) {
                result.add(aEl);
            }
        }
        return result;
    }
}
