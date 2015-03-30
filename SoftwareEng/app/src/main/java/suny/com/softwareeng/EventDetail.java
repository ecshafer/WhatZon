package suny.com.softwareeng;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


public class EventDetail extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        //Remove notification bar

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
    final Context context = this;
        ImageButton menu = (ImageButton) findViewById(R.id.btnMenu);
        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, MenuPage.class);


                startActivity(intent);
            }
        });
        ImageButton map = (ImageButton) findViewById(R.id.btnMaps);
        map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("DETAILS", "CLICO");
                Intent intent = new Intent(context, MapsActivity.class);


                startActivity(intent);
            }
        });
        ImageButton right = (ImageButton) findViewById(R.id.btnYes);
        ImageButton left = (ImageButton) findViewById(R.id.btnNo);
        Bundle extras = getIntent().getExtras();
        HashMap<String,String>  value = new HashMap<String,String> ();
        if (extras != null) {
            value =( HashMap<String,String>) extras.getSerializable("hash");
        }
        Log.d("DETAILS", value.toString());
        ImageView picture = (ImageView) findViewById(R.id.imageView2);
        //picture.setBackground((Integer) value.get("picture"));
        right.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Save in the calendar
                Log.d("Details", "Right");
                Calendar cal = Calendar.getInstance();
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra("beginTime", cal.getTimeInMillis());
                intent.putExtra("allDay", true);
                intent.putExtra("rrule", "FREQ=YEARLY");
                intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
                intent.putExtra("title", "A Test Event from android app");
                startActivity(intent);
                Intent intent2 = new Intent(context, Events.class);
                startActivity(intent2);
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.d ("Details", "Left");
                Intent intent = new Intent(context, Events.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
