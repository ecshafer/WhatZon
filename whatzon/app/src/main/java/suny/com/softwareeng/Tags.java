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
    public Tags(boolean musicTg,boolean partyTg,boolean politicsTg, boolean movieTg, boolean educationTg){
        this.musicTg = musicTg;
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

    @Override
    public String toString(){
        return "Music:"+getMusicTg()+"\nParty"+getpartyTg()+"\nPolitics"+getPolitcsTg()+"\nEducation"+geteducationTg()+"\nMovies"+getMovieTg();
    }



}
