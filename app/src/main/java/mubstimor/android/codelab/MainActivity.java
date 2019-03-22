package mubstimor.android.codelab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GithubUserAdapter.ListItemClickListener {

    private GithubUserAdapter mAdapter;
    private RecyclerView githubUsersList;

    private Toast mToast;

    private ArrayList<GithubUser> userListContent = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        githubUsersList = (RecyclerView) findViewById(R.id.rv_gitusers);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        githubUsersList.setLayoutManager(layoutManager);
        githubUsersList.setHasFixedSize(true);

        mAdapter = new GithubUserAdapter(this);

        populateRecyclerViewValues();
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        if (mToast != null) {
            mToast.cancel();
        }

        String toastMessage = "Item # "+ clickedItemIndex + " clicked";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
        mToast.show();

        GithubUser githubUser = userListContent.get(clickedItemIndex);
        String username = githubUser.getUsername();
        String imageUrl = githubUser.getImageUrl();

        Intent intent = new Intent(MainActivity.this, UserDetailActivity.class);
        intent.putExtra("user", username);
        intent.putExtra("image", imageUrl);
        startActivity(intent);

    }

    private void populateRecyclerViewValues() {
        /**
         * Add JSON data request here
         * */
        for(int iter=1; iter<=30; iter++) {
            GithubUser githubUser = new GithubUser();
            githubUser.setUsername("mubstimor" + iter);
            githubUser.setImageUrl("https://res.cloudinary.com/andela-valkyrie/image/upload/v1553607091/sims-avatar.jpg");
            userListContent.add(githubUser);
        }

        mAdapter.setListContent(userListContent);
        githubUsersList.setAdapter(mAdapter);
    }
}
