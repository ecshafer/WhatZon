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
    private String date;
    private String time;
    private Tags tags;
    private String moreInfo;

    public Event( String title,String location,String moreInfo,String date,String time,Tags tags){

        this.title = title;
        this.location = location;
        this.date = date;
        this.time= time;
        this.moreInfo = moreInfo;
        this.tags = tags;
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
    public String getDate() throws IllegalStateException {
        return date;
    }
    public String getTime() throws IllegalStateException {
        return time;
    }
    public String getTags(){ return tags.toString();}
    public Tags getTagsValues(){return tags;}

    @Override
    public String toString() {
        return "Title:"+getTitle()+"\nLocation:"+getLocation()+"\nInformation"+getMoreInfo()+"\nDate"+getDate()+"\nTime"+getTime();
    }
}
