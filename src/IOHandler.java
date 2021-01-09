import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class IOHandler {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

    public static Gson getGson(){ return gson; }

    private static HashMap<Integer, User> userHashMap = new HashMap<>();
    private static HashMap<Integer, Diary> diaryHashMap = new HashMap<>();

    public static void initialize() throws FileNotFoundException {
        try{
            initializeUsers();
            initializeDiaries();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void initializeUsers() throws FileNotFoundException {

        File userFolder = new File("users");

        if(!userFolder.isDirectory()) userFolder.mkdir();

        for(File file : userFolder.listFiles()){
            Scanner scanner = new Scanner(file);
            StringBuilder text = new StringBuilder();
            while(scanner.hasNext()) text.append(scanner.next());
            User user = User.deserialize(text.toString());
            userHashMap.put(user.getUserID(), user);
        }
    }

    public static void initializeDiaries() throws FileNotFoundException {
        File diaryFolder = new File("diaries");

        if(!diaryFolder.isDirectory()) diaryFolder.mkdir();

        for(File file : diaryFolder.listFiles()){
            Scanner scanner = new Scanner(file);
            String text = "";
            while(scanner.hasNext()) text= text+scanner.next()+" ";
            Diary diary = Diary.deserialize(text);
            diaryHashMap.put(diary.getDiaryID(), diary);
        }
    }

    public static ConfigHandler getConfigHandler() throws IOException {
        File configFile = new File("timelineDiary.json");
        if(!configFile.isFile()){
            configFile.createNewFile();
            return new ConfigHandler();
        }
        Scanner scanner = new Scanner(configFile);
        StringBuilder jsonObject = new StringBuilder();
        while(scanner.hasNext()) jsonObject.append(scanner.next());
        return ConfigHandler.deserialize(jsonObject.toString());
    }

    public static void addUser(String password, String username) {
        try {
            User user = new User(username, password);
            userHashMap.put(user.getUserID(), user);
            saveUsers();
            saveConfig();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void saveUsers() throws IOException {
        for (Integer userID : userHashMap.keySet()){
            File file = new File("users/"+userID+".json");
            if(!file.exists()) file.createNewFile();
            FileWriter writer = new FileWriter(file, false);
            writer.write(userHashMap.get(userID).serialize());
            writer.flush();
            writer.close();
        }
    }

    public static void addDiary(String title, String text, String weather, User user){
        try {
            Diary newDiary = new Diary(user.getUserID(), title, text, weather);
            diaryHashMap.put(newDiary.getDiaryID(), newDiary);
            saveDiaries();
            saveConfig();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void saveDiaries() throws IOException {
        for(Integer diaryID : diaryHashMap.keySet()){
            File file = new File("diaries/"+diaryID+".json");
            if(!file.exists()) file.createNewFile();
            FileWriter writer = new FileWriter(file, false);
            writer.write(diaryHashMap.get(diaryID).serialize());
            writer.flush();
            writer.close();
        }
    }

    static void saveConfig() throws IOException {
        File file = new File("timelineDiary.json");
        FileWriter writer = new FileWriter(file, false);
        writer.write(Main.getConfig().serialize());
        writer.flush();
        writer.close();
    }

    public static HashMap<Integer, Diary> getDiaryHashMap() {
        return diaryHashMap;
    }

    public static HashMap<Integer, User> getUserHashMap() {
        return userHashMap;
    }

    public static void resetDiaryFolder() throws IOException {
        File diaryFolder = new File("diaries");
        if(!diaryFolder.isDirectory()) diaryFolder.mkdir();
        for(File file : diaryFolder.listFiles()){
            if(file.isFile()) System.out.println(file.delete());
        }
        saveDiaries();
    }
}
