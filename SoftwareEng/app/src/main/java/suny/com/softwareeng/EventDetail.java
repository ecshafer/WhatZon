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
<<<<<<< HEAD
=======
import android.widget.TextView;
>>>>>>> SaraivaBranch

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
<<<<<<< HEAD
            value =( HashMap<String,String>) extras.getSerializable("hash");
        }
        Log.d("DETAILS", value.toString());
=======
            TextView name = (TextView) findViewById(R.id.txtName);
            ImageView picture = (ImageView) findViewById(R.id.imageView2);
            value =( HashMap<String,String>) extras.getSerializable("hash");
            int begin = value.toString().indexOf("=") + 1;
            String teste2 = value.toString().substring((value.toString().indexOf("picture=")+8), (value.toString().indexOf("}")-1));
            String teste = value.toString().substring(begin,(value.toString().indexOf(",")-1 ));
            name.setText(value.toString().substring(begin,value.toString().indexOf(",") ));
            String draw =  "party";
            int id = getResources().getIdentifier(draw, "drawable", getPackageName());
            Drawable drawable = getResources().getDrawable(id);
            picture.setBackground(drawable);
            Log.d("DETAILS",teste2);
            Log.d("DETAILS", value.toString());
        }

>>>>>>> SaraivaBranch
        ImageView picture = (ImageView) findViewById(R.id.imageView2);
        //picture.setBackground((Integer) value.get("picture"));
        right.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Save in the calendar
                Log.d("Details", "Right");
<<<<<<< HEAD
                Calendar cal = Calendar.getInstance();
=======
              /*  Calendar cal = Calendar.getInstance();
>>>>>>> SaraivaBranch
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra("beginTime", cal.getTimeInMillis());
                intent.putExtra("allDay", true);
                intent.putExtra("rrule", "FREQ=YEARLY");
                intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
                intent.putExtra("title", "A Test Event from android app");
<<<<<<< HEAD
                startActivity(intent);
                Intent intent2 = new Intent(context, Events.class);
                startActivity(intent2);
=======
                startActivity(intent);*/
                Intent intent = new Intent(context, Events.class);;
                intent.putExtra("answer","right");
                setResult(RESULT_OK, intent);
                finish();
>>>>>>> SaraivaBranch
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
<<<<<<< HEAD

                Log.d ("Details", "Left");
                Intent intent = new Intent(context, Events.class);
                startActivity(intent);
=======
                Intent intent = new Intent(context, Events.class);;
                intent.putExtra("answer","left");
                setResult(RESULT_OK, intent);
                finish();

>>>>>>> SaraivaBranch
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
