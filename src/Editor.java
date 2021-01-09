import javax.swing.*;
import java.io.IOException;

public class Editor {
    private JEditorPane editorPane1;
    protected JPanel mainPanel;
    private JButton saveAndCloseButton;
    private JTextPane titlePane;
    private JTextPane weatherPane;

    public Editor(Diary diary, JFrame jFrame){
        if(diary!=null){
            titlePane.setText(diary.getTitle());
            weatherPane.setText(diary.getWeather());
        } else {
            titlePane.setText("Untitled");
        }

        saveAndCloseButton.addActionListener(e -> {
            if(diary==null){
                IOHandler.addDiary(titlePane.getText(), editorPane1.getText(), weatherPane.getText(), Main.currentUser);
            }else{
                diary.update(titlePane.getText(), editorPane1.getText(), weatherPane.getText());
                try {
                    IOHandler.saveDiaries();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            jFrame.dispose();
        });

    }

}
