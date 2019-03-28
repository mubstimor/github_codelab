package mubstimor.android.codelab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserDetailActivity extends AppCompatActivity {

    Intent intent;
    TextView usernameTextView;
    CircleImageView userImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        intent = getIntent();
        usernameTextView = (TextView) findViewById(R.id.tv_full_name);
        userImageView = (CircleImageView) findViewById(R.id.iv_user_image);

        String username = intent.getStringExtra("user");
        String imageUrl = intent.getStringExtra("image");
        Picasso.with(UserDetailActivity.this).load(imageUrl).into(userImageView);

        usernameTextView.setText(username);
    }
}
