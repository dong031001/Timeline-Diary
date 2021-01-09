import javax.swing.*;
import java.io.IOException;
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

    private Diary currentDiary = null;
    private Diary[] currentDiaryList;

    public Menu(){

        refreshData();

        newButton.addActionListener(event->{
            openEditor(null);
            refreshData();
        });

        editButton.addActionListener(event->{
            if(currentDiary==null) return;
            openEditor(currentDiary);
            refreshData();
        });

        viewButton.addActionListener(event->{
            openViewer(currentDiary);
        });

        deleteButton.addActionListener(event -> {

            int result = JOptionPane.showConfirmDialog(panel1, "Are you sure to delete diary "+currentDiary.getTitle()+" ?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if(result!=JOptionPane.OK_OPTION) return;

            currentDiary.setDisposed();

            try {
                IOHandler.saveDiaries();
            } catch (IOException e) {
                e.printStackTrace();
            }

            IOHandler.getDiaryHashMap().remove(currentDiary.getDiaryID());

            refreshData();

        });

        diaryList.addListSelectionListener(event -> {

            if(diaryList.getSelectedIndex()==-1) return;

            this.currentDiary = currentDiaryList[diaryList.getSelectedIndex()];
            titleValueLabel.setText(currentDiary.getTitle());
            dateValueLabel.setText(currentDiary.getDate());
            lengthValueLabel.setText(String.valueOf(currentDiary.getLength()));
        });
    }

    private void openEditor(Diary diary){
        JFrame editorFrame = new JFrame("Timeline Diary Editor");
        editorFrame.setContentPane(new Editor(diary, editorFrame, this).mainPanel);
        editorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editorFrame.pack();
        editorFrame.setVisible(true);
    }

    private void openViewer(Diary diary){
        JFrame viewerFrame = new JFrame("Timeline Diary Viewer");
        viewerFrame.setContentPane((new View(diary, viewerFrame)).mainPanel);
        viewerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewerFrame.pack();
        viewerFrame.setVisible(true);
    }

    public void refreshData(){
        currentDiaryList = getDiariesFromUser(Main.currentUser);
        diaryList.setListData(currentDiaryList);
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
