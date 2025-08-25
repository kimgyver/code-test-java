import java.util.List;

public class TestBalanceChecker {
    // Balance Checker
    // Given a list of daily transactions (amount, type), where type is "credit" or "debit", return:
    // The final balance
    // The highest balance at any point in time
    // Example:
    // transactions = [
    //   (100, "credit"),
    //   (50, "debit"),
    //   (500, "credit"),
    //   (200, "debit")
    // ]

    public static class Transaction {
        int amount;
        String type;

        public Transaction(int amount, String type) {
            this.amount = amount;
            this.type = type;
        }
    }

    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
            new Transaction(100, "credit"),
            new Transaction(50, "debit"),
            new Transaction(500, "credit"),
            new Transaction(200, "debit")
        );

        int finalBalance = 0;
        int highestBalance = 0;

        for (Transaction transaction : transactions) {
            if (transaction.type == "credit") { // credit
                finalBalance += transaction.amount;
            } else { // debit
                finalBalance -= transaction.amount;
            }
            highestBalance = Math.max(highestBalance, finalBalance);
        }

        System.out.println("Final Balance: " + finalBalance);
        System.out.println("Highest Balance: " + highestBalance);
    }
}
