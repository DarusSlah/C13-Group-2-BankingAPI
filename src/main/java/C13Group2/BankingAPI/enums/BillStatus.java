package C13Group2.BankingAPI.enums;

public enum BillStatus {
    Pending("pending"),
    Cancelled("Cancelled"),
    Completed("Completed"),
    Recurring("Recurring");

    private String status;
    BillStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
