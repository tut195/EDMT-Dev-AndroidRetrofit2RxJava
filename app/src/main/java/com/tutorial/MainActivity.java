package com.tutorial;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tutorial.adapter.PostAdapter;
import com.tutorial.model.Post;
import com.tutorial.retrofit.IMyApi;
import com.tutorial.retrofit.RetrofitClient;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

  // Variables

  IMyApi myApi;
  RecyclerView recyclerPosts;
  CompositeDisposable compositeDisposable = new CompositeDisposable();

  // Life

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Init Api
    Retrofit retrofit = RetrofitClient.getInstance();

    myApi = retrofit.create(IMyApi.class);

    // View

    recyclerPosts = (RecyclerView) findViewById(R.id.recycler_posts);
    recyclerPosts.setHasFixedSize(true);
    recyclerPosts.setLayoutManager(new LinearLayoutManager(this));

    fetchData();
  }

  @Override
  protected void onStop() {
    super.onStop();
    compositeDisposable.clear();
  }

  private void fetchData() {
    compositeDisposable.add(myApi.getPosts()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Post>>() {
          @Override
          public void accept(List<Post> posts) throws Exception {
            displayData(posts);

          }
        }));
  }

  private void displayData(List<Post> posts) {
    PostAdapter adapter = new PostAdapter(this, posts);
    recyclerPosts.setAdapter(adapter);
  }
}
