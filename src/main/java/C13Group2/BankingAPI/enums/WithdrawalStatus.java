package C13Group2.BankingAPI.enums;

public enum WithdrawalStatus {
    PENDING("Pending"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed");


    private final String status;

    WithdrawalStatus(String status) {
        this.status = status;
    }
}
