import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FNZTest {
    public static void main(String[] args) {
        findDistinctNumberOfProducts();
        findNumberWithLargestDigitSum();
    }

    static int findDistinctNumberOfProducts() {
        int[] input = { 2,3,4,5 };
        List<Integer> inputList = Arrays.asList(2,3,4,5);
        Set<Integer> products = new HashSet<>();
        for (int i= 0;i < input.length; i ++) {
//            List<Integer> tempList = Arrays.stream(input).boxed().collect(Collectors.toList());
//            tempList.remove(i);

//            List<Integer> tempList = inputList

//            for (int j = 0; j < tempList.size(); j ++) {
//                for (int k = j + 1; k < tempList.size(); k ++) {
//                    products.add(tempList.get(j) * tempList.get(k));
//                }
//            }
        }

        System.out.println(products);
        return products.size();
    }

    static int findNumberWithLargestDigitSum() {
        int input = 20000;

        int maxDigitSum = 0;
        int numberWithMaxDigitSum = 0;
        int currSumOfDigits;
        for (int i = input - 1; i >= 0; i --) {
            currSumOfDigits = sumOfDigits(i);
            if (maxDigitSum < currSumOfDigits) {
                maxDigitSum = currSumOfDigits;
                numberWithMaxDigitSum = i;
            }
        }
        System.out.println(numberWithMaxDigitSum);
        return numberWithMaxDigitSum;
    }

    static int sumOfDigits(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
}
