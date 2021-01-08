import javax.swing.*;

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
                String password1 = JOptionPane.showInputDialog("Repeat your password");
                if (!password.equals(password1)) {
                    JOptionPane.showMessageDialog(null,"Input illegal, please try again!");
                } else {
                    IOHandler.addUser(password, username);
                    JOptionPane.showMessageDialog(null,"Register completed!");
                }
            }catch (NullPointerException exception){
                exception.printStackTrace();
            }
        });
    }

    public JPanel getLoginPanel(){ return loginPanel; }

}
