import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class LogReader {
    public static class LogEntry {
        public Date timestamp;
        public String userId;
        public String endpoint;
        public int statusCode;
    }

    public static class LogSummary
    {
        public int totalRequests;
        public Map<String, Integer> requestsPerUser;
        public String mostHitEndpoint;
        public int mostHitEndpointCount;
        public int failedRequestsCount;
    }

    public static List<LogEntry> readLogs(String input, Date startDate, Date endDate) {
        try {
            List<String> lines;

            // Check if input is a file path
            if (Files.exists(Paths.get(input))) {
                lines = Files.readAllLines(Paths.get(input));
            } else if (input.contains("\n")) {
                // Split multi-line string into lines
                lines = List.of(input.split("\n"));
            } else {
                throw new IllegalArgumentException("Input must be a file path or multi-line log string.");
            }

            // Parse lines and filter by date range
            return lines.stream()
                    .map(LogReader::parseLogEntry)
                    .filter(log -> log != null)
                    .filter(log -> (startDate == null || !log.timestamp.before(startDate)) &&
                            (endDate == null || !log.timestamp.after(endDate)))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error reading logs", e);
        }
    }

    // Helper method to parse a log entry
    private static LogEntry parseLogEntry(String line) {
        String[] parts = line.split(",");
        if (parts.length != 4) return null;

        try {
            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date timestamp = isoFormat.parse(parts[0].trim());

            String userId = parts[1].trim();
            String endpoint = parts[2].trim();
            int statusCode = Integer.parseInt(parts[3].trim());

            LogEntry logEntry = new LogEntry();
            logEntry.timestamp = timestamp;
            logEntry.userId = userId;
            logEntry.endpoint = endpoint;
            logEntry.statusCode = statusCode;

            return logEntry;
        } catch (Exception e) {
            return null; // Skip invalid log entries
        }
    }

    // Summary report logic separated for testability
    public static LogSummary getSummary(List<LogEntry> logs) {
        LogSummary summary = new LogSummary();
        summary.totalRequests = logs.size();

        // Group by user ID
        summary.requestsPerUser = logs.stream()
                .collect(Collectors.groupingBy(log -> log.userId == null ? "" : log.userId, Collectors.summingInt(log -> 1)));

        // Find most hit endpoint
        var endpointGroup = logs.stream()
                .collect(Collectors.groupingBy(log -> log.endpoint == null ? "" : log.endpoint, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue());

        if (endpointGroup.isPresent()) {
            summary.mostHitEndpoint = endpointGroup.get().getKey();
            summary.mostHitEndpointCount = endpointGroup.get().getValue().intValue();
        } else {
            summary.mostHitEndpoint = null;
            summary.mostHitEndpointCount = 0;
        }

        // Count failed requests
        summary.failedRequestsCount = (int) logs.stream()
                .filter(log -> log.statusCode >= 400)
                .count();

        return summary;
    }

    public static void main(String[] args) {
        {
            String logString = """
                    2025-08-12T10:15:00Z, user1, /login, 200
                    2025-08-12T10:16:12Z, user1, /products, 200
                    2025-08-12T10:18:34Z, user2, /login, 403
                    2025-08-12T10:19:49Z, user3, /main, 202
                    2025-08-12T10:21:59Z, user3, /login, 200
                    2025-08-12T11:30:05Z, user1, /logout, 200
                    """;

            List<LogEntry> logs = readLogs(logString, null, null);

            for (var log : logs) {
                System.out.printf("%s | %s | %s | %d%n", log.timestamp, log.userId, log.endpoint, log.statusCode);
            }

            LogSummary summary = getSummary(logs);
            System.out.println("Total Requests: " + summary.totalRequests);

            System.out.println("Requests per User:");
            for (Map.Entry<String, Integer> user : summary.requestsPerUser.entrySet()) {
                System.out.println(user.getKey() + ": " + user.getValue());
            }

            System.out.println("Most Hit Endpoint: " + summary.mostHitEndpoint + " with " + summary.mostHitEndpointCount + " hits");
            System.out.println("Failed Requests Count: " + summary.failedRequestsCount);
        }
    }
}