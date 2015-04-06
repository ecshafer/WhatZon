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

import android.widget.ImageButton;


import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Lucas on 3/25/2015.
 */
public class AddEvent extends Activity{

    ArrayList<Event> event = new ArrayList<Event>();
    EditText title;
    EditText location;
    EditText date;
    ImageButton ok;
    EditText moreInfo;
    Event ev;
    Date dateStr;
    String titleStr, locationStr,moreInfoStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

                super.onCreate(savedInstanceState);
        setContentView(R.layout.addevent);
        ImageButton ok = (ImageButton) findViewById(R.id.btnYes);

        final Context context = this;
        //Remove notification bar
        title = (EditText) findViewById(R.id.Title);
        date = (EditText) findViewById(R.id.Date);
        moreInfo = (EditText) findViewById(R.id.MoreInfo);
        location = (EditText) findViewById(R.id.Location);

        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(title.getText().toString().trim().length() != 0)
                    titleStr = title.getText().toString();
                if(location.getText().toString().trim().length() != 0)
                    locationStr = location.getText().toString();
                if(moreInfo.getText().toString().trim().length() != 0)
                    moreInfoStr = moreInfo.getText().toString();
                ev = new Event(titleStr,locationStr,moreInfoStr);
                event.add(ev);
                Intent intent = new Intent(context, MenuPage.class);
                startActivity(intent);
             //   Log.d("Events",event.get(0).getTitle());
                Log.isLoggable("Events",event.size());
                for(int i=0;i<event.size();i++){
                    Log.d("Events",event.get(i).toString());
                }
            }
        });

        ImageButton menu = (ImageButton) findViewById(R.id.menuBtn);
        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, MenuPage.class);
>>>>>>> SaraivaBranch
                startActivity(intent);
            }
        });
    }

}
