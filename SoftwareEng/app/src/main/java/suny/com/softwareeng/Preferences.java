package suny.com.softwareeng;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Lucas on 3/25/2015.
 */
public class Preferences  extends Activity {

         public static boolean food,music,party,politics,education,movies;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);

            //Remove notification bar
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            super.onCreate(savedInstanceState);
            getFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new PrefsFragment()).commit();
            PreferenceManager.setDefaultValues(this, R.xml.preferences,false);

            Log.d(" CALLLINNG ME", " llllll");




        }
    public static class PrefsFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.d(" CALLLINNG ME", " jjjjj");
            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preferences);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs =   PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Log.d(" CALLLINNG ME", " CALLLL"+music);

        music = prefs.getBoolean("music", false);
        food = prefs.getBoolean("food", true);
        party = prefs.getBoolean("party", true);
        politics = prefs.getBoolean("politics", true);
        education = prefs.getBoolean("education", true);
        movies = prefs.getBoolean("movies", true);
        Log.d(" CALLLINNG ME", " CALLLL"+music);
    }


}
