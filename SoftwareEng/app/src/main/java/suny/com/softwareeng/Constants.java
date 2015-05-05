package suny.com.softwareeng;

/**
 * Created by AKiniyalocts on 2/23/15.
 */
public class Constants {
  /*
    Logging flag
   */
  public static final boolean LOGGING = true;

  /*
    Your imgur client id. You need this to upload to imgur.

    More here: https://api.imgur.com/
   */
  public static final String MY_IMGUR_CLIENT_ID = "e91640526c1043b";
  public static final String MY_IMGUR_CLIENT_SECRET = "640822e7e6a615daf84bbf76651265781eff7bf6";

  /*
    Redirect URL for android.
   */
  public static final String MY_IMGUR_REDIRECT_URL = "http://android";

  /*
    Client Auth
   */
  public static String getClientAuth(){
    return "Client-ID " + MY_IMGUR_CLIENT_ID;
  }

}
