package C13Group2.BankingAPI.enums;

public enum DepositStatus {
    PENDING("Pending"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed");


    private final String status;

    DepositStatus(String status) {
        this.status = status;
    }
}
