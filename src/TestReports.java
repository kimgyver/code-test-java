import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestReports {
    public static void main(String[] args) {
        String transactionsJson = """
        [
          {"date": "2025-07-01", "amount": 120.5, "type": "credit"},
          {"date": "2025-07-01", "amount": 30.0,  "type": "debit"},
          {"date": "2025-07-02", "amount": 100.0, "type": "credit"}
        ]
        """;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Jackson vs Gson, 무엇이 더 나을까?
            // Jackson이 더 기능이 많고 성능이 좋다고 알려져 있지만, Gson은 사용하기 더 간단하고 가벼운 편이야.
            // Jackson이 더 복잡한 데이터 구조를 다루기에 좋고, Gson은 간단한 작업에 적합해.
            // Jackson이 Gson보다 약간 먼저 나왔어요. Jackson은 2007년에 처음 출시되었고, Gson은 2008년에 Google에서 개발되었습니다.
            var transactions = objectMapper.readValue(transactionsJson, new TypeReference<List<Transaction>>() {});
            Map<String, Double> dailyBalance = transactions.stream()
                .collect(Collectors.groupingBy(
                    t -> t.date,
                    Collectors.summingDouble(t -> t.type.equals("credit") ? t.amount : -t.amount)
                ));
            System.out.println("Daily Balances: " + dailyBalance);


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        String invoicesJson = """
        [
            {"id": "INV-001", "customer": "ACME", "amount": 200.0, "status": "paid"},
            {"id": "INV-002", "customer": "ACME", "amount": 300.0, "status": "unpaid"},
            {"id": "INV-003", "customer": "Globex", "amount": 150.0, "status": "paid"},
            {"id": "INV-004", "customer": "Globex", "amount": 250.0, "status": "paid"}
        ]
        """;
        try {
            List<Invoice> invoices = objectMapper.readValue(invoicesJson, new TypeReference<List<Invoice>>() {});
            var paidInvoicesSummary = invoices.stream()
                .filter(i -> i.status.equals("paid"))
                .collect(Collectors.groupingBy(i -> i.customer, Collectors.summingDouble(i -> i.amount)));
            System.out.println("Paid Invoices Summary: " + paidInvoicesSummary);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String accountsJson = """
        [
            {"accountId": "A1", "amount": 200.0, "timestamp": "2025-07-22T10:00:00Z"},
            {"accountId": "A2", "amount": 300.0, "timestamp": "2025-07-22T11:00:00Z"},
            {"accountId": "A1", "amount": 300.0, "timestamp": "2025-07-22T12:00:00Z"}
        ]
        """;

        try {
            List<Account> accounts = objectMapper.readValue(accountsJson, new TypeReference<List<Account>>() {});
            var balances = accounts.stream()
                .collect(Collectors.groupingBy(a -> a.accountId, Collectors.summingDouble(a -> a.amount)));
            var richestAccount = balances.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
            var balancesAndRichestAccount = Map.of(
                "Balances", balances,
                "RichestAccount", richestAccount
            );
            System.out.println("Balances and Richest Account: " + balancesAndRichestAccount);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String billingsJson = """
        [
            {"userId": "U1", "plan": "pro", "amount": 50.0, "timestamp": "2025-07-22T10:00:00Z"},
            {"userId": "U2", "plan": "basic", "amount": 20.0, "timestamp": "2025-07-22T11:00:00Z"},
            {"userId": "U3", "plan": "pro", "amount": 50.0, "timestamp": "2025-07-22T12:00:00Z"}
        ]
        """;

        try {
            List<Billing> billings = objectMapper.readValue(billingsJson, new TypeReference<List<Billing>>() {});
            var totalBilling = billings.stream()
                .collect(Collectors.groupingBy(b -> b.plan, Collectors.summingDouble(b -> b.amount)));
            var topPlan = totalBilling.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
            var totalBillingAndTopPlan = Map.of(
                "PlanTotals", totalBilling,
                "TopPlan", topPlan
            );
            System.out.println("Total Billing and Top Plan: " + totalBillingAndTopPlan);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        var ticketsStatsJson = """
        [
            {"ticketId": "T1", "status": "open", "priority": "high"},
            {"ticketId": "T2", "status": "closed", "priority": "low"},
            {"ticketId": "T3", "status": "open", "priority": "medium"}
        ]
        """;
        try {
            List<TicketStats> ticketsStats = objectMapper.readValue(ticketsStatsJson, new TypeReference<List<TicketStats>>() {});
            var statusCounts = ticketsStats.stream()
                .collect(Collectors.groupingBy(t -> t.status, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toList());
            var priorityCounts = ticketsStats.stream()
                .collect(Collectors.groupingBy(t -> t.priority, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toList());
            var ticketsSummary = Map.of(
                "StatusCounts", statusCounts,
                "PriorityCounts", priorityCounts
            );
            System.out.println("Tickets Summary: " + ticketsSummary);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    // 왜 static class로 정의하지?
    // static class로 정의하면 외부 클래스의 인스턴스 없이도 접근할 수 있어.
    // 이 경우, TestReports 클래스 내에서만 사용되는 데이터 구조를 정의하는 데 유용해.
    // 또한, 메모리 사용 측면에서도 효율적일 수 있어.
    // static class로 정의하면 C#의 nested class처럼 사용할 수 있어서 코드가 더 깔끔해지고 관리하기 쉬워져요.
    static class Transaction {
        public String date;
        public double amount;
        public String type;
    }

    static class Invoice {
        public String id;
        public String customer;
        public double amount;
        public String status;
    }

    static class Account {
        public String accountId;
        public double amount;
        public String timestamp;
    }

    static class Billing {
        public String userId;
        public String plan;
        public double amount;
        public String timestamp;
    }

    static class TicketStats {
        public String ticketId;
        public String status;
        public String priority;
    }
}
