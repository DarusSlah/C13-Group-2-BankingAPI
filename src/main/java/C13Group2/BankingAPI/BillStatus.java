package C13Group2.BankingAPI;

public enum BillStatus {
    Pending("pending"),
    Cancelled("Cancelled"),
    Completed("Completed"),
    Recurring("Recurring");

    private String status;
    BillStatus(String status) {
        this.status = status;
    }
}
