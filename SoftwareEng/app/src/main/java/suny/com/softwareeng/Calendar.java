package suny.com.softwareeng;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class Calendar extends ActionBarActivity {
    private Fmenu pmenu;
    int id;
    public Context contxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        final TextView title = (TextView) findViewById(R.id.txtTitle);
       final TextView date = (TextView) findViewById(R.id.txtData);
        contxt=this;
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest("http://moxie.cs.oswego.edu:19991/whatzon/get2?name="+Login.id, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int size = response.getJSONArray("Names:").length();


                            for(int i=0;i<size;i++){

                                    title.setText(response.getJSONArray("Names:").get(i).toString());
                                    date.setText(response.getJSONArray("Dates:").get(i).toString());
                                    id = response.getJSONArray("Ids:").getInt(i);


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("ErrorEvents", "Failed to connect moxie"); }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("ErrorVolleyEvents", volleyError.toString());
                    }
                });
        Log.d("EventsQueue", "Finished");
        queue.add(request);

        ImageButton b = (ImageButton) findViewById(R.id.btnInfo);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(contxt, EventDetail.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("id",id);
                startActivityForResult(intent, 1);
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
