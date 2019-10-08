package br.com.manfe.crud.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.manfe.crud.R;
import br.com.manfe.crud.activities.EditUserActivity;
import br.com.manfe.crud.activities.MainActivity;
import br.com.manfe.crud.entities.User;

import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder> {

    private List<User> mUsers;

    public UserRecyclerViewAdapter(List<User> users) {
        mUsers = users;
    }

    // Método que recebe atualização e informa que o dataset foi atualizado
    public void setmUsers(List<User> mUsers) {
        this.mUsers = mUsers;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mUsers.get(position);
        holder.mIdView.setText("" + holder.mItem.getUid());
        holder.mContentView.setText(mUsers.get(position).getEmail());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditUserActivity.class);
                intent.putExtra("email", holder.mItem.getEmail());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public User mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
