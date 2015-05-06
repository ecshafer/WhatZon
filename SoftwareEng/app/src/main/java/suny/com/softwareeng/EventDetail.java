package suny.com.softwareeng;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


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

                                value = (int) extras.getSerializable("id");
                                int size = response.getJSONArray("Names:").length();
                            for(int i=0;i<size;i++) {
                                if (response.getJSONArray("Ids:").getInt(i) == (value)) {
                                    Log.d("value4444:" , " "+ value);
                                     TextView descript = (TextView) findViewById(R.id.txtDescription);
                                    //TextView descript = (TextView) findViewById(R.id.txtDescription);
                                    final TextView names = (TextView) findViewById(R.id.txtName);
                                   // TextView names = (TextView) findViewById(R.id.txtName);
                                    final TextView address = (TextView) findViewById(R.id.txtAdress);
                                    TextView date = (TextView) findViewById(R.id.txtDate);
                                    ImageView picture = (ImageView) findViewById(R.id.imageView2);
                                    descript.setText(response.getJSONArray("Description:").get(i).toString());
                                    names.setText(response.getJSONArray("Names:").get(i).toString());
                                    address.setText(response.getJSONArray("Address:").get(i).toString());
                                    date.setText(response.getJSONArray("Dates:").get(i).toString());
                                    picture.setImageBitmap(getBitmapFromURL(response.getJSONArray("Pictures:").get(i).toString()));
                                    final String lat = response.getJSONArray("latitude").get(i).toString();
                                    final String lon = response.getJSONArray("longitude").get(i).toString();
                                    ImageButton map = (ImageButton) findViewById(R.id.btnMaps);
                                    map.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            Intent intent = new Intent(context, MapsActivity.class);
                                            final Bundle params = new Bundle();
                                            params.putString("name",names.getText().toString());
                                            params.putString("desc", address.getText().toString());
                                            params.putString("lat", lat);
                                            params.putString("lon",lon);
                                            intent.putExtras(params);
                                            startActivity(intent, params);
                                            startActivity(intent);
                                        }
                                    });
                                }

                            }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.d("FailedEventDetail", "Moxie connection Failed"); }
                            }
                        },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Log.d("FailedEventDetail", volleyError.toString());
                        }
                    });
            Log.d("EventDetail", "Finished");
            queue.add(request);

        }



        right.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("Details", "Right");
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
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data != null){
            String name = data.getStringExtra("answer");
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
