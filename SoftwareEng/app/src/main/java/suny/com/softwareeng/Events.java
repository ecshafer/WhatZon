package suny.com.softwareeng;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class Events extends Activity {

    private Fmenu pmenu;
    private int i;
    public     ExtendedSimpleAdapter adapter;
    public  List<HashMap<String,?>> aList;
    public ArrayList<Integer> idList = new ArrayList<>();
    public ArrayList<Integer> idList2 = new ArrayList<>();

    public Context context2;
    public  List<HashMap<String,String>> hm3= new ArrayList<HashMap<String,String>>();
    public  List<HashMap<String,String>> hm2 = new ArrayList<HashMap<String,String>> ();

    @InjectView(R.id.frame) SwipeFlingAdapterView flingContainer;

    public Bitmap getBitmapFromURL(String imageUrl) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(input),390, 230, true);

            return myBitmap;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);
        ButterKnife.inject(this);

        final Context context = this;
        context2 = this;
        aList = new ArrayList<HashMap<String,?>>();
// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest("http://moxie.cs.oswego.edu:19991/whatzon/get2?name="+Login.id, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

int size = response.getJSONArray("Names:").length();


        for(int i=0;i<size;i++){
            HashMap<String, Object> hm = new HashMap<String,Object>();

            hm.put("EventName", "" + response.getJSONArray("Names:").get(i).toString());
            if(response.getJSONArray("Ids:").getString(i) != null){
                Log.d("PICTURESEMFOTO", "SEMFOTO");
               if(response.getJSONArray("Pictures:").get(i).toString().equals("@getPicture")){
                   //hm.put("picture","TITICA");
               } else {
                   Log.d("COMFOTO", response.getJSONArray("Pictures:").get(i).toString());

                /*   Bitmap map = getBitmapFromURL( response.getJSONArray("Pictures:").get(i).toString());
                   ByteArrayOutputStream bao = new ByteArrayOutputStream();
                   map.compress(Bitmap.CompressFormat.JPEG, 90, bao);
                   byte [] ba = bao.toByteArray();
                   String ba1=Base64.encodeToString(ba,Base64.DEFAULT);*/
                 //  Log.d("TESTE", "" + ba1);
                   hm.put("picture", getBitmapFromURL(response.getJSONArray("Pictures:").get(i).toString()));


                }

            idList.add(response.getJSONArray("Ids:").getInt(i));
          idList2.add(response.getJSONArray("Ids:").getInt(i));

            }


                aList.add(hm);
            }



            // Keys used in Hashmap
            String[] from = { "picture","EventName" };
            // Ids of views in listview_layout
            int[] to = { R.id.picture,R.id.eventText};
            // Instantiating an adapter to store each items
            // R.layout.listview_layout defines the layout of each item
             adapter = new ExtendedSimpleAdapter(getBaseContext(), aList, R.layout.item, from, to);
            // Setting the adapter to the listView
           // listView.setAdapter(adapter);

                            flingContainer.setAdapter(adapter);
                            Log.d("FINALIZADO", " FINALIZADO");
                        } catch (JSONException e) {
                            Log.d("FLAHOOOOSAODOASDOSAO", "to upload from moxie"); }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("volley error", volleyError.toString());
                    }
                });
        Log.d("queue", "has Stopped");
        queue.add(request);

       // arrayAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.helloText, al );
       ImageButton findOut = (ImageButton) findViewById(R.id.findOut);
        findOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, EventDetail.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("id", idList.get(0));
                startActivityForResult(intent,1);
            }
        });
        ImageButton menu = (ImageButton) findViewById(R.id.menuBtn);
        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pmenu = new Fmenu();
                pmenu.initiatePopupWindow(Events.this, v);
            }
        });


        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                Log.d("LIST", "removed object!");
                aList.remove(0);
               adapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {



                String url = "http://moxie.cs.oswego.edu:19991/whatzon/saveLikes";
                final RequestQueue queue1 = Volley.newRequestQueue(context);
                StringRequest sr = new StringRequest(com.android.volley.Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("57975495"," SIZE:" + aList.size());
                    }
                }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("4774474788"," SIZE:" + aList.size());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        if (!idList.isEmpty()) {
                            Map<String, String> params = new HashMap<String, String>();
                            Log.d("SIZEEEEEEEEE", " SIZE:" + aList.size());
                            params.put("id_user", Login.id);
                            Log.d("ID",Login.id);
                            int value = idList.get(0);
                            params.put("id_event", "" + (value - 1));
                            params.put("participation", "0");
                            Calendar c = Calendar.getInstance();
                            int day = c.get(Calendar.DAY_OF_MONTH);
                            int month = c.get(Calendar.MONTH);
                            int year = c.get(Calendar.YEAR);
                            params.put("date", month + "/" + day + "/" + year);

                            return params;
                        }
                        return null;
                    }
                };


                queue1.add(sr);

                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject

                idList.remove(0);
                makeToast(Events.this, "Left!");
            }

            @Override
            public void onRightCardExit(Object dataObject) {

                String url = "http://moxie.cs.oswego.edu:19991/whatzon/saveLikes";
                final RequestQueue queue1 = Volley.newRequestQueue(context);
                StringRequest sr = new StringRequest(com.android.volley.Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("57975495"," SIZE:" + aList.size());
                    }
                }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("4774474788"," SIZE:" + aList.size());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        Log.d("SIZEEEEEEEEE"," SIZE:" + aList.size());
                        params.put("id_user", Login.id);
                        int value = idList.get(0);
                        params.put("id_event", ""+(value-1));
                        params.put("participation", "1");
                        Calendar c = Calendar.getInstance();
                        int day = c.get(Calendar.DAY_OF_MONTH);
                        int month = c.get(Calendar.MONTH);
                        int year = c.get(Calendar.YEAR);
                        params.put("date", month+"/"+day+"/"+year);

                        return params;
                    }
                };


                queue1.add(sr);

                idList.remove(0);
                makeToast(Events.this, "Right!");
            }

           @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

                                   if(aList.size() == 0) {
                    Intent intent = new Intent(context, Wait.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                    startActivityForResult(intent, 1234);
                }}

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });

        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Intent intent = new Intent(context, EventDetail.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("id", idList.get(0));
                startActivityForResult(intent,1);
            }
        });

    }

    static void makeToast(Context ctx, String s){
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
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
                flingContainer.getTopCardListener().selectRight();
                Log.d("Answer","I wanna go");
            }
            if(name.equals("left")){
                flingContainer.getTopCardListener().selectLeft();
                Log.d("Answer","I do not wanna go");
            }
        }

    }}

    @OnClick(R.id.right)
    public void right() {


        flingContainer.getTopCardListener().selectRight();
    }

    @OnClick(R.id.left)
    public void left() {

        flingContainer.getTopCardListener().selectLeft();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

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
