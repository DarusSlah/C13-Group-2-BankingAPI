package C13Group2.BankingAPI.enums;

public enum AccountType {
    SAVINGS("Savings"),
    CHECKING("Checking"),
    CREDIT("Credit");

    private final String displayName;

    AccountType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
