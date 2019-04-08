package mubstimor.android.codelab.view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
    LinearLayoutManager layoutManager;

    private static final String LIST_STATE = "listState";
    private Parcelable mListState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(R.string.app_label);

        totalUserCountTextView = (TextView) findViewById(R.id.tv_total_users);

        recyclerView = findViewById(R.id.rv_gitusers);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        mAdapter = new GithubUsersAdapter();

        checkConnection();

        if (!NetworkUtility.isOnline(getApplicationContext())) {
            showSnackBar(MainActivity.this, "No Internet Connection");
        } else {
            downloadData();
        }
    }

    /**
     * checks if connection exists.
     */
    private void checkConnection() {
        boolean isConnected = NetworkUtility.isOnline(getApplicationContext());
        showSnackBar(MainActivity.this, "Status " + isConnected);
    }

    /**
     * Download github user data from search API.
     */
    void downloadData() {
        GithubPresenter githubPresenter = new GithubPresenter();
        githubPresenter.getGithubUsers(this);
    }

    /**
     * Refresh activity on click of button.
     */
    void refreshOnNetworkChange() {
        Log.i("Reloading", "refreshing 1");
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
        new GithubApp().getmInstance().setNetworkListener(this);
        if (mListState != null) {
            displayData(githubUserArrayList);
            layoutManager.onRestoreInstanceState(mListState);
            mListState = null;
        }
    }

    /**
     * Display data in recycler view.
     *
     * @param githubUserArrayList list of users
     */
    private void displayData(List<GithubUser> githubUserArrayList) {
        try {
            mAdapter.setListContent(githubUserArrayList);
            mAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(mAdapter);
        } catch (NullPointerException e) {
            Log.i("Restore", "Silent restore");
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
            snackbar.setAction("RETRY", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    refreshOnNetworkChange();
                }
            });
            snackbar.setActionTextColor(Color.RED);
            snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
        }
        snackbar.show();
    }

    @Override
    public void usersReady(List<GithubUser> githubUsers, int totalCount) {
        githubUserArrayList = (ArrayList<GithubUser>) githubUsers;

        displayData(githubUsers);
        totalUserCountTextView.setText(totalCount + " Developers");
    }

    @Override
    public void onNetworkStatusChange(boolean isConnected) {
        if (!isConnected) {
            showSnackBar(MainActivity.this, "No Internet Connection available. ");
        } else {
            showSnackBar(MainActivity.this, "Internet Connection now available");
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something after 100ms
                    downloadData();
                }
            }, 100);
        }
    }

}
