package suny.com.softwareeng;

import android.media.Image;
import android.text.format.Time;

import java.util.Date;


/**
 * Created by Lucas on 3/19/2015.
 */
public class Event {

    private String picture;

    private String title;
    private String location;
    private Date date;
    private Time time;
    private String tags;
    private String moreInfo;

    public Event( String title,String location,  String moreInfo){

        this.title = title;
        this.location = location;
        this.moreInfo = moreInfo;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocation() throws IllegalStateException {
        return location;
    }
    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }
    public String getMoreInfo() throws IllegalStateException {
        return moreInfo;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() throws IllegalStateException {
        return title;
    }

    @Override
    public String toString() {
        return "Title:"+getTitle()+"\nLocation:"+getLocation()+"\nInformation"+getMoreInfo();
    }

}
