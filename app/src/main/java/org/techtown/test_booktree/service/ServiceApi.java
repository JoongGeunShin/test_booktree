package org.techtown.test_booktree.service;

import org.techtown.test_booktree.dataclass.JoinData;
import org.techtown.test_booktree.dataclass.JoinResponse;
import org.techtown.test_booktree.dataclass.LoginData;
import org.techtown.test_booktree.dataclass.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceApi {
    @POST("/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/join")
    Call<JoinResponse> userJoin(@Body JoinData data);
}
