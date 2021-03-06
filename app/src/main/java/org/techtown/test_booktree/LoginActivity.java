package org.techtown.test_booktree;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.techtown.test_booktree.dataclass.LoginData;
import org.techtown.test_booktree.dataclass.LoginResponse;
import org.techtown.test_booktree.service.RetrofitClient;
import org.techtown.test_booktree.service.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private EditText mEmailView;
    private EditText mPasswordView;
    private Button mEmailLoginButton;
    private Button mSignUpButton;
    private Button mNonMemberLoginButton;
    private ProgressBar mProgressView;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView = (EditText) findViewById(R.id.emailEditText);
        mPasswordView = (EditText) findViewById(R.id.passwordEditText);
        mEmailLoginButton = (Button) findViewById(R.id.loginButton);
        mSignUpButton = (Button) findViewById(R.id.gotoSignUpButton);
        mProgressView = (ProgressBar) findViewById(R.id.login_progress);
        mNonMemberLoginButton = (Button) findViewById(R.id.nonMemLoginButton);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        findViewById(R.id.loginButton).setOnClickListener(onClickListener);
        findViewById(R.id.gotoSignUpButton).setOnClickListener(onClickListener);
        findViewById(R.id.nonMemLoginButton).setOnClickListener(onClickListener);
    }


    View.OnClickListener onClickListener =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.loginButton:
                    Log.e("??????", "??????");
                    attemptLogin();
                    //startMainActivity();
                    break;
                case R.id.gotoSignUpButton:
                    Log.e("??????", "??????");
                    startSignUpActivity();
                    break;
                case R.id.nonMemLoginButton:
                    Log.e("??????", "??????");
                    startMainActivity();
                    break;
            }
        }
    };

    private void attemptLogin() {
        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // ??????????????? ????????? ??????
        if (password.isEmpty()) {
            mEmailView.setError("??????????????? ??????????????????.");
            focusView = mEmailView;
            cancel = true;
        }/* else if (!isPasswordValid(password)) {
            mPasswordView.setError("6??? ????????? ??????????????? ??????????????????.");
            focusView = mPasswordView;
            cancel = true;
        }*/

        // ???????????? ????????? ??????
        if (email.isEmpty()) {
            mEmailView.setError("???????????? ??????????????????.");
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startLogin(new LoginData(email, password));
            showProgress(true);
        }
    }

    private void startLogin(LoginData data) {
        service.userLogin(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();
                Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);
                //startMainActivity();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "????????? ?????? ??????", Toast.LENGTH_SHORT).show();
                Log.e("????????? ?????? ??????", t.getMessage());
                showProgress(false);
            }
        });
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void startSignUpActivity(){
        Intent intent=new Intent(this,SignupActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void startMainActivity(){
        Intent intent=new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
