package mubstimor.android.codelab.view;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mubstimor.android.codelab.R;
import mubstimor.android.codelab.adapter.GithubUsersAdapter;
import mubstimor.android.codelab.model.GithubUser;
import mubstimor.android.codelab.presenter.GithubPresenter;

/**
 * This class implements the Main Activity for the app.
 * @author Timothy Mubiru
 */

public class MainActivity extends AppCompatActivity {

    GithubUsersAdapter mAdapter;
    RecyclerView recyclerView;
    private SearchView searchView;
    TextView totalUserCountTextView;
    List<GithubUser> githubUserArrayList;
    static final String USERS_KEY = "githubUsers";
    LinearLayoutManager layoutManager;

    private static final String LIST_STATE = "listState";
    private Parcelable mListState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(R.string.app_label);

        totalUserCountTextView = (TextView) findViewById(R.id.tv_total_users);
        searchView = new SearchView() {
            @Override
            public void usersReady(List<GithubUser> githubUsers, int totalCount) {
                githubUserArrayList = (ArrayList<GithubUser>) githubUsers;

                mAdapter.setListContent(githubUsers);
                recyclerView.setAdapter(mAdapter);
                totalUserCountTextView.setText(totalCount + " Developers");
            }
        };

        GithubPresenter githubPresenter = new GithubPresenter();

        recyclerView = findViewById(R.id.rv_gitusers);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        mAdapter = new GithubUsersAdapter();

        githubPresenter.getGithubUsers(searchView);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(
                USERS_KEY,
                (ArrayList<? extends Parcelable>) githubUserArrayList
        );
        mListState = layoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE, mListState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            githubUserArrayList = savedInstanceState.getParcelableArrayList(USERS_KEY);
            mListState = savedInstanceState.getParcelable(LIST_STATE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mListState != null) {
            mAdapter.setListContent(githubUserArrayList);
            recyclerView.setAdapter(mAdapter);
            layoutManager.onRestoreInstanceState(mListState);
            mListState = null;
        }
    }

}
