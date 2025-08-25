import java.util.List;
import java.util.Map;

public class TestSocialMedia {
    public static void main(String[] args) {
        String[][] events = {
            {"CONNECT", "Alice", "Bob"},
            {"DISCONNECT", "Bob", "Alice"},
            {"CONNECT", "Alice", "Charlie"},
            {"CONNECT", "Dennis", "Bob"},
            {"CONNECT", "Pam", "Dennis"},
            {"DISCONNECT", "Pam", "Dennis"},
            {"CONNECT", "Pam", "Dennis"},
            {"CONNECT", "Edward", "Bob"},
            {"CONNECT", "Dennis", "Charlie"},
            {"CONNECT", "Alice", "Nicole"},
            {"CONNECT", "Pam", "Edward"},
            {"DISCONNECT", "Dennis", "Charlie"},
            {"CONNECT", "Dennis", "Edward"},
            {"CONNECT", "Charlie", "Bob"}
        };

        // Assuming SocialMediaEvents is a method that processes the events
        // and returns a list containing two lists:
        // 1. person list with connections of less than some value
        // 2. person list with connections of equal to some value or more
        // The value is passed as the second argument to the method.
        // For example, if the value is 3, it will return persons with less than 3 connections
        // and persons with 3 or more connections.

        List<List<String>> result = socialMediaEvents(events, 3);
        System.out.println(result);

        result = socialMediaEvents(events, 1);
        System.out.println(result);

        result = socialMediaEvents(events, 10);
        System.out.println(result);
    }

    static List<List<String>> socialMediaEvents(String[][] events, int threshold) {
        Map<String, List<String>> connections = new java.util.HashMap<>();
        for (String[] event : events) {
            String action = event[0];
            String person1 = event[1];
            String person2 = event[2];

            if (action.equals("CONNECT")) {
                connections.putIfAbsent(person1, new java.util.ArrayList<>());
                connections.putIfAbsent(person2, new java.util.ArrayList<>());
                connections.get(person1).add(person2);
                connections.get(person2).add(person1);
            } else if (action.equals("DISCONNECT")) {
                if (connections.containsKey(person1)) {
                    connections.get(person1).remove(person2);
                }
                if (connections.containsKey(person2)) {
                    connections.get(person2).remove(person1);
                }
            }
        }

        List<String> lessThanThreshold = new java.util.ArrayList<>();
        List<String> atLeastThreshold = new java.util.ArrayList<>();
        for (Map.Entry<String, List<String>> entry : connections.entrySet()) {
            String person = entry.getKey();
            int connectionCount = entry.getValue().size();

            if (connectionCount < threshold) {
                lessThanThreshold.add(person);
            } else {
                atLeastThreshold.add(person);
            }
        }
        return List.of(lessThanThreshold, atLeastThreshold);
    }
}
