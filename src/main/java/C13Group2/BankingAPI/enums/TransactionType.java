package C13Group2.BankingAPI.enums;

public enum TransactionType {
    DEPOSIT("Deposit"),
    WITHDRAWAL("Withdrawal");


    private final String type;


    private TransactionType(String type) {
        this.type = type;

    }
}
