package C13Group2.BankingAPI.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class SuccessResponse<T>/* The T is just a placeHolder */ {
    private int status;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL) // properties with a null value should be excluded from the serialized JSON output. And properties with non-null values will be included in the JSON.
    private T data;

    public SuccessResponse() {
    }

    public SuccessResponse(int status, String message) {
        this.status = status;
        this.message = message;

    }

    public SuccessResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
