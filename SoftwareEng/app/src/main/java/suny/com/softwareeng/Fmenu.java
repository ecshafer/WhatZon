package suny.com.softwareeng;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupWindow;

public class Fmenu extends Activity {
    private PopupWindow pwindo;
    private View layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fmenu);
    }

    public boolean initiatePopupWindow(final Context c, View v) {
        try {
// We need to get the instance of the LayoutInflater

            int w = v.getDisplay().getWidth();  // deprecated
            int h = v.getDisplay().getHeight();

            LayoutInflater inflater = (LayoutInflater) c
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.fragment_fmenu,
                    (ViewGroup) v.findViewById(R.id.popup));
            pwindo = new PopupWindow(layout, w, h, true);
            pwindo.setAnimationStyle(R.style.popwin_anim_style);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

            ImageButton Settings = (ImageButton) layout.findViewById(R.id.btnSettings);
            Settings.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(c, Preferences.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    c.startActivity(intent);
                }
            });
            ImageButton Profile = (ImageButton) layout.findViewById(R.id.btnProfile);
            Profile.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    c.startActivity(new Intent(c, UserProfile.class));
                }
            });
            ImageButton NewEvent = (ImageButton) layout.findViewById(R.id.btnNew);
            NewEvent.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    c.startActivity(new Intent(c, AddEvent.class));
                }
            });
            ImageButton Calendar = (ImageButton) layout.findViewById(R.id.btnCalendar);
            Calendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    c.startActivity(new Intent(c, Calendar.class));
                }
            });

            ImageButton close = (ImageButton) layout.findViewById(R.id.btnClose);
            close.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    pwindo.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return(true);
    }


}