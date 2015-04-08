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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Date;

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
    CheckBox musCheck,partCheck,movCheck,eduCheck,politCheck;
    String titleStr, locationStr,moreInfoStr, dateStr,timeStr;
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
        Log.d("NAME",test);
        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(title.getText().toString().trim().length() != 0)
                    titleStr = title.getText().toString();
                if(location.getText().toString().trim().length() != 0)
                    locationStr = location.getText().toString();
                if(moreInfo.getText().toString().trim().length() != 0)
                    moreInfoStr = moreInfo.getText().toString();
                if(date.getText().toString().trim().length() != 0)
                    dateStr = date.getText().toString();

                if(time.getText().toString().trim().length() != 0)
                    timeStr = time.getText().toString();
               // Log.d("TEST",dateStr);
                tags.setMusicTg(musCheck.isChecked());
                tags.setPartyTg(partCheck.isChecked());
                tags.setMovieTg(movCheck.isChecked());
                tags.setEducationTg(eduCheck.isChecked());
                tags.setPolitcsTg(politCheck.isChecked());


                ev = new Event(titleStr,locationStr,moreInfoStr,dateStr,timeStr,tags);
                event.add(ev);
                Intent intent = new Intent(context, MenuPage.class);
                startActivity(intent);
             //   Log.d("Events",event.get(0).getTitle());
                Log.d("TAGS",tags.toString());
                Log.isLoggable("Events",event.size());
                for(int i=0;i<event.size();i++){
                    Log.d("Events",event.get(i).toString());
                }
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

}
