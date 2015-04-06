package suny.com.softwareeng;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class Events extends Activity {

<<<<<<< HEAD
    private ArrayList<String> al;
    private ArrayAdapter<String> arrayAdapter;
=======

>>>>>>> SaraivaBranch
    private int i;
    public     SimpleAdapter adapter;
    public  List<HashMap<String,String>> aList;

    @InjectView(R.id.frame) SwipeFlingAdapterView flingContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
<<<<<<< HEAD


        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);
        ButterKnife.inject(this);


     /*   al = new ArrayList<>();
        al.add("php");
        al.add("c");
        al.add("python");
        al.add("java");
        al.add("html");
        al.add("c++");
        al.add("css");
        al.add("javascript");
*/



        // Array of strings storing country names
        String[] countries = new String[] {
                "India",
=======
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);
        ButterKnife.inject(this);
        // Array of strings storing country names
        String[] countries = new String[] {
                "Party",
>>>>>>> SaraivaBranch
                "Pakistan",
                "Sri Lanka",
                "China",
                "Bangladesh",
                "Nepal",
                "Afghanistan",
                "North Korea",
                "South Korea",
                "Japan"
        };
<<<<<<< HEAD

        // Array of integers points to images stored in /res/drawable-ldpi/
        int[] flags = new int[]{
                R.drawable.icon,
=======
        // Array of integers points to images stored in /res/drawable-ldpi/
        int[] flags = new int[]{
                R.drawable.party,
>>>>>>> SaraivaBranch
                R.drawable.icon,
                R.drawable.icon,
                R.drawable.icon,
                R.drawable.icon,
                R.drawable.icon,
                R.drawable.icon,
                R.drawable.icon,
                R.drawable.icon,
                R.drawable.icon
        };

<<<<<<< HEAD




            // Each row in the list stores country name, currency and flag
            aList = new ArrayList<HashMap<String,String>>();

            for(int i=0;i<10;i++){
                HashMap<String, String> hm = new HashMap<String,String>();
                hm.put("heloText", "Country : " + countries[i]);
                hm.put("picture", Integer.toString(flags[i]));
=======
        aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<10;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("EventName", "" + countries[i]);
             hm.put("picture", Integer.toString(flags[i]));
>>>>>>> SaraivaBranch
                Log.d("LIST", Integer.toString(flags[i]));
                aList.add(hm);
            }

            // Keys used in Hashmap
<<<<<<< HEAD
            String[] from = { "picture","heloText" };

            // Ids of views in listview_layout
            int[] to = { R.id.picture,R.id.helloText};

            // Instantiating an adapter to store each items
            // R.layout.listview_layout defines the layout of each item
             adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.item, from, to);

            // Getting a reference to listview of main.xml layout file
         //   ListView listView = ( ListView ) findViewById(R.id.listview);

=======
            String[] from = { "picture","EventName" };
            // Ids of views in listview_layout
            int[] to = { R.id.picture,R.id.eventText};
            // Instantiating an adapter to store each items
            // R.layout.listview_layout defines the layout of each item
             adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.item, from, to);
>>>>>>> SaraivaBranch
            // Setting the adapter to the listView
           // listView.setAdapter(adapter);
        final Context context = this;
       // arrayAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.helloText, al );
<<<<<<< HEAD
    ImageButton findOut = (ImageButton) findViewById(R.id.findOut);
        findOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, EventDetail.class);
              //  intent.putExtra("hash",aList.get(0));


                startActivity(intent);
            }
        });
        ImageButton menu = (ImageButton) findViewById(R.id.ImageButton01);
        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, MenuPage.class);


                startActivity(intent);
=======
       ImageButton findOut = (ImageButton) findViewById(R.id.findOut);
        findOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, EventDetail.class);
                intent.putExtra("hash",aList.get(0));
                startActivityForResult(intent,1);
            }
        });
        ImageButton menu = (ImageButton) findViewById(R.id.menuBtn);
        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, MenuPage.class);
                startActivityForResult(intent,1);
>>>>>>> SaraivaBranch
            }
        });

        flingContainer.setAdapter(adapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                Log.d("LIST", "removed object!");
                aList.remove(0);
<<<<<<< HEAD

=======
>>>>>>> SaraivaBranch
               adapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                makeToast(Events.this, "Left!");
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                makeToast(Events.this, "Right!");
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                Intent intent = new Intent(context, Wait.class);
                startActivity(intent);
            }

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
<<<<<<< HEAD
                makeToast(Events.this, "Clicked!");

                Intent intent = new Intent(context, EventDetail.class);
                startActivity(intent);
=======
               // makeToast(Events.this, "Clicked!");
                Intent intent = new Intent(context, EventDetail.class);
                startActivityForResult(intent,1);
>>>>>>> SaraivaBranch
            }
        });

    }

    static void makeToast(Context ctx, String s){
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }

<<<<<<< HEAD

    @OnClick(R.id.right)
    public void right() {
        Calendar cal = Calendar.getInstance();
=======
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
       /*Calendar cal = Calendar.getInstance();
>>>>>>> SaraivaBranch
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", cal.getTimeInMillis());
        intent.putExtra("allDay", true);
        intent.putExtra("rrule", "FREQ=YEARLY");
        intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
        intent.putExtra("title", "A Test Event from android app");
<<<<<<< HEAD
        startActivity(intent);
=======
        startActivity(intent);*/
>>>>>>> SaraivaBranch
        flingContainer.getTopCardListener().selectRight();
    }

    @OnClick(R.id.left)
    public void left() {
        flingContainer.getTopCardListener().selectLeft();
    }





}
