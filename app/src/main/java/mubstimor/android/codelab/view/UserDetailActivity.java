package mubstimor.android.codelab.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import mubstimor.android.codelab.R;
import mubstimor.android.codelab.model.GithubUser;
import mubstimor.android.codelab.presenter.GithubPresenter;

/**
 * This class implements the User detail activity.
 * @author Timothy Mubiru
 */

public class UserDetailActivity extends AppCompatActivity {

    Intent intent;
    TextView fullnameTextView;
    TextView usernameTextView;
    CircleImageView userImageView;
    TextView locationTextView;
    TextView companyTextView;
    TextView bioTextView;
    TextView linkTextView;

    private ProfileView profileView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        intent = getIntent();
        fullnameTextView = (TextView) findViewById(R.id.tv_full_name);
        usernameTextView = (TextView) findViewById(R.id.tv_username);
        userImageView = findViewById(R.id.iv_user_image);
        linkTextView = (TextView) findViewById(R.id.tv_user_link);
        locationTextView = (TextView) findViewById(R.id.tv_user_location);
        companyTextView = (TextView) findViewById(R.id.tv_user_company);
        bioTextView = (TextView) findViewById(R.id.tv_user_bio);

        Bundle data = getIntent().getExtras();
        GithubUser githubUser = (GithubUser) data.getParcelable("githubUser");
        String username = githubUser.getUsername();
        String imageUrl = githubUser.getAvatarUrl();

        Picasso.with(UserDetailActivity.this).load(imageUrl).into(userImageView);
        usernameTextView.setText(username);
        profileView = new ProfileView() {
            @Override
            public void profileReady(GithubUser githubUser) {
                updateUI(githubUser);
            }
        };
        GithubPresenter githubPresenter = new GithubPresenter();
        githubPresenter.getUserProfile(username, profileView);
    }

    /**
     * Updates fields on the layout.
     * @param githubUser object with user data
     */
    public void updateUI(GithubUser githubUser) {
        fullnameTextView.setText(githubUser.getName());
        locationTextView.setText(githubUser.getLocation());
        bioTextView.setText(githubUser.getBio());

        if (githubUser.getCompany() == null) {
            companyTextView.setText("None");
        } else {
            companyTextView.setText(githubUser.getCompany());
        }
        linkTextView.setText(githubUser.getHtmlUrl());
    }
}
