package suny.com.softwareeng;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lucas on 3/25/2015.
 */
public class AddEvent extends Activity{

    ArrayList<Event> event = new ArrayList<>();
    EditText title;
    EditText location;
    EditText time;
    EditText date;
    ImageButton ok,no;
    Tags tags;
    EditText moreInfo;
    Event ev;
    Button browser;
    CheckBox musCheck,partCheck,movCheck,eduCheck,politCheck;
    String titleStr, locationStr,moreInfoStr, dateStr,timeStr;
    public void add(View view){

        EditText title = (EditText) findViewById(R.id.Title);
        String t = title.getText().toString();

        EditText loc = (EditText) findViewById(R.id.Location);
        String l = loc.getText().toString();

        EditText date = (EditText) findViewById(R.id.Date);
        String d = date.getText().toString();

        EditText time = (EditText) findViewById(R.id.Time);
        String ti = time.getText().toString();

        EditText description = (EditText) findViewById(R.id.MoreInfo);
        String des = description.getText().toString();

        final String nameM = t;
        final String timeM = ti;
        final String dateM = d;
        final String whereM = l;
        final String descriptionM = des;
        final String authorM = "@getUserName()";
        final String pictureM = "@getPicture";

        String url = "http://moxie.cs.oswego.edu:19991/whatzon/add";

        final RequestQueue queue1 = Volley.newRequestQueue(this);

        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", nameM);
                params.put("time", timeM);
                params.put("date", dateM);
                params.put("where", whereM);
                params.put("description", descriptionM);
                params.put("author", authorM);
                params.put("picture", pictureM);


                return params;
            }
        };


        queue1.add(sr);
        String test = Login.name;
         Log.d("NAME", test);

        Intent intent = new Intent(this, MenuPage.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        tags = new Tags(false,false,false,false,false);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addevent);
        ok = (ImageButton) findViewById(R.id.btnYes);
        no = (ImageButton) findViewById(R.id.btnNo);
        final Context context = this;
        browser = (Button) findViewById(R.id.browseBtn);
        musCheck =(CheckBox) findViewById(R.id.musicBox);
        partCheck =(CheckBox) findViewById(R.id.partyBox);
        movCheck= (CheckBox) findViewById(R.id.moviesBox);
        eduCheck =(CheckBox) findViewById(R.id.educationBox);
        politCheck =(CheckBox) findViewById(R.id.politcsBox);
        title = (EditText) findViewById(R.id.Title);
        date = (EditText) findViewById(R.id.Date);
        time = (EditText) findViewById(R.id.Time);
        moreInfo = (EditText) findViewById(R.id.MoreInfo);
        location = (EditText) findViewById(R.id.Location);

        String test = Login.name;
       // Log.d("NAME", test);
       /* ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (title.getText().toString().trim().length() != 0)
                    titleStr = title.getText().toString();
                if (location.getText().toString().trim().length() != 0)
                    locationStr = location.getText().toString();
                if (moreInfo.getText().toString().trim().length() != 0)
                    moreInfoStr = moreInfo.getText().toString();
                if (date.getText().toString().trim().length() != 0)
                    dateStr = date.getText().toString();

                if (time.getText().toString().trim().length() != 0)
                    timeStr = time.getText().toString();
                // Log.d("TEST",dateStr);
                tags.setMusicTg(musCheck.isChecked());
                tags.setPartyTg(partCheck.isChecked());
                tags.setMovieTg(movCheck.isChecked());
                tags.setEducationTg(eduCheck.isChecked());
                tags.setPolitcsTg(politCheck.isChecked());


                ev = new Event(titleStr, locationStr, moreInfoStr, dateStr, timeStr, tags);
                event.add(ev);
                Intent intent = new Intent(context, MenuPage.class);
                startActivity(intent);
                //   Log.d("Events",event.get(0).getTitle());
                Log.d("TAGS", tags.toString());
                Log.isLoggable("Events", event.size());
                for (int i = 0; i < event.size(); i++) {
                    Log.d("Events", event.get(i).toString());
                }
            }
        });*/
        browser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), 1);

            }
            });
        no.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(title.getText().toString().trim().length() != 0)
                 title.setText("");
                if(location.getText().toString().trim().length() != 0)
                    location.setText("");
                if(moreInfo.getText().toString().trim().length() != 0)
                    moreInfo.setText("");
                if(date.getText().toString().trim().length() != 0){
                    date.setText("");
                if(time.getText().toString().trim().length() != 0)
                     time.setText("");
                }
            }
        });
        ImageButton menu = (ImageButton) findViewById(R.id.menuBtn);
        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, Events.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode == RESULT_OK) {
            if (requestCode == 1)
                Log.d("Image",data.getData().toString());

        }

    }

}
