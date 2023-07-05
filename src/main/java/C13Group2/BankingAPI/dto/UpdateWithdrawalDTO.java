package C13Group2.BankingAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateWithdrawalDTO {

    @JsonProperty("description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
