package org.techtown.test_booktree.dataclass;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    //@SerializedName("userName")
    //private String userName;

    //@SerializedName("userEmail")
    private String username;

    //@SerializedName("userPwd")
    private String password;

    public JoinData(String userName, String userEmail, String userPwd) {
        this.username = userName;
        //this.userEmail = userEmail;
        this.password = userPwd;
    }
}
