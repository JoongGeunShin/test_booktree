package org.techtown.test_booktree.dataclass;

import com.google.gson.annotations.SerializedName;

public class LoginData {


    String username;

    String password;

    public LoginData(String userEmail, String userPwd) {
        this.username = userEmail;
        this.password = userPwd;
    }
}