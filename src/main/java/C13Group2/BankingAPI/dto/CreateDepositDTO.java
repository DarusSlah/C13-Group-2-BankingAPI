package C13Group2.BankingAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateDepositDTO {
    @JsonProperty("medium")
    @NotEmpty
    private String medium;
    @JsonProperty("amount")
    @NotNull
    private Double amount;
    @JsonProperty("description")
    private String description;

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

