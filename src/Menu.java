import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Menu {
    public JPanel panel1;
    private JButton viewButton;
    private JButton editButton;
    private JButton newButton;
    private JList diaryList;
    private JLabel titleValueLabel;
    private JLabel lengthValueLabel;
    private JLabel dateValueLabel;
    private JButton deleteButton;



    public Menu(){


        newButton.addActionListener(event->{
            JFrame editorFrame = new JFrame("Timeline Diary Editor");
            editorFrame.setContentPane(new Editor(null, editorFrame).mainPanel);
            editorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            editorFrame.pack();
            editorFrame.setVisible(true);
        });


    }

    private void refreshData(){
        diaryList.setListData(getTitlesFromUser(Main.currentUser));
    }

    private static Diary[] getDiariesFromUser(User user){
        ArrayList<Diary> diaries = new ArrayList<>();
        int userID = user.getUserID();
        HashMap<Integer, Diary> diaryHashMap = IOHandler.getDiaryHashMap();
        for(Integer diaryID : diaryHashMap.keySet()){
            Diary diary = diaryHashMap.get(diaryID);
            int correspondingUserID = diary.getUserID();
            if(correspondingUserID==userID) diaries.add(diary);
        }

        Diary[] diaryArray = diaries.toArray(new Diary[0]);

        Arrays.sort(diaryArray);

        return diaryArray;
    }

    private static String[] getTitlesFromUser(User user){
        Diary[] diaries = getDiariesFromUser(user);
        String[] titles = new String[diaries.length];

        for(int i = 0; i < diaries.length; i++) titles[i] = diaries[i].getTitle();

        return titles;
    }

}
