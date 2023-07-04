package C13Group2.BankingAPI.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountType {
    SAVINGS("Savings"),
    CHECKING("Checking"),
    CREDIT("Credit");

    private final String type;

    AccountType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    @JsonValue
    public String toString() {
        return this.type;
    }
}
