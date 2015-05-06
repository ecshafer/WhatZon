package suny.com.softwareeng;

import android.app.AlertDialog;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.location.Location;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;


public class Wait extends ActionBarActivity implements GoogleApiClient.ConnectionCallbacks,  GoogleApiClient.OnConnectionFailedListener{


    private TextView tvCoordinate;
    private GoogleApiClient mGoogleApiClient;
    private Fmenu pmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wait);
        ImageView img =(ImageView)findViewById(R.id.imgGif);

        img.setBackgroundResource(R.drawable.gifwhatzon);
        AnimationDrawable frameAnimation=(AnimationDrawable) img.getBackground();
        frameAnimation.start();

        tvCoordinate = (TextView) findViewById(R.id.tv_coordinate);
        callConnection();


        ImageButton menu = (ImageButton) findViewById(R.id.menuBtn);
        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pmenu = new Fmenu();
                pmenu.initiatePopupWindow(Wait.this, v);
            }
        });
    }





    private synchronized void callConnection(){
        mGoogleApiClient = new GoogleApiClient.Builder(this).addOnConnectionFailedListener(this).addConnectionCallbacks(this).addApi(LocationServices.API).build();
        mGoogleApiClient.connect();
    }



    // LISTENER

    @Override

    public void onConnected(Bundle bundle) {
        Location l = LocationServices
                .FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(l != null){

            tvCoordinate.setText(l.getLatitude()+" | "+l.getLongitude());
        }

    }


    @Override

    public void onConnectionSuspended(int i) {
        final AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        builder.setMessage("No new events at this moment");
        builder.create().show();

    }

    @Override

    public void onConnectionFailed(ConnectionResult connectionResult) {
        final AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        builder.setMessage("No new events at this moment");
        builder.create().show();

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
