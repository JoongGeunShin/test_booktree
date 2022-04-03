package org.techtown.test_booktree.dataclass;

import com.google.gson.annotations.SerializedName;

public class JoinResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}