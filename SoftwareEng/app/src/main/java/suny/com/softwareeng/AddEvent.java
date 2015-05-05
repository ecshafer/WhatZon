package suny.com.softwareeng;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class AddEvent extends Activity implements OnImageUploadedListener{

    ArrayList<Event> event = new ArrayList<>();
    EditText title;
    EditText location;
    EditText time;
    EditText date;
    private Upload upload;
    ImageButton ok,no;
    private File chosenFile;
    Tags tags;
    EditText moreInfo;
    int flag;
    Event ev;
    String t;
    Button browser;
    private Fmenu pmenu;
    public View view2;
    CheckBox musCheck,partCheck,movCheck,eduCheck,politCheck,foodCheck;
    String linkPicture;
    @Override
    public void onImageUploaded(ImageResponse response) {
        Log.d("TESTE", response.toString());
        linkPicture = response.getLink();
    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            Log.d("Long", locationAddress);
        }
    }


    public void callPopUp(View view,String message){
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
                // TODO Auto-generated method stub
                popupWindow.dismiss();
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

        browser = (Button) findViewById(R.id.browseBtn);
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
        if (t.trim().length() != 0 && l.trim().length() !=0 && d.trim().length() !=0 && ti.trim().length() != 0) {
            flag=0;
        }else
        {
            flag = 1;
            Log.d("ELSE", "ELSE");
            callPopUp(view,"* Means required");
        }

        EditText description = (EditText) findViewById(R.id.MoreInfo);
        String des = description.getText().toString();


        final String nameM = t;
        final String timeM = ti;
        final String dateM = d;
        final String whereM = l;
        final String descriptionM = des;
        final String authorM = Login.name;

        final String tagsM = tags.toString();


        GeocodingLocation locationAddress = new GeocodingLocation();
        locationAddress.getAddressFromLocation(whereM,
                getApplicationContext(), new GeocoderHandler());
       // Log.d("Name",authorM);
        final String pictureM = "@getPicture";

        final String latitude = Double.toString(locationAddress.lat);
        final String longitude = Double.toString(locationAddress.longt);

        Log.d(" Lat e Log"," lat + log  "+latitude);
        Log.d(" Lat e Log"," lat + log  "+longitude);

        if(flag ==0) {

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
                    params.put("picture", pictureM);
                    params.put("tags", tagsM);
                    params.put("picture",linkPicture);
                    params.put("responsible", Login.id);
                    Calendar c = Calendar.getInstance();
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    int month = c.get(Calendar.MONTH);
                    int year = c.get(Calendar.YEAR);
                    params.put("last_update", month+"/"+day+"/"+year);

                    Log.d("End","End");
                    return params;

                }
            };


            queue1.add(sr);
            callPopUp(view,"Event Added");

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        tags = new Tags(false,false,false,false,false,false);
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

