package suny.com.softwareeng;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;


public class Wait extends ActionBarActivity implements GoogleApiClient.ConnectionCallbacks,  GoogleApiClient.OnConnectionFailedListener{

    private LocationManager locationManager;
    private boolean allowNetwork;
    private TextView tvCoordinate;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wait);
        ImageView img =(ImageView)findViewById(R.id.imgGif);

        img.setBackgroundResource(R.drawable.gifwhatzon);
        AnimationDrawable frameAnimation=(AnimationDrawable) img.getBackground();
        frameAnimation.start();

        tvCoordinate = (TextView) findViewById(R.id.tv_coordinate);
        callConnection();
        final Context context = this;
        ImageButton menu = (ImageButton) findViewById(R.id.btnMenu);
        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, MenuPage.class);


                startActivity(intent);
            }
        });
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

            Log.i("LOG", "latitude: "+l.getLatitude());

            Log.i("LOG", "longitude: "+l.getLongitude());

            tvCoordinate.setText(l.getLatitude()+" | "+l.getLongitude());
        }

    }


    @Override

    public void onConnectionSuspended(int i) {

        Log.i("LOG", "onConnectionSuspended(" + i + ")");
        final AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        builder.setMessage("Connection with location devices is suspended: onConnectionSuspended(" + i + ")");
        builder.create().show();

    }

    @Override

    public void onConnectionFailed(ConnectionResult connectionResult) {

        Log.i("LOG", "onConnectionFailed("+connectionResult+")");
        final AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        builder.setMessage("Connection with location devices is unable: onConnectionFailed("+connectionResult+")");
        builder.create().show();

    }

}
