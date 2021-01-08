import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static ConfigHandler config = null;

    public static User currentUser = null;

    public static JFrame frame = null;

    public static void main(String[] args) throws IOException {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        config = IOHandler.getConfigHandler();
        IOHandler.initialize();

        frame = new JFrame("Timeline Diary");
        frame.setContentPane(new Login().getLoginPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public static int getNewDiaryID(){
        return config.getNewDiaryID();
    }

    public static int getNewUserID(){
        return config.getNewUserID();
    }

    public static ConfigHandler getConfig(){ return config; }
}
