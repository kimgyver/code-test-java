import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class WordFrequencyCounter {
    private String input;
    private Map<String, Integer> wordAndCount = new HashMap<>();

    public WordFrequencyCounter(String input) {
        this.input = input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void process() {
        // split with space/,/./'/"
        var separated = input.split("[ ,.'\"]");

        // remove whitespace word
//        var separatedNoWhitespace = Arrays.stream(separated)
//                .filter(w -> !w.equals(""))
//                .collect(Collectors.toList());

        // foreach word -> put to map
        for (var each : separated) {
            if (each.isEmpty()) continue;
            int count = wordAndCount.containsKey(each) ? wordAndCount.get(each) + 1 : 1;
            wordAndCount.put(each, count);
        }

        // sort by descending order
        System.out.println(wordAndCount);
//        var sorted = wordAndCount.entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
//                .collect(Collectors.toList());

        System.out.println(wordAndCount.entrySet() + "<-- entryset");
        var sorted = new ArrayList<>(wordAndCount.entrySet());
        sorted.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        System.out.println(sorted);
    }
}

public class WordFrequencyCounterApplication {
    public static void main(String[] args) {
        WordFrequencyCounter counter = new WordFrequencyCounter("");
        counter.setInput("AA BB CC AA BB DD EE FF AA. BB BB BB");
        counter.process();
    }
}
