package suny.com.softwareeng;

import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.mime.TypedFile;


public interface ImgurAPI {
  public static String server = "https://api.imgur.com";



  @POST("/3/image")ImageResponse postImage(
          @Header("Authorization") String auth,
          @Query("title") String title,
          @Query("description") String description,
          @Query("album") String albumId,
          @Query("account_url") String username,
          @Body TypedFile file
  );
}
