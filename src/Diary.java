import java.time.Instant;
import java.util.Date;

public class Diary implements Comparable<Diary> {

    private int diaryID;
    private int authorID;
    private String title;
    private String text;
    private Date creationDate;
    private Date lastModifiedDate;
    private String weather;

    public Diary(int authorID, String title, String text, String weather){
        this.authorID = authorID;
        this.title = title;
        this.text = text;
        this.weather = weather;
        this.creationDate = Date.from(Instant.now());
        this.lastModifiedDate = Date.from(Instant.now());
        this.diaryID = Main.getNewDiaryID();
    }

    public void update(String title, String text, String weather){
        this.title = title;
        this.text = text;
        this.weather = weather;
        this.lastModifiedDate = Date.from(Instant.now());
    }

    public String serialize(){
        return IOHandler.getGson().toJson(this);
    }

    public static Diary deserialize(String json){
        return IOHandler.getGson().fromJson(json, Diary.class);
    }

    public int getDiaryID() {
        return diaryID;
    }

    @Override
    public int compareTo(Diary o) {
        if(this.creationDate.after(o.creationDate)) return 1;
        if(this.creationDate.before(o.creationDate)) return -1;
        return 0;
    }

    public Integer getUserID() {
        return authorID;
    }

    public String getTitle() {
        return title;
    }

    public String getWeather() {
        return weather;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getDate() {
        return creationDate.toLocaleString();
    }

    public int getLength() {
        return text.length();
    }

    public String getText() {
        return text;
    }
}
