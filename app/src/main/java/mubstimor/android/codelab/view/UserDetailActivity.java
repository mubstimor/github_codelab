package mubstimor.android.codelab.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
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

public class UserDetailActivity extends AppCompatActivity implements ProfileView {

    Intent intent;
    TextView fullnameTextView;
    TextView usernameTextView;
    CircleImageView userImageView;
    TextView locationTextView;
    TextView companyTextView;
    TextView bioTextView;
    TextView linkTextView;
    ImageView shareButton;

    String profileUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        intent = getIntent();
        initViews();

        Bundle data = getIntent().getExtras();
        GithubUser githubUser = (GithubUser) data.getParcelable("githubUser");
        final String username = githubUser.getUsername();
        String imageUrl = githubUser.getAvatarUrl();

        Picasso.with(UserDetailActivity.this).load(imageUrl).into(userImageView);
        usernameTextView.setText(username);

        GithubPresenter githubPresenter = new GithubPresenter();
        githubPresenter.getUserProfile(username, this);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareProfile(username, profileUrl);
            }
        });
    }

    /**
     * set view fields here.
     */
    private void initViews() {
        fullnameTextView = (TextView) findViewById(R.id.tv_full_name);
        usernameTextView = (TextView) findViewById(R.id.tv_username);
        userImageView = findViewById(R.id.iv_user_image);
        linkTextView = (TextView) findViewById(R.id.tv_user_link);
        locationTextView = (TextView) findViewById(R.id.tv_user_location);
        companyTextView = (TextView) findViewById(R.id.tv_user_company);
        bioTextView = (TextView) findViewById(R.id.tv_user_bio);
        shareButton = (ImageView) findViewById(R.id.iv_share);
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
        profileUrl = githubUser.getHtmlUrl();
    }

    /**
     * Share user profile.
     * @param username user handle
     * @param htmlUrl profile address
     */
    public void shareProfile(String username, String htmlUrl) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/html");
        String message = "Check out this awesome developer @" + username + "</b>, " + htmlUrl;
        sharingIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(message));
        startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }

    @Override
    public void profileReady(GithubUser githubUser) {
        updateUI(githubUser);
    }
}
