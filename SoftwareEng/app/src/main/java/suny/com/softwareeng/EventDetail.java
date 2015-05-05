package suny.com.softwareeng;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class EventDetail extends ActionBarActivity {

    private Fmenu pmenu;
    public  Bundle extras;
    public int  value;

    public Bitmap getBitmapFromURL(String imageUrl) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);

            return myBitmap;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        final Context context = this;
        ImageButton menu = (ImageButton) findViewById(R.id.menuBtn);
        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pmenu = new Fmenu();
                pmenu.initiatePopupWindow(EventDetail.this, v);
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


          value = 0;
         extras = getIntent().getExtras();


        if (extras != null) {

            RequestQueue queue = Volley.newRequestQueue(this);
            JsonObjectRequest request = new JsonObjectRequest("http://moxie.cs.oswego.edu:19991/whatzon/get", null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.d("value1:" , " "+ value);
                                value = (int) extras.getSerializable("id");
                                TextView name = (TextView) findViewById(R.id.txtName);
                                int size = response.getJSONArray("Names:").length();
                                Log.d("value2:" , " "+ value);
                            for(int i=0;i<size;i++) {
                                Log.d("value3:" , " "+ response.getJSONArray("Ids:").getInt(i));
                                Log.d("value3:" , " "+ value);
                                if (response.getJSONArray("Ids:").getInt(i) == (value)) {

                                    Log.d("value4444:" , " "+ value);
                                    TextView descript = (TextView) findViewById(R.id.txtDescription);
                                    TextView names = (TextView) findViewById(R.id.txtName);
                                    TextView address = (TextView) findViewById(R.id.txtAdress);
                                    TextView date = (TextView) findViewById(R.id.txtDate);
                                    ImageView picture = (ImageView) findViewById(R.id.imageView2);
                                    descript.setText(response.getJSONArray("Description:").get(i).toString());
                                    names.setText(response.getJSONArray("Names:").get(i).toString());
                                    address.setText(response.getJSONArray("Address:").get(i).toString());
                                    date.setText(response.getJSONArray("Dates:").get(i).toString());
                                    picture.setImageBitmap(getBitmapFromURL(response.getJSONArray("Pictures:").get(i).toString()));
                                    Log.d("TEste", response.getJSONArray("latitude").get(i).toString());
                                    Log.d("TEste",response.getJSONArray("longitude").get(i).toString());


                                }
                            }

                    Log.d("value:" , " "+ value);
                            } catch (JSONException e) {
                                Log.d("Failed", "to upload from moxie"); }
                            }
                        },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Log.d("volley error", "errror");
                        }
                    });
            Log.d("queue", "has Stopped");
            queue.add(request);

        }

        ImageView picture = (ImageView) findViewById(R.id.imageView2);
        //picture.setBackground((Integer) value.get("picture"));
        right.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Save in the calendar
                Log.d("Details", "Right");
              /*  Calendar cal = Calendar.getInstance();
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra("beginTime", cal.getTimeInMillis());
                intent.putExtra("allDay", true);
                intent.putExtra("rrule", "FREQ=YEARLY");
                intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
                intent.putExtra("title", "A Test Event from android app");
                startActivity(intent);*/
                Intent intent = new Intent(context, Events.class);
                intent.putExtra("answer","right");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, Events.class);
                intent.putExtra("answer","left");
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("TEste", "JJJ");
        if(data != null){
            Bundle extras = getIntent().getExtras();
            String name = data.getStringExtra("answer");
            Log.d("TEste", name);
            if (name != null) {
                if(name.equals("right")){
             //       flingContainer.getTopCardListener().selectRight();
                    Log.d("Answer","I wanna go");
                }
                if(name.equals("left")){
               //     flingContainer.getTopCardListener().selectLeft();
                    Log.d("Answer","I do not wanna go");
                }
            }

        }}

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.settings:
                startActivity(new Intent(this, Preferences.class));
                return true;
            case R.id.calendar:
                startActivity(new Intent(this, Wait.class));
                return true;
            case R.id.profile:
                startActivity(new Intent(this, Events.class));
                return true;
            case R.id.newevent:
                startActivity(new Intent(this, AddEvent.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
