import javax.swing.*;

public class View {
    JPanel mainPanel;
    private JTextPane textPane1;
    private JButton exitButton;
    private JLabel title;

    public View(Diary diary, JFrame frame){

        textPane1.setText(diary.getText());
        title.setText(diary.getTitle());

        exitButton.addActionListener(e -> {
            frame.dispose();
        });

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        textPane1 = new JTextPane(){
            @Override
            public boolean isEditable() {
                return false;
            }
        };
    }
}
