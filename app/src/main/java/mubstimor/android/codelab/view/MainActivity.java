package mubstimor.android.codelab.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import mubstimor.android.codelab.R;
import mubstimor.android.codelab.adapter.GithubUsersAdapter;
import mubstimor.android.codelab.model.GithubUser;
import mubstimor.android.codelab.presenter.GithubPresenter;

public class MainActivity extends AppCompatActivity {

    private GithubUsersAdapter mAdapter;
    private RecyclerView githubUsersList;
    private SearchView searchView;
    TextView totalUserCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(R.string.app_label);

        totalUserCountTextView = (TextView) findViewById(R.id.tv_total_users);

        searchView = new SearchView() {
            @Override
            public void usersReady(ArrayList<GithubUser> githubUsers, int totalCount) {

                mAdapter.setListContent(githubUsers);
                githubUsersList.setAdapter(mAdapter);
                totalUserCountTextView.setText(String.valueOf(totalCount)+ " Developers");

            }
        };

        GithubPresenter githubPresenter = new GithubPresenter();

        githubUsersList = findViewById(R.id.rv_gitusers);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        githubUsersList.setLayoutManager(layoutManager);
        githubUsersList.setHasFixedSize(true);

        mAdapter = new GithubUsersAdapter();

        githubPresenter.getGithubUsers(searchView);

    }

}
