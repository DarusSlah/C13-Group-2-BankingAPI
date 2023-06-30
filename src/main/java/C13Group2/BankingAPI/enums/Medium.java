package C13Group2.BankingAPI.enums;

public enum Medium {
    BALANCE("Balance"),
    REWARDS("Rewards");


    private final String medium;

    Medium(String medium) {
        this.medium = medium;
    }
}
