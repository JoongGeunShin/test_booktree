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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.techtown.test_booktree.dataclass.JoinData;
import org.techtown.test_booktree.dataclass.JoinResponse;
import org.techtown.test_booktree.service.RetrofitClient;
import org.techtown.test_booktree.service.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    private EditText mEmailView;
    private EditText mPasswordView;
    private EditText mNameView;
    private Button mSignUpButton;
    private ServiceApi service;
    private ProgressBar mProgressView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mEmailView = (EditText) findViewById(R.id.emailEditText);
        mPasswordView = (EditText) findViewById(R.id.passwordEditText);
        mNameView = (EditText) findViewById(R.id.memeberNameText);
        mSignUpButton = (Button) findViewById(R.id.SignUpButton);
        mProgressView = (ProgressBar) findViewById(R.id.join_progress);
        mSignUpButton = (Button) findViewById(R.id.SignUpButton);

        findViewById(R.id.gotoLoginButton).setOnClickListener(onClickListener);
        findViewById(R.id.SignUpButton).setOnClickListener(onClickListener);

        service = RetrofitClient.getClient().create(ServiceApi.class);
    }
    View.OnClickListener onClickListener =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.gotoLoginButton:
                    Log.e("클릭", "클릭");
                    startLoginActivity();
                    break;

                    case R.id.SignUpButton:
                        Log.e("클릭", "클릭");
                        attemptJoin();
                        break;
                }
            }
        };



    private void attemptJoin() {
        mNameView.setError(null);
        mEmailView.setError(null);
        mPasswordView.setError(null);

        String name = mNameView.getText().toString();
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            mEmailView.setError("비밀번호를 입력해주세요.");
            focusView = mEmailView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = mPasswordView;
            cancel = true;
        }

        // 이메일의 유효성 검사
        if (email.isEmpty()) {
            mEmailView.setError("이메일을 입력해주세요.");
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError("@를 포함한 유효한 이메일을 입력해주세요.");
            focusView = mEmailView;
            cancel = true;
        }

        // 이름의 유효성 검사
        if (name.isEmpty()) {
            mNameView.setError("이름을 입력해주세요.");
            focusView = mNameView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startJoin(new JoinData(name, email, password));
            showProgress(true);
        }
    }

    private void startJoin(JoinData data) {
        service.userJoin(data).enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                JoinResponse result = response.body();
                Toast.makeText(SignupActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private  void startLoginActivity(){
        Intent intent=new Intent(this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


/*
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.SignUpButton:
                    Log.e("클릭", "클릭");
                    signUp();
                    break;
                case R.id.gotoLoginButton:
                    startLoginActivity();
                    break;
            }

        }
    };

    private void signUp() {
        EditText emailE = (EditText) findViewById(R.id.emailEditText);
        String email = emailE.getText().toString();
        EditText passwordE = (EditText) findViewById(R.id.passwordEditText);
        String password = passwordE.getText().toString();
        EditText passwordCheckE = (EditText) findViewById(R.id.passwordCheckEditText);
        String passwordCheck = passwordCheckE.getText().toString();

         (email.length() > 0 && password.length() > 0 && passwordCheck.length() > 0) {
            if (password.equals(passwordCheck)) {
                Task<AuthResult> authResultTask = mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this,
                                new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            startToast("회원가입을 성공적으로 마쳤습니다.");
                                            FirebaseUser user = mAuth.getCurrentUser();
                                        } else {
                                            if (task.getException() != null) {
                                                // If sign in fails, display a message to the user.
                                                startToast(task.getException().toString());
                                            }
                                        }
                                    }
                                });
            } else {
                startToast("비밀번호가 일치하지 않습니다.");
            }
        } else {
            startToast("이메일 또는 비밀번호를 입력해주세요.");
        }
    }

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }*/
}

