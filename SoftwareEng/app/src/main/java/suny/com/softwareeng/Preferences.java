package suny.com.softwareeng;

import android.content.Context;
import android.content.Intent;
import android.location.Geocoder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.SeekBar;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class Preferences extends ActionBarActivity {
    private Fmenu pmenu;
    View v;
    Tags tg;
    SeekBar distance;
    public Context context;
    CheckBox educationCheck,foodCheck,moviesCheck,musicCheck,partyCheck,politicsCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        context = this;

        ImageButton menu = (ImageButton) findViewById(R.id.menuBtn);
        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pmenu = new Fmenu();
                pmenu.initiatePopupWindow(Preferences.this, v);
            }
        });
        educationCheck = (CheckBox) findViewById(R.id.checkBox);
        foodCheck = (CheckBox) findViewById(R.id.checkBox2);
        moviesCheck = (CheckBox) findViewById(R.id.checkBox3);
        musicCheck = (CheckBox) findViewById(R.id.checkBox4);
        partyCheck = (CheckBox) findViewById(R.id.checkBox5);
        politicsCheck = (CheckBox) findViewById(R.id.checkBox6);
        distance = (SeekBar) findViewById(R.id.seekBar);
        tg = new Tags(musicCheck.isChecked(), partyCheck.isChecked(),politicsCheck.isChecked(),moviesCheck.isChecked(),educationCheck.isChecked(),foodCheck.isChecked());
        educationCheck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("asd", "+" + educationCheck.isChecked());
                tg.setEducationTg(educationCheck.isChecked());
            }
        });
        foodCheck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("asd", "+" + foodCheck.isChecked());
                tg.setFoodTg(foodCheck.isChecked());
            }
        });
        musicCheck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("asd", "+" + musicCheck.isChecked());
                tg.setMusicTg(musicCheck.isChecked());
            }
        });
        partyCheck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("asd", "+" + partyCheck.isChecked());
                tg.setPartyTg(partyCheck.isChecked());
            }
        });
        politicsCheck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("asd", "+" + politicsCheck.isChecked());
                tg.setPolitcsTg(politicsCheck.isChecked());
            }
        });
        moviesCheck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("asd", "+" + moviesCheck.isChecked());
                tg.setMovieTg(moviesCheck.isChecked());
            }
        });


        try{

            String url = "http://moxie.cs.oswego.edu:19991/whatzon/updateUser";
            final RequestQueue queue1 = Volley.newRequestQueue(context);
            StringRequest sr = new StringRequest(com.android.volley.Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    Log.d("PREFERENCES",Login.id);
                   params.put("facebook", Login.id);
                    params.put("tags", tg.toString());
                    params.put("max_distance", "ffdfdf");
                    return params;
                }
            };
            queue1.add(sr);
            Log.d("WORKED0","FINEEEE");
        }
        catch (Exception e){
            e.printStackTrace();
            Log.d("LOGIN INFO", "Exception e");
        }




         distance.getProgress();

        Log.d("TAGS", tg.toString());
        Log.d("PROGRESS", distance.getProgress() + " INT ");

    }
    @Override
    public void onResume(){
        super.onResume();
         educationCheck = (CheckBox) findViewById(R.id.checkBox);
         foodCheck = (CheckBox) findViewById(R.id.checkBox2);
         moviesCheck = (CheckBox) findViewById(R.id.checkBox3);
         musicCheck = (CheckBox) findViewById(R.id.checkBox4);
         partyCheck = (CheckBox) findViewById(R.id.checkBox5);
         politicsCheck = (CheckBox) findViewById(R.id.checkBox6);
        distance = (SeekBar) findViewById(R.id.seekBar);
        tg = new Tags(musicCheck.isChecked(), partyCheck.isChecked(),politicsCheck.isChecked(),moviesCheck.isChecked(),educationCheck.isChecked(),foodCheck.isChecked());



        educationCheck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("asd", "+" + educationCheck.isChecked());
                tg.setEducationTg(educationCheck.isChecked());
            }
        });
        foodCheck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("asd", "+" + foodCheck.isChecked());
                tg.setFoodTg(foodCheck.isChecked());
            }
        });
        musicCheck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("asd", "+" + musicCheck.isChecked());
                tg.setMusicTg(musicCheck.isChecked());
            }
        });
        partyCheck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("asd", "+" + partyCheck.isChecked());
                tg.setPartyTg(partyCheck.isChecked());
            }
        });
        politicsCheck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("asd", "+" + politicsCheck.isChecked());
                tg.setPolitcsTg(politicsCheck.isChecked());
            }
        });
        moviesCheck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("asd", "+" + moviesCheck.isChecked());
                tg.setMovieTg(moviesCheck.isChecked());
            }
        });


        try{

            String url = "http://moxie.cs.oswego.edu:19991/whatzon/updateUser";
            Log.d("WORKING", "EHAHHAAHAHAHAHA");
            final RequestQueue queue1 = Volley.newRequestQueue(context);
            StringRequest sr = new StringRequest(com.android.volley.Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("WORKING", response);
                }
            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("WORKING", error.toString());
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    Log.d("PREFERENCES On RESUME",Login.id);
                    Log.d("PREFERENCES On RESUME",tg.toString());
                    params.put("facebook",Login.id);
                    params.put("tags", tg.toString());
                    params.put("max_distance", "452");
                    return params;
                }
            };
            queue1.add(sr);

        }
        catch (Exception e){
            e.printStackTrace();
            Log.d("UPDATE ERRRO", "Exception e");
        }


         Log.d("TAGS", tg.toString());
        Log.d("PROGRESS",distance.getProgress()+" INT ");

        }

    @Override
    public void onBackPressed() {
        educationCheck = (CheckBox) findViewById(R.id.checkBox);
        foodCheck = (CheckBox) findViewById(R.id.checkBox2);
        moviesCheck = (CheckBox) findViewById(R.id.checkBox3);
        musicCheck = (CheckBox) findViewById(R.id.checkBox4);
        partyCheck = (CheckBox) findViewById(R.id.checkBox5);
        politicsCheck = (CheckBox) findViewById(R.id.checkBox6);
        distance = (SeekBar) findViewById(R.id.seekBar);
        tg = new Tags(musicCheck.isChecked(), partyCheck.isChecked(),politicsCheck.isChecked(),moviesCheck.isChecked(),educationCheck.isChecked(),foodCheck.isChecked());



        educationCheck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("asd", "+" + educationCheck.isChecked());
                tg.setEducationTg(educationCheck.isChecked());
            }
        });
        foodCheck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("asd", "+" + foodCheck.isChecked());
                tg.setFoodTg(foodCheck.isChecked());
            }
        });
        musicCheck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("asd", "+" + musicCheck.isChecked());
                tg.setMusicTg(musicCheck.isChecked());
            }
        });
        partyCheck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("asd", "+" + partyCheck.isChecked());
                tg.setPartyTg(partyCheck.isChecked());
            }
        });
        politicsCheck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("asd", "+" + politicsCheck.isChecked());
                tg.setPolitcsTg(politicsCheck.isChecked());
            }
        });
        moviesCheck.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("asd", "+" + moviesCheck.isChecked());
                tg.setMovieTg(moviesCheck.isChecked());
            }
        });


        try{

            String url = "http://moxie.cs.oswego.edu:19991/whatzon/updateUser";
            Log.d("WORKING", "EHAHHAAHAHAHAHA");
            final RequestQueue queue1 = Volley.newRequestQueue(context);
            StringRequest sr = new StringRequest(com.android.volley.Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("WORKING", response);
                }
            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("WORKING", error.toString());
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    Log.d("PREFERENCES On RESUME",Login.id);
                    Log.d("PREFERENCES On RESUME", tg.toString());
                    params.put("facebook",Login.id);
                    params.put("tags", tg.toString());
                    params.put("max_distance", ""+distance.getProgress());
                    return params;
                }
            };
            queue1.add(sr);

        }
        catch (Exception e){
            e.printStackTrace();
            Log.d("UPDATE ERRRO", "Exception e");
        }


        Log.d("TAGS", tg.toString());
        Log.d("PROGRESS",distance.getProgress()+" INT ");


        Log.d("CDA", "onBackPressed Called");
        moveTaskToBack(true);
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
