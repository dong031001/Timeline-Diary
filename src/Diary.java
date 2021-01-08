import java.time.Instant;
import java.util.Date;

public class Diary {

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
}
