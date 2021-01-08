import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class IOHandler {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static Gson getGson(){ return gson; }

    private static HashMap<Integer, User> userHashMap = new HashMap<>();
    private static HashMap<Integer, Diary> diaryHashMap = new HashMap<>();

    public static void initialize() throws FileNotFoundException {
        initializeUsers();
        initializeDiaries();
    }

    public static void initializeUsers() throws FileNotFoundException {

        File userFolder = new File("users");

        if(!userFolder.isDirectory()) userFolder.mkdir();

        for(File file : userFolder.listFiles()){
            Scanner scanner = new Scanner(file);
            String text = scanner.next();
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


}
