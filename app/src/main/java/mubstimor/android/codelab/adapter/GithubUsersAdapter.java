package mubstimor.android.codelab.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import mubstimor.android.codelab.utils.ColorUtils;
import mubstimor.android.codelab.R;
import mubstimor.android.codelab.model.GithubUser;
import mubstimor.android.codelab.view.UserDetailActivity;

/**
 * This class implements a RecyclerView adapter displaying items in a list view.
 * @author Timothy Mubiru
 */

public class GithubUsersAdapter extends RecyclerView.Adapter<GithubUsersAdapter.UserViewHolder> {

    Context context;

    List<GithubUser> githubUsers;

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.user_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        final int viewPosition = position;
        ColorUtils colorUtils = new ColorUtils();
        int backgroundColorForViewHolder = colorUtils
                .getViewHolderBackgroundColorFromInstance(context, position);
        holder.itemView.setBackgroundColor(backgroundColorForViewHolder);
        final GithubUser githubUser = githubUsers.get(viewPosition);
        holder.usernameView.setText(githubUser.getUsername());
        Picasso.with(context).load(githubUser.getAvatarUrl()).into(holder.userImageView);

        holder.itemlinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserDetailActivity.class);
                intent.putExtra("githubUser", new GithubUser(
                        githubUser.getUsername(),
                        githubUser.getAvatarUrl()
                ));
                context.startActivity(intent);
            }
        });
    }


    /**
     * Update list with updated content.
     * @param githubUsers Array list for users to be displayed.
     */
    public void setListContent(List<GithubUser> githubUsers) {

        this.githubUsers = githubUsers;
        notifyItemRangeChanged(0, githubUsers.size());
    }

    @Override
    public int getItemCount() {
        return githubUsers.size();
    }


    /**
     * This class initialises the fields to be updated by the adapter.
     */
    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView usernameView;
        CircleImageView userImageView;
        LinearLayout itemlinearLayout;

        /**
         * Initialise fields to be accessed.
         * @param itemView view for single item
         */
        public UserViewHolder(View itemView) {
            super(itemView);

            usernameView = (TextView) itemView.findViewById(R.id.tv_username);
            userImageView = (CircleImageView) itemView.findViewById(R.id.iv_user_image);
            itemlinearLayout = (LinearLayout) itemView.findViewById(R.id.item_parent_layout);
        }

    }
}
