package com.servicethrottle.stuaaservice.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ResetPasswordRequest {
    @NotNull
    private String resetKey;

    @NotNull
    @Size(min = 8)
    private String newPassword;

    public ResetPasswordRequest(@NotNull String resetKey,
                                @NotNull @Size(min = 8) String newPassword) {
        this.resetKey = resetKey;
        this.newPassword = newPassword;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "ResetPasswordRequest{" +
                "resetKey='" + resetKey + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
