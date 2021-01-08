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
            String text = "";
            while(scanner.hasNext()) text = text+scanner.next();
            System.out.println(text);
            User user = User.deserialize(text);
            userHashMap.put(user.getUserID(), user);
        }
    }

    public static void initializeDiaries() throws FileNotFoundException {
        File diaryFolder = new File("diaries");

        if(!diaryFolder.isDirectory()) diaryFolder.mkdir();

        for(File file : diaryFolder.listFiles()){
            Scanner scanner = new Scanner(file);
            String text = scanner.next();
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
        String jsonObject = "";
        while(scanner.hasNext()) jsonObject = jsonObject+scanner.next();
        return ConfigHandler.deserialize(jsonObject);
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

    private static void saveConfig() throws IOException {
        File file = new File("timelineDiary.json");
        FileWriter writer = new FileWriter(file, false);
        writer.write(Main.getConfig().serialize());
        writer.flush();
        writer.close();
    }
}
