package ru.larev.shorturl.payload;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Larev Pavel
 * @author http://telegram.me/larev
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountRegistration {
    private boolean success;
    private String description;
    private String password;

    public AccountRegistration(boolean success, String description, String password) {
        this.success = success;
        this.description = description;
        this.password = password;
    }

    public AccountRegistration(boolean success, String description) {
        this.success = success;
        this.description = description;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
