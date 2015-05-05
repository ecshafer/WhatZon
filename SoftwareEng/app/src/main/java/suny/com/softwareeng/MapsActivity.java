package suny.com.softwareeng;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;

import java.util.List;


public class MapsActivity extends FragmentActivity {
    /**
     * Local variables *
     */
    GoogleMap googleMap;
    private SupportMapFragment mapFrag;
    private GoogleMap map;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Marker marker;
    private Polyline polyline;
    private List<LatLng> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        GoogleMapOptions options = new GoogleMapOptions();
        options.zOrderOnTop(true);
        SupportMapFragment mapFrag = SupportMapFragment.newInstance(options);

        configMap();
    }

    public void configMap(){
        map = mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(
                R.id.mapView)).getMap();


        LatLng latLng = new LatLng(43.4542984,-76.5411241);

        CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(16).bearing(0).tilt(0).build();
        CameraUpdate update = CameraUpdateFactory.newCameraPosition(cameraPosition);

        map.moveCamera(update);
        map.animateCamera(update, 3000, new GoogleMap.CancelableCallback(){
            @Override
            public void onCancel() {
                Log.i("Script", "CancelableCallback.onCancel()");
            }


            @Override
            public void onFinish() {
                Log.i("Script", "CancelableCallback.onFinish()");
            }
        });
        //Markers
        addMarker(new LatLng(43.4542984,-76.5411241), "Test Place", "Event: Description blablabla ");

    }



    private void createMapView() {
        /**
         * Catch the null pointer exception that
         * may be thrown when initialising the map
         */
        try {
            if (null == googleMap) {
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.mapView)).getMap();

                /**
                 * If the map is still null after attempted initialisation,
                 * show an error to the user
                 */
                if (null == googleMap) {
                    Toast.makeText(getApplicationContext(),
                            "Error creating map", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception) {
            Log.e("mapApp", exception.toString());
        }
    }

    private void addMarker(LatLng latlng, String title, String snippet ) {
        MarkerOptions options = new MarkerOptions();
        options.position(latlng).title(title).snippet(snippet).draggable(true);
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.iconmap));
        marker = map.addMarker(options);


        /** Make sure that the map has been initialised **/


        map.setInfoWindowAdapter(new InfoWindowAdapter() {

            @Override
            public View getInfoContents(Marker marker) {
                TextView tv = new TextView(MapsActivity.this);
                tv.setText(Html.fromHtml("<b><font color=\"#ff0000\">" + marker.getTitle() + ":</font></b> " + marker.getSnippet()));

                return tv;
            }

            @Override
            public View getInfoWindow(Marker marker) {
                LinearLayout ll = new LinearLayout(MapsActivity.this);
                ll.setPadding(20, 20, 20, 20);
                ll.setBackgroundColor(Color.parseColor("#CC66FF"));


                TextView tv = new TextView(MapsActivity.this);
                tv.setText(Html.fromHtml("<b><font color=\"#ffffff\" size=24px>" + marker.getTitle() +
                                        ":</font></b><br>" + marker.getSnippet()));

                ll.addView(tv);

                return ll;
            }

        });
    }
}