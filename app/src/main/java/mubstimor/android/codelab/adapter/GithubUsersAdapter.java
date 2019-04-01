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

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import mubstimor.android.codelab.utils.ColorUtils;
import mubstimor.android.codelab.R;
import mubstimor.android.codelab.model.GithubUser;
import mubstimor.android.codelab.view.UserDetailActivity;

public class GithubUsersAdapter extends RecyclerView.Adapter<GithubUsersAdapter.UserViewHolder> {

    private Context context;

    private ArrayList<GithubUser> list_members=new ArrayList<>();

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.user_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        UserViewHolder viewHolder = new UserViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        final int viewPosition = position;
        int backgroundColorForViewHolder = ColorUtils
                .getViewHolderBackgroundColorFromInstance(context, position);
        holder.itemView.setBackgroundColor(backgroundColorForViewHolder);
        GithubUser githubUser = list_members.get(viewPosition);
        holder.usernameView.setText(githubUser.getUsername());
        Picasso.with(context).load(githubUser.getAvatarUrl()).into(holder.userImageView);

        holder.itemlinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GithubUser githubUser1 = list_members.get(viewPosition);
                Intent intent = new Intent(context, UserDetailActivity.class);
                intent.putExtra("user", githubUser1.getUsername());
                intent.putExtra("image", githubUser1.getAvatarUrl());
                context.startActivity(intent);
            }
        });
    }


    /**
     * set array list
     * */
    public void setListContent(ArrayList<GithubUser> list_members) {

        this.list_members = list_members;
        notifyItemRangeChanged(0, list_members.size());
    }

    @Override
    public int getItemCount() {
        return list_members.size();
    }


    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView usernameView;
        CircleImageView userImageView;
        LinearLayout itemlinearLayout;

        public UserViewHolder(View itemView) {
            super(itemView);

            usernameView = (TextView) itemView.findViewById(R.id.tv_username);
            userImageView = (CircleImageView) itemView.findViewById(R.id.iv_user_image);
            itemlinearLayout = (LinearLayout) itemView.findViewById(R.id.item_parent_layout);
        }

    }
}
