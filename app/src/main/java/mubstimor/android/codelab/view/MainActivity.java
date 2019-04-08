package mubstimor.android.codelab.view;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.design.widget.Snackbar;

import java.util.ArrayList;
import java.util.List;

import mubstimor.android.codelab.R;
import mubstimor.android.codelab.adapter.GithubUsersAdapter;
import mubstimor.android.codelab.application.GithubApp;
import mubstimor.android.codelab.model.GithubUser;
import mubstimor.android.codelab.presenter.GithubPresenter;
import mubstimor.android.codelab.util.NetworkUtility;

/**
 * This class implements the Main Activity for the app.
 *
 * @author Timothy Mubiru
 */

public class MainActivity extends AppCompatActivity implements
        SearchView, NetworkUtility.NetworkListener {

    GithubUsersAdapter mAdapter;
    RecyclerView recyclerView;
    TextView totalUserCountTextView;
    List<GithubUser> githubUserArrayList;
    static final String USERS_KEY = "githubUsers";
    GridLayoutManager gridLayoutManager;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;
    int totalDevCount;

    private static final String LIST_STATE = "listState";
    private static final String TOTAL_COUNT = "totalCount";
    private Parcelable mListState;
    int hasRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(R.string.app_label);

        totalUserCountTextView = (TextView) findViewById(R.id.tv_total_users);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipeToRefresh();
        recyclerView = findViewById(R.id.rv_gitusers);

        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridLayoutManager = getGridLayoutManager(2);
        } else {
            gridLayoutManager = getGridLayoutManager(3);
        }
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        mAdapter = new GithubUsersAdapter();

        if (savedInstanceState != null) {
            githubUserArrayList = savedInstanceState.getParcelableArrayList(USERS_KEY);
            totalDevCount = savedInstanceState.getInt(TOTAL_COUNT);
            mListState = savedInstanceState.getParcelable(LIST_STATE);
            hasRun = 1;
        } else {
            downloadData();
        }
    }

    /**
     * updates UI on swipe.
     */
    private void swipeToRefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                downloadData();
                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }

    /**
     * set grid layout manager.
     * @param spanCount number of columns
     * @return specified grid layour
     */
    private GridLayoutManager getGridLayoutManager(int spanCount) {
        return new GridLayoutManager(this, spanCount, GridLayoutManager.VERTICAL, false);
    }

    /**
     * Download github user data from search API.
     */
    void downloadData() {
        progressBar.setVisibility(View.VISIBLE);
        GithubPresenter githubPresenter = new GithubPresenter();
        githubPresenter.getGithubUsers(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(
                USERS_KEY,
                (ArrayList<? extends Parcelable>) githubUserArrayList
        );
        outState.putInt(TOTAL_COUNT, totalDevCount);
        mListState = gridLayoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE, mListState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            githubUserArrayList = savedInstanceState.getParcelableArrayList(USERS_KEY);
            totalDevCount = savedInstanceState.getInt(TOTAL_COUNT);
            mListState = savedInstanceState.getParcelable(LIST_STATE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        new GithubApp().getmInstance().setNetworkListener(this);
        if (mListState != null) {
            displayData(githubUserArrayList, totalDevCount);
            gridLayoutManager.onRestoreInstanceState(mListState);
            mListState = null;
        }
    }

    /**
     * Display data in recycler view.
     *
     * @param githubUserArrayList list of users
     * @param totalCount total number of developer
     */
    private void displayData(List<GithubUser> githubUserArrayList, int totalCount) {
        try {
            totalUserCountTextView.setText(totalCount + " Developers");
            mAdapter.setListContent(githubUserArrayList);
            mAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(mAdapter);
            progressBar.setVisibility(View.GONE);
        } catch (NullPointerException e) {
            // intentionally left blank
        }
    }

    /**
     * show no internet connection error.
     *
     * @param activity current activity
     * @param message  error to display
     */
    public void showSnackBar(Activity activity, String message) {
        View rootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG);
        if (!NetworkUtility.isOnline(getApplicationContext())) {
            snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
        }
        snackbar.show();
    }

    @Override
    public void usersReady(List<GithubUser> githubUsers, int totalCount) {
        githubUserArrayList = (ArrayList<GithubUser>) githubUsers;
        totalDevCount = totalCount;
        displayData(githubUsers, totalCount);
        totalUserCountTextView.setText(totalCount + " Developers");
    }

    @Override
    public void onNetworkStatusChange(boolean isConnected) {
        if (!isConnected) {
            showSnackBar(MainActivity.this, "No Internet Connection available. ");
        } else {
            showSnackBar(MainActivity.this, "...");
            if (hasRun != 1) {
                downloadData();
            }
        }
    }

}
