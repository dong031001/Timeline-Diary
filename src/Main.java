import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static int newDiaryID = 0;

    public static void main(String[] args) throws IOException {

        IOHandler.initialize();

    }

    public static int getNewDiaryID() {
        //TODO
        return ++newDiaryID;
    }
}
