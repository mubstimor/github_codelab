package mubstimor.android.codelab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GithubUserAdapter extends RecyclerView.Adapter<GithubUserAdapter.UserViewHolder> {

    private ListItemClickListener mOnClickListener;

    private Context context;

    private ArrayList<GithubUser> list_members=new ArrayList<>();

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public GithubUserAdapter(ListItemClickListener listener) {
        mOnClickListener = listener;
    }

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
        int backgroundColorForViewHolder = ColorUtils
                .getViewHolderBackgroundColorFromInstance(context, position);
        holder.itemView.setBackgroundColor(backgroundColorForViewHolder);
        GithubUser list_items = list_members.get(position);
        holder.usernameView.setText(list_items.getUsername());
        Picasso.with(context).load(list_items.getImageUrl()).transform(new CircleTransform()).into(holder.userImageView);
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


    class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView usernameView;
        ImageView userImageView;

        public UserViewHolder(View itemView) {
            super(itemView);

            usernameView = (TextView) itemView.findViewById(R.id.tv_username);
            userImageView = (ImageView) itemView.findViewById(R.id.iv_user_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }

    }
}
