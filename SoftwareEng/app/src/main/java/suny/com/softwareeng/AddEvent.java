package suny.com.softwareeng;

import android.app.Activity;

import android.content.Intent;
import android.graphics.Color;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class AddEvent extends Activity implements OnImageUploadedListener{


    EditText title,location,time,date,moreInfo;
    private Upload upload;
    ImageButton ok,no;
    private File chosenFile;
    Tags tags;
    int flag,f;
    String latitude,longitude,linkPicture;
    Button browser;
    private Fmenu pmenu;
    CheckBox musCheck,partCheck,movCheck,eduCheck,politCheck,foodCheck;

    @Override
    public void onImageUploaded(ImageResponse response) {
        browser = (Button) findViewById(R.id.browseBtn);
            if(response.success) {
                browser.setBackgroundColor((Color.parseColor("#00FF00")));
                browser.setText("Uploaded");
            }else{
                browser.setBackgroundColor((Color.parseColor("#fff00000")));
                browser.setText("Failed");
        }
        linkPicture = response.getLink();
    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    //Bundle bundle = message.getData();
                    latitude = Double.toString(GeocodingLocation.lat);
                    longitude = Double.toString(GeocodingLocation.longt);
                    break;
                default:
                    f=0;
            }
            f=1;
        }
    }


    public void callPopUp(View view, final String message){
        LayoutInflater layoutInflater
                = (LayoutInflater) getBaseContext()
                .getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.pop_up, null);
        final PopupWindow popupWindow = new PopupWindow(
                popupView,
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        ImageButton btnDismiss = (ImageButton) popupView.findViewById(R.id.dismiss);
        TextView textPop = (TextView) popupView.findViewById(R.id.text);
        textPop.setText(message);
        btnDismiss.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(message.equals("Event added")){
                    finish();
                }else{
                    popupWindow.dismiss();
                }

            }
        });

    }
    public void add(View view){
        flag =1;

        EditText title = (EditText) findViewById(R.id.Title);
        String  t = title.getText().toString();
        EditText loc = (EditText) findViewById(R.id.Location);
        String l = loc.getText().toString();
        EditText date = (EditText) findViewById(R.id.Date);
        String d = date.getText().toString();
        EditText time = (EditText) findViewById(R.id.Time);
        String ti = time.getText().toString();


        musCheck =(CheckBox) findViewById(R.id.musicBox);
        partCheck =(CheckBox) findViewById(R.id.partyBox);
        movCheck= (CheckBox) findViewById(R.id.moviesBox);
        eduCheck =(CheckBox) findViewById(R.id.educationBox);
        politCheck =(CheckBox) findViewById(R.id.politcsBox);
        foodCheck = (CheckBox) findViewById(R.id.foodBox);

        tags.setMusicTg(musCheck.isChecked());
        tags.setPartyTg(partCheck.isChecked());
        tags.setMovieTg(movCheck.isChecked());
        tags.setEducationTg(eduCheck.isChecked());
        tags.setPolitcsTg(politCheck.isChecked());
        tags.setFoodTg(foodCheck.isChecked());
        //
        if (t.trim().length() != 0 && l.trim().length() !=0 && d.trim().length() !=0 && ti.trim().length() != 0 && browser.getText().equals("Uploaded") ) {

            try {
                DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
                df.setLenient(false);
                df.parse(d);
                flag=0;
            } catch (ParseException e) {
                e.printStackTrace();
                flag=1;
                callPopUp(view,"Date wrong, use MM-dd-yyy");
            }
        }else{
            flag = 1;
            callPopUp(view,"* Means required");
        }

        EditText description = (EditText) findViewById(R.id.MoreInfo);
        String des = description.getText().toString();
        final String nameM = t;
        final String timeM = ti;
        final String dateM = d;
        final String whereM = l;
        final String descriptionM = des;
        final String tagsM = tags.toString();

        if(flag==0) {
            GeocodingLocation locationAddress = new GeocodingLocation();
            locationAddress.getAddressFromLocation(whereM,
                    getApplicationContext(), new GeocoderHandler());

            if (f == 0) {
                callPopUp(view, "Loading or Wrong Address. Please Try Again!!");
            } else {
                f = 1;
            }
        }
       if(flag ==0 && f == 1) {
            String url = "http://moxie.cs.oswego.edu:19991/whatzon/addEvent";
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
                    params.put("address", whereM);
                    params.put("date", dateM+"--"+timeM);
                    params.put("latitude", latitude);
                    params.put("description", descriptionM);
                    params.put("longitude", longitude);
                    params.put("tags", tagsM);
                    params.put("picture",linkPicture);
                    params.put("responsible", Login.id);
                    Calendar c = Calendar.getInstance();
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    int month = c.get(Calendar.MONTH);
                    int year = c.get(Calendar.YEAR);
                    params.put("last_update", month + "/" + day + "/" + year);
                    return params;
                }
            };
           queue1.add(sr);

            callPopUp(view,"Event Added");
        }
        f=0;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        tags = new Tags(false,false,false,false,false,false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addevent);
        ok = (ImageButton) findViewById(R.id.btnYes);
        no = (ImageButton) findViewById(R.id.btnNo);
        browser = (Button) findViewById(R.id.browseBtn);
        title = (EditText) findViewById(R.id.Title);
        date = (EditText) findViewById(R.id.Date);
        time = (EditText) findViewById(R.id.Time);
        moreInfo = (EditText) findViewById(R.id.MoreInfo);
        location = (EditText) findViewById(R.id.Location);
        f=0;


        browser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                browser.setText("Browse");
                browser.setBackgroundColor((Color.parseColor("#ff6e058f")));
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), 1);
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (title.getText().toString().trim().length() != 0)
                    title.setText("");
                if (location.getText().toString().trim().length() != 0)
                    location.setText("");
                if (moreInfo.getText().toString().trim().length() != 0)
                    moreInfo.setText("");
                if (date.getText().toString().trim().length() != 0)
                    date.setText("");
                if (time.getText().toString().trim().length() != 0)
                    time.setText("");
                if (moreInfo.getText().toString().trim().length() != 0)
                    moreInfo.setText("");

            }
        });

        ImageButton menu = (ImageButton) findViewById(R.id.menuBtn);
        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pmenu = new Fmenu();
                pmenu.initiatePopupWindow(AddEvent.this, v);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        Uri returnUri;
        if(resultCode == RESULT_OK) {
            if (requestCode == 1){
                Log.d("Image",data.getData().toString());
                    returnUri = data.getData();
                browser.setText("Uploading");
                browser.setBackgroundColor((Color.parseColor("#FFFF00")));
                    chosenFile = new File(DocumentHelper.getPath(this, returnUri));
                createUpload(chosenFile);
                new UploadService(upload, this).execute();
                }
            }
        }

    private void createUpload(File image){
        upload = new Upload();
        EditText title = (EditText) findViewById(R.id.Title);
        upload.image = image;
        upload.title = title.getText().toString();
        upload.description =" Image for event";
    }
}

