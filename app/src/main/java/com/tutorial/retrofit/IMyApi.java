package com.tutorial.retrofit;

import com.tutorial.model.Post;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.GET;

public interface IMyApi {

  @GET("posts")
  Observable<List<Post>> getPosts();

}
