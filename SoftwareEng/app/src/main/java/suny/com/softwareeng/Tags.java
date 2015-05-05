package suny.com.softwareeng;

/**
 * Created by Lucas on 4/6/2015.
 */
public class Tags {

    private boolean musicTg;
    private boolean partyTg;
    private boolean politicsTg;
    private boolean movieTg;
    private boolean educationTg;
    private boolean foodTg;
    public Tags(boolean musicTg,boolean partyTg,boolean politicsTg, boolean movieTg, boolean educationTg,boolean foodTg){
        this.musicTg = musicTg;
        this.foodTg = foodTg;
        this.partyTg = partyTg;
        this.politicsTg = politicsTg;
        this.movieTg = movieTg;
        this.educationTg = educationTg;
    }

    public void setMusicTg(boolean musicTg) {
        this.musicTg = musicTg;
    }
    public boolean getMusicTg() throws IllegalStateException {
        return musicTg;
    }
    public void setPartyTg(boolean partyTg) {
        this.partyTg = partyTg;
    }
    public boolean getpartyTg() throws IllegalStateException {
        return partyTg;
    }
    public void setPolitcsTg(boolean politicsTg) {
        this.politicsTg = politicsTg;
    }
    public boolean getPolitcsTg() throws IllegalStateException {
        return politicsTg;
    }
    public void setMovieTg(boolean movieTg) {
        this.movieTg = movieTg;
    }
    public boolean getMovieTg() throws IllegalStateException {
        return movieTg;
    }
    public void setEducationTg(boolean educationTg) {
        this.educationTg = educationTg;
    }
    public boolean geteducationTg() throws IllegalStateException {
        return educationTg;

    }
    public void setFoodTg(boolean foodTg) {
        this.foodTg = foodTg;
    }
    public boolean getFoodTg() throws IllegalStateException {
        return foodTg;

    }
    @Override
    public String toString(){
        String tags="";
        int flag = 0;
        if(!getMusicTg()==false){
            tags+="Music";
            flag=1;
        }
        if(flag == 1){
            tags+=",";
            flag=0;
        }
        if(!geteducationTg()==false)
            tags+="Education,";
        if(flag == 1){
            tags+=",";
            flag=0;
        }
        if(!getMovieTg()==false)
            tags+="Movie,";
        if(flag == 1){
            tags+=",";
            flag=0;
        }

        if(!getpartyTg()==false)
            tags+="Party,";
        if(flag == 1){
            tags+=",";
            flag=0;
        }

        if(!getPolitcsTg()==false)
            tags+="Politics,";
        if(flag == 1){
            tags+=",";

        }
        if(!getFoodTg()==false)
            tags+="Food,";
        if(flag == 1){
            tags+=",";

        }
        if(tags.length() != 0)
        tags= tags.substring(0,(tags.length()-1));

        tags+=".";



        return tags;
    }



}
