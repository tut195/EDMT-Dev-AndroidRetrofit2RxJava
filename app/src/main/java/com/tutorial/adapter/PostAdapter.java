package com.tutorial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.tutorial.R;
import com.tutorial.model.Post;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

  Context context;
  List<Post> postsList;

  public PostAdapter(Context context, List<Post> postsList) {
    this.context = context;
    this.postsList = postsList;
  }


  @NonNull
  @Override
  public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_layout, parent, false);

    return new PostViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
    holder.txt_author.setText(String.valueOf(postsList.get(position).userId));
    holder.txt_title.setText(String.valueOf(postsList.get(position).title));
    holder.txt_content.setText(new StringBuilder(postsList.get(position).body.substring(0, 20)).append("...").toString());

  }

  @Override
  public int getItemCount() {
    return postsList.size();
  }
}
