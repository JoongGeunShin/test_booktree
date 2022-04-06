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
        findViewById(R.id.communityButton).setOnClickListener(onClickListener);
        findViewById(R.id.profileButton).setOnClickListener(onClickListener);
        findViewById(R.id.searchButton).setOnClickListener(onClickListener);
        findViewById(R.id.rankingButton).setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.chatButton:
                    //FirebaseUser user = mAuth.getCurrentUser();
                    startChatActivity();
                    break;
                case R.id.communityButton:
                    startCommunityActivity();
                    break;
                case R.id.profileButton:
                    startProfileActivity();
                    break;
                case R.id.searchButton:
                    startSearchActivity();
                    break;
                case R.id.rankingButton:
                    startRankingActivity();
                    break;
            }
        }
    };
    private void startChatActivity(){
        Intent intent = new Intent(this, ChatStarterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    private void startCommunityActivity(){
        Intent intent = new Intent(this, CommunityActivity.class);
        startActivity(intent);
    }
    private void startProfileActivity(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
    private void startSearchActivity(){
        Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);
    }
    private void startRankingActivity(){
        Intent intent = new Intent(this, RankingActivity.class);
        startActivity(intent);
    }
}