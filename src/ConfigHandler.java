public class ConfigHandler {

    private int newDiaryID = 0;
    private int newUserID = 0;

    public int getNewDiaryID() {
        return ++newDiaryID;
    }

    public int getNewUserID() {
        return ++newUserID;
    }

    public String serialize(){
        return IOHandler.getGson().toJson(this, ConfigHandler.class);
    }

    public static ConfigHandler deserialize(String jsonObject){
        return IOHandler.getGson().fromJson(jsonObject, ConfigHandler.class);
    }
}
