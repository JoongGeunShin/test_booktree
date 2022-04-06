package org.techtown.test_booktree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.chatButton).setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.chatButton:
                    //FirebaseUser user = mAuth.getCurrentUser();
                    startChatActivity();
                    break;
             // case R.id."":
            }
        }
    };
    private  void startChatActivity(){
        Intent intent = new Intent(this, ChatStarterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}