import javax.swing.JFrame;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class logInFrame {
    JFrame logInFrame;
    JFrame registerFrame;
    JLabel userLogName;
    JLabel passwordLog;
    JTextField userLogField;
    JTextField passwordLogField;
    JButton logInButton;
    JButton registerButton;
    JLabel userRegisterLabel;
    JLabel passwordRegLabel;
    JLabel passVeriRegLabel;
    JTextField userRegField;
    JTextField passRegField;
    JTextField passVeriField;
    JButton registerReButton;
    JButton logInReButton;



    public logInFrame(){
        logInFrame = new JFrame("Log in");
        logInFrame.setSize(500,400);
        logInFrame.setLayout(null);
        logInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        userLogName = new JLabel("Username");
        passwordLog = new JLabel("Password");
        
        // set properties for user and password label in log in frame
        userLogName.setBounds(30,60,100,20);
        userLogName.setFont(new Font("Arial",Font.BOLD,20));
        logInFrame.add(userLogName);

        passwordLog.setBounds(30,110,100,20);
        passwordLog.setFont(new Font("Arial",Font.BOLD,20));
        logInFrame.add(passwordLog);

        userLogField = new JTextField();
        passwordLogField = new JTextField();

        // set properties for user and password text field in log in frame
        userLogField.setBounds(150,60,300,30);
        userLogField.setFont(new Font("Arial",Font.BOLD,20));
        logInFrame.add(userLogField);

        passwordLogField.setBounds(150,110,300,30);
        passwordLogField.setFont(new Font("Arial",Font.BOLD,20));
        logInFrame.add(passwordLogField);

        logInButton = new JButton("Log in");
        registerButton = new JButton("Register");

        // set properties for log in and register button in log in frame
        logInButton.setBounds(100,200,100,30);
        logInButton.setFont(new Font("Arial",Font.BOLD,15));
        logInFrame.add(logInButton);

        registerButton.setBounds(240,200,100,30);
        registerButton.setFont(new Font("Arial",Font.BOLD,15));
        logInFrame.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (e.getSource()==registerButton){
                    registerFrame = new JFrame("Register");

                    // make log in frame disappear
                    logInFrame.setVisible(false);

                    // set properties for register frame
                    registerFrame.setSize(500,400);
                    registerFrame.setLayout(null);
                    registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    userRegisterLabel = new JLabel("Username");
                    passwordRegLabel = new JLabel("Password");
                    passVeriRegLabel = new JLabel("Verify password");

                    // set properties for user name and password label in register frame
                    userRegisterLabel.setBounds(30,60,100,20);
                    userRegisterLabel.setFont(new Font("Arial",Font.BOLD,20));
                    registerFrame.add(userRegisterLabel);

                    passwordRegLabel.setBounds(30,110,100,20);
                    passwordRegLabel.setFont(new Font("Arial",Font.BOLD,20));
                    registerFrame.add(passwordRegLabel);

                    passVeriRegLabel.setBounds(30,160,160,20);
                    passVeriRegLabel.setFont(new Font("Arial",Font.BOLD,20));
                    registerFrame.add(passVeriRegLabel);

                    userRegField = new JTextField();
                    passRegField = new JTextField();
                    passVeriField = new JTextField();

                    // set properties for user and password field in register frame
                    userRegField.setBounds(210,60,200,30);
                    userRegField.setFont(new Font("Arial",Font.BOLD,15));
                    registerFrame.add(userRegField);

                    passRegField.setBounds(210,110,200,30);
                    passRegField.setFont(new Font("Arial",Font.BOLD,15));
                    registerFrame.add(passRegField);

                    passVeriField.setBounds(210,160,200,30);
                    passVeriField.setFont(new Font("Arial",Font.BOLD,15));
                    registerFrame.add(passVeriField);

                    registerReButton = new JButton("Register");
                    logInReButton = new JButton("Log in");

                    // set properties for register and log in button in register frame
                    registerReButton.setBounds(130,250,100,30);
                    registerReButton.setFont(new Font("Arial",Font.BOLD,15));
                    registerFrame.add(registerReButton);

                    logInReButton.setBounds(260,250,100,30);
                    logInReButton.setFont(new Font("Arial",Font.BOLD,15));
                    registerFrame.add(logInReButton);
                    
                    // visibility
                    registerFrame.setVisible(true);

                    // set log in button event in register frame
                    logInReButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e){
                            if (e.getSource()==logInReButton){
                                logInFrame.setVisible(true);
                                registerFrame.dispose();
                            }
                        }
                    });

                    registerReButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String urlLog = "jdbc:sqlserver://localhost:1433;databaseName=GPAtracking;user=sa;password=123456789;encrypt=true;trustServerCertificate=true";
                            try {
                                if (isDatabaseEmpty(DriverManager.getConnection(urlLog),"logInData")){

                                    if (passRegField.getText().toString().equals(passVeriField.getText().toString())) {
                                        String url = "jdbc:sqlserver://localhost:1433;databaseName=GPAtracking;user=sa;password=123456789;encrypt=true;trustServerCertificate=true";
                                        String insertQuery = "INSERT INTO logInData (Username, Password) VALUES (?, ?)";
               
                                        if (!userRegField.getText().toString().isEmpty() && !passRegField.getText().toString().isEmpty()) {
                                            try (Connection connection = DriverManager.getConnection(url)) {
                                                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                                                insertStatement.setString(1, userRegField.getText().toString());
                                                insertStatement.setString(2, passRegField.getText().toString());
                                                // Execute the statement
                                                int rowsInserted = insertStatement.executeUpdate();
               
                                                if (rowsInserted > 0) {
                                                    JOptionPane.showMessageDialog(logInFrame, "Registration successful");
                                                    registerFrame.setVisible(false);
                                                    logInFrame.setVisible(true);
                                                } else {
                                                    JOptionPane.showMessageDialog(logInFrame, "Failed to register");
                                                }
                                            } catch (SQLException ex) {
                                                ex.printStackTrace();
                                                JOptionPane.showMessageDialog(logInFrame, "Database error: " + ex.getMessage());
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(logInFrame, "You forgot to enter username or password");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(logInFrame, "Your verifying password is different from the entered password");
                                    }
                                }
                                else{
                                    JOptionPane.showMessageDialog(logInFrame,"You have already create your own password");

                                }
                            } catch (HeadlessException | SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        }
                    });
                    
                }
            }
        });


        // set event for log in button in log in frame
        logInButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(e.getSource() == logInButton){
                    String url ="jdbc:sqlserver://localhost:1433;databaseName=GPAtracking;user=sa;password=123456789;encrypt=true;trustServerCertificate=true";
                    String selectQuery ="SELECT Password FROM logInData WHERE Username=?";

                    String enteredUser = userLogField.getText().toString();
                    String enteredPassword = passwordLogField.getText().toString();

                    try (Connection connection = DriverManager.getConnection(url)){
                        PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
                        selectStatement.setString(1, enteredUser);

                        ResultSet resultSet = selectStatement.executeQuery();

                        if (resultSet.next()){
                            String storedPassword = resultSet.getString("Password");
                            if (enteredPassword.equals(storedPassword)) {
                                JOptionPane.showMessageDialog(logInFrame, "Login successful");
                                logInFrame.dispose();
                                new semesterFrame();

                            }
                            else{
                                JOptionPane.showMessageDialog(logInFrame, "Incorrect password");
                            }    
                        }
                    }
                    catch (SQLException ex){
                        ex.printStackTrace();
                    }
                }

            }
        });



        logInFrame.setVisible(true);
    }

    public boolean isDatabaseEmpty(Connection connection, String tableName) {
        String query = "SELECT COUNT(*) FROM " + tableName;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                int rowCount = resultSet.getInt(1);
                return rowCount == 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true; // Return true by default if an error occurs
    }

    public void deleteAllRows(Connection connection, String tableName) {
        String query = "DELETE FROM " + tableName;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new logInFrame();
    }
}
