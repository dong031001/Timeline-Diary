import javax.swing.*;
import java.security.NoSuchAlgorithmException;

public class Login {
    private JButton loginButton;
    private JButton registerButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton forgotYourPasswordButton;
    private JPanel loginPanel;

    public Login(){
        registerButton.addActionListener(event ->{
            try {
                String username = JOptionPane.showInputDialog("Input your username").toLowerCase();
                String password = JOptionPane.showInputDialog("Input your password");
                String repeatedPassword = JOptionPane.showInputDialog("Repeat your password");
                if (!password.equals(repeatedPassword)) {
                    JOptionPane.showMessageDialog(null,"Input illegal, please try again!");
                } else {
                    IOHandler.addUser(password, username);
                    JOptionPane.showMessageDialog(null,"Register completed!");
                }
            }catch (NullPointerException exception){
                exception.printStackTrace();
            }
        });

        loginButton.addActionListener(event -> {

            for(Integer userID : IOHandler.getUserHashMap().keySet()){
                User user = IOHandler.getUserHashMap().get(userID);
                try {
                    if(user.getUsername().equals(textField1.getText())
                            && user.isRightPassword(new String(passwordField1.getPassword()))){
                        Main.currentUser = user;
                        Main.frame.dispose();

                        Main.frame = new JFrame("Timeline Diary");
                        Main.frame.setContentPane(new Menu().panel1);
                        Main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        Main.frame.pack();
                        Main.frame.setVisible(true);

                    }
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

            }

        });
    }

    public JPanel getLoginPanel(){ return loginPanel; }

}
