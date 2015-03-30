package suny.com.softwareeng;

import android.media.Image;
import android.text.format.Time;

import java.util.Date;


/**
 * Created by Lucas on 3/19/2015.
 */
public class Event {

    private String picture;
    private String name;
    private String address;
    private Date date;
    private Time time;
    private String tags;


    public Event(String picture, String name){
        this.picture =picture;
        this.name = name;
    }


}
