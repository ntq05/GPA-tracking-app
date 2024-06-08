import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class GPAFrame3 {
    JFrame gpaFrame;
    JPanel textPanel;
    JLabel subjectLabel;
    JTextField subjectText;
    JLabel creditLabel;
    JTextField creditText;
    JLabel inClassLabel;
    JTextField inClassText;
    JLabel midtermLabel;
    JTextField midtermText;
    JLabel finalLabel;
    JTextField finalText;
    JButton addButton;
    JButton clearButton;
    JButton clrAllButton;
    JButton ShowGPAButton;
    JButton saveButton;
    JButton updateButton;
    JButton totalButton;
    JButton exitButton;
    JTable gpaTable;
    DefaultTableModel tableModel;

    
    public GPAFrame3(){
        // Create a frame
        gpaFrame = new JFrame("GPA tracking");
        gpaFrame.setSize(820,500);
        gpaFrame.setLayout(null);
        gpaFrame.setResizable(false);
        gpaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create every labels and textfields
        subjectLabel = new JLabel("Subject");
        subjectText = new JTextField();
        creditLabel = new JLabel("Credit");
        creditText = new JTextField();
        inClassLabel = new JLabel("In-class");
        inClassText = new JTextField();
        midtermLabel = new JLabel("Midterm");
        midtermText = new JTextField();
        finalLabel = new JLabel("Final");
        finalText = new JTextField();

        // set subject
        subjectLabel.setBounds(20,10,65,40);
        subjectLabel.setFont(new Font("Arial",Font.BOLD,15));
        gpaFrame.add(subjectLabel);
        subjectText.setBounds(90,15,200,30);
        subjectText.setFont(new Font("Arial",Font.PLAIN,15));
        gpaFrame.add(subjectText);

        // set credit
        creditLabel.setBounds(20,50,65,40);
        creditLabel.setFont(new Font("Arial",Font.BOLD,15));
        gpaFrame.add(creditLabel);
        creditText.setBounds(90,50,200,30);
        creditText.setFont(new Font("Arial",Font.PLAIN,15));
        gpaFrame.add(creditText);

        // set in class
        inClassLabel.setBounds(20,85,65,40);
        inClassLabel.setFont(new Font("Arial",Font.BOLD,15));
        gpaFrame.add(inClassLabel);
        inClassText.setBounds(90,90,200,30);
        inClassText.setFont(new Font("Arial",Font.PLAIN,15));
        gpaFrame.add(inClassText);

        // set midterm
        midtermLabel.setBounds(310,10,65,40);
        midtermLabel.setFont(new Font("Arial",Font.BOLD,15));
        gpaFrame.add(midtermLabel);
        midtermText.setBounds(385,15,200,30);
        midtermText.setFont(new Font("Arial",Font.PLAIN,15));
        gpaFrame.add(midtermText);

        // set final 
        finalLabel.setBounds(310,50,65,40);
        finalLabel.setFont(new Font("Arial",Font.BOLD,15));
        gpaFrame.add(finalLabel);
        finalText.setBounds(385,55,200,30);
        finalText.setFont(new Font("Arial",Font.PLAIN,15));
        gpaFrame.add(finalText);

        // create add button, clear button, clear all button,show GPA button and save button 
        addButton = new JButton("Add");
        clearButton = new JButton("Clear");
        clrAllButton = new JButton("Clear all");
        ShowGPAButton = new JButton("Show GPA");
        saveButton = new JButton("Save");
        updateButton = new JButton("Update");
        totalButton = new JButton("Total");

        // set add button
        addButton.setBounds(20,150,120,30);
        addButton.setFont(new Font("Arial",Font.BOLD,15));
        gpaFrame.add(addButton);

        // set clear button
        clearButton.setBounds(150,150,120,30);
        clearButton.setFont(new Font("Arial",Font.BOLD,15));
        gpaFrame.add(clearButton);

        // set clear all button
        clrAllButton.setBounds(280,150,120,30);
        clrAllButton.setFont(new Font("Arial",Font.BOLD,15));
        gpaFrame.add(clrAllButton);

        // set show GPA button 
        ShowGPAButton.setBounds(410,150,120,30);
        ShowGPAButton.setFont(new Font("Arial",Font.BOLD,15));
        gpaFrame.add(ShowGPAButton);

        // set save button
        saveButton.setBounds(540,150,120,30);
        saveButton.setFont(new Font("Arial",Font.BOLD,15));
        gpaFrame.add(saveButton);

        //set update button
        updateButton.setBounds(670,150,120,30);
        updateButton.setFont(new Font("Arial",Font.BOLD,15));
        gpaFrame.add(updateButton);

        // set total button
        totalButton.setBounds(600,15,120,30);
        totalButton.setFont(new Font("Arial",Font.BOLD,15));
        gpaFrame.add(totalButton);


        // create table model
        tableModel = new DefaultTableModel();

        // add column
        tableModel.addColumn("Subject");
        tableModel.addColumn("Credit");
        tableModel.addColumn("In-class");
        tableModel.addColumn("Midterm");
        tableModel.addColumn("Final");
        tableModel.addColumn("Total");

        // create table
        gpaTable = new JTable(tableModel);

        // set font for table column
        JTableHeader header = gpaTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 15));
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);

        // create scroll pane
        JScrollPane tableScroll = new JScrollPane(gpaTable);
        gpaFrame.add(tableScroll,BorderLayout.CENTER);
        tableScroll.setBounds(10,190,780,260);

        // create and set properties for exit button
        exitButton = new JButton("Exit");

        exitButton.setBounds(600,55,120,30);
        exitButton.setFont(new Font("Arial",Font.BOLD,15));
        gpaFrame.add(exitButton);

        // create the event for the add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String subject = subjectText.getText();
                String credit = creditText.getText();
                String inclass = inClassText.getText();
                String midterm = midtermText.getText();
                String Final = finalText.getText();
                if (subjectText.getText().isEmpty() && creditText.getText().isEmpty() && inClassText.getText().isEmpty() && midtermText.getText().isEmpty() && finalText.getText().isEmpty()){
                    JOptionPane.showMessageDialog(gpaFrame, "Please fill in at least 1 column");
                }
                else{
                    if(isString((subjectText.getText())) && isString(creditText.getText())&& (isInteger(inClassText.getText()) || inClassText.getText().isEmpty())&& (isInteger(midtermText.getText()) || midtermText.getText().isEmpty())&& (isInteger(finalText.getText()) || finalText.getText().isEmpty())){
                        
                        tableModel.addRow(new Object[]{subject,credit,inclass,midterm,Final});
                        // clear all the text
                        subjectText.setText("");
                        creditText.setText("");
                        inClassText.setText("");
                        midtermText.setText("");
                        finalText.setText("");
                    }
                    else{
                        JOptionPane.showMessageDialog(gpaFrame,"Invalid input");
                    }
                }

            }
        });

        // set mouse click event for updating new data
        gpaTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                if (e.getClickCount()==1){
                    int selectedRow = gpaTable.getSelectedRow();
                    int i = 0;
                    for (i=0;i<gpaTable.getRowCount();i++){
                        if (selectedRow!=-1){
                            String row_0 = (String)gpaTable.getValueAt(selectedRow, 0);
                            String row_1 = (String)gpaTable.getValueAt(selectedRow, 1).toString();
                            String row_2 = (String)gpaTable.getValueAt(selectedRow, 2).toString();
                            String row_3 = (String)gpaTable.getValueAt(selectedRow, 3).toString();
                            String row_4 = (String)gpaTable.getValueAt(selectedRow, 4).toString();
                            subjectText.setText(row_0); 
                            creditText.setText(row_1);
                            inClassText.setText(row_2);
                            midtermText.setText(row_3);
                            finalText.setText(row_4);
                            
                        }
                    }

                }
            }
        });

        // event for update button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(gpaTable.getSelectedRowCount()==1){
                    String subject = subjectText.getText();
                    String Credit = creditText.getText();
                    String inClass = inClassText.getText();
                    String midterm = midtermText.getText();
                    String Final = finalText.getText();

                    if (isString(subject) && isInteger(Credit) && (isInteger(inClass) || inClass.isEmpty())&& (isInteger(midterm) || midterm.isEmpty()) && (isInteger(Final) || Final.isEmpty())){
                        tableModel.setValueAt(subject, gpaTable.getSelectedRow(), 0);
                        tableModel.setValueAt(Credit, gpaTable.getSelectedRow(), 1);
                        tableModel.setValueAt(inClass, gpaTable.getSelectedRow(), 2);
                        tableModel.setValueAt(midterm, gpaTable.getSelectedRow(), 3);
                        tableModel.setValueAt(Final, gpaTable.getSelectedRow(), 4);
                        JOptionPane.showMessageDialog(gpaFrame,"Update successfully");
                    }
                    else{
                        JOptionPane.showMessageDialog(gpaFrame,"Invalid input");
                    }
                }
            }
        });

        // set event for clear all button
        clrAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (e.getSource()==clrAllButton){

                    // create the warning frame
                    JFrame clrAllFrame = new JFrame("Warning");
                    clrAllFrame.setSize(500,300);
                    clrAllFrame.setLayout(null);

                    // Create warning label
                    JLabel warnLabel = new JLabel("Are you sure you want to clear all?");

                    // set label 
                    warnLabel.setBounds(80,50,3300,50);
                    warnLabel.setFont(new Font("Arial",Font.BOLD,20));
                    clrAllFrame.add(warnLabel);

                    //create yes button
                    JButton yesButton = new JButton("Yes");

                    // set yes button
                    yesButton.setBounds(130,120,100,30);
                    yesButton.setFont(new Font("Arial",Font.BOLD,15));
                    clrAllFrame.add(yesButton);

                    // create no button
                    JButton noButton = new JButton("No");
                    
                    // set no button
                    noButton.setBounds(250,120,100,30);
                    noButton.setFont(new Font("Arial",Font.BOLD,15));
                    clrAllFrame.add(noButton);

                    // set clear warning visible
                    clrAllFrame.setVisible(true);

                    // set event for yes button
                    yesButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e){
                            if (e.getSource()==yesButton){
                                tableModel.setRowCount(0);
                                clrAllFrame.setVisible(false);
                            }
                        }
                    });
                    noButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e){
                            if (e.getSource()==noButton){
                                clrAllFrame.setVisible(false);
                            }
                        }
                    });
                }
            }
        });

        // set event for clear button
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (e.getSource()==clearButton){
                    int selectedRow = gpaTable.getSelectedRow();
                    if (selectedRow != -1){
                        tableModel.removeRow(selectedRow);
                        subjectText.setText("");
                        creditText.setText("");
                        inClassText.setText("");
                        midtermText.setText("");
                        finalText.setText("");

                    }
                    else{
                        JOptionPane.showMessageDialog(gpaFrame,"Please select a row you want to delete !");
                    }
                }
            }
        });

        // set event for total button
        totalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (e.getSource()==totalButton){
                    try{
                        int inCl = Integer.parseInt(inClassText.getText());
                        int mid = Integer.parseInt(midtermText.getText());
                        int fin = Integer.parseInt(finalText.getText());
                        JFrame totalFrame = new JFrame("Total");

                    
                        // set total frame
                        totalFrame.setSize(500,300);
                        totalFrame.setLayout(null);

                        // button done
                        JButton doneButton = new JButton("Done");

                        // percentage label
                        JLabel scale = new JLabel("Enter the scales of each column of scores");
                        scale.setBounds(45,10,400,50);
                        scale.setFont(new Font("Arial",Font.BOLD,20));
                        totalFrame.add(scale);

                        JLabel inClassSc = new JLabel("In-class");
                        inClassSc.setBounds(45,40,100,50);
                        inClassSc.setFont(new Font("Arial",Font.BOLD,15));
                        totalFrame.add(inClassSc);

                        JLabel midSc = new JLabel("Midterm");
                        midSc.setBounds(45,70,100,50);
                        midSc.setFont(new Font("Arial",Font.BOLD,15));
                        totalFrame.add(midSc);

                        JLabel finSc = new JLabel("Final");
                        finSc.setBounds(45,100,100,50);
                        finSc.setFont(new Font("Arial",Font.BOLD,15));
                        totalFrame.add(finSc);

                        // text field
                        JTextField inClassF = new JTextField();
                        inClassF.setBounds(200,50,100,20);
                        inClassF.setFont(new Font("Arial",Font.BOLD,15));
                        totalFrame.add(inClassF);

                        JTextField midF = new JTextField();
                        midF.setBounds(200,80,100,20);
                        midF.setFont(new Font("Arial",Font.BOLD,15));
                        totalFrame.add(midF);

                        JTextField finF = new JTextField();
                        finF.setBounds(200,110,100,20);
                        finF.setFont(new Font("Arial",Font.BOLD,15));
                        totalFrame.add(finF);

                        // done button
                        doneButton.setBounds(210,210,100,30);
                        doneButton.setFont(new Font("Arial",Font.BOLD,15));
                        totalFrame.add(doneButton);

                        // set done button event
                        doneButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e){
                                double total = (inCl*(Double.parseDouble(inClassF.getText()))) + (mid *(Double.parseDouble(midF.getText()))+(fin*(Double.parseDouble(finF.getText()))));
                                tableModel.setValueAt(total, gpaTable.getSelectedRow(), 5);
                                totalFrame.setVisible(false);
                                
                                // set every text to default
                                subjectText.setText("");
                                creditText.setText("");
                                inClassText.setText("");
                                midtermText.setText("");
                                finalText.setText("");
                            }
                        });


                        totalFrame.setVisible(true);
                    }
                    catch (Exception ex){
                        JOptionPane.showMessageDialog(gpaFrame,"Please enter complete information first !");
                    }
                
                }
            }
        });

        // set event for show gpa button
        ShowGPAButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e){
            if(e.getSource()==ShowGPAButton){
                ArrayList<Double>totalRow=new ArrayList<>();
                ArrayList<Double>creditRow=new ArrayList<>();
                for (int i =0; i<tableModel.getRowCount();i++){
                    totalRow.add(Double.valueOf(tableModel.getValueAt(i, 1).toString())*Double.valueOf(tableModel.getValueAt(i, 5).toString()));
                    creditRow.add(Double.valueOf(tableModel.getValueAt(i, 1).toString()));
                }
                double sumTotal =0.0;
                for (double j: totalRow){
                    sumTotal +=j;
                }
                double sumCredit =0.0;
                for (double a: creditRow){
                    sumCredit +=a;
                }
                JOptionPane.showMessageDialog(gpaFrame,"Your GPA score is: "+ sumTotal/sumCredit);
                sumTotal=0.0;
                sumCredit=0.0;

            }
           } 
        });

        // set save button event
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (e.getSource()==saveButton){
                    String insertQuery = "INSERT INTO GPAscore3 (Subject, Credit, [In-class], Midterm, Final, Total) VALUES(?,?,?,?,?,?)";
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=GPAtracking;user=sa;password=123456789;encrypt=true;trustServerCertificate=true";
                    try {
                        if (isDatabaseEmpty(DriverManager.getConnection(url), "GPAscore3")){
                            try (Connection connection = DriverManager.getConnection(url)){
                                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                                        for(int j =0 ; j< tableModel.getRowCount();j++){
                                            insertStatement.setString(1, (String)tableModel.getValueAt(j, 0));

                                            // condition ? value_if_true : value_if_false -> short hand of if else statement
                                            String creditValue = tableModel.getValueAt(j, 1) != null ? tableModel.getValueAt(j, 1).toString() : "";
                                            if (!creditValue.isEmpty()) {
                                                insertStatement.setInt(2, Integer.parseInt(creditValue));
                                            } else {
                                                insertStatement.setNull(2, Types.INTEGER);
                                            }

                                            String inClassValue = tableModel.getValueAt(j, 2) != null ? tableModel.getValueAt(j, 2).toString() : "";
                                            if (!inClassValue.isEmpty()) {
                                                insertStatement.setInt(3, Integer.parseInt(inClassValue));
                                            } else {
                                                insertStatement.setNull(3, Types.INTEGER);
                                            }

                                            String midtermValue = tableModel.getValueAt(j, 3) != null ? tableModel.getValueAt(j, 3).toString() : "";
                                            if (!midtermValue.isEmpty()) {
                                                insertStatement.setInt(4, Integer.parseInt(midtermValue));
                                            } else {
                                                insertStatement.setNull(4, Types.INTEGER);
                                            }

                                            String finalValue = tableModel.getValueAt(j, 4) != null ? tableModel.getValueAt(j, 4).toString() : "";
                                            if (!finalValue.isEmpty()) {
                                                insertStatement.setInt(5, Integer.parseInt(finalValue));
                                            } else {
                                                insertStatement.setNull(5, Types.INTEGER);
                                            }

                                            String totalValue = tableModel.getValueAt(j, 5) != null ? tableModel.getValueAt(j, 5).toString() : "";
                                            if (!totalValue.isEmpty()) {
                                                insertStatement.setDouble(6, Double.parseDouble(totalValue));
                                            } else {
                                                insertStatement.setNull(6, Types.DOUBLE);
                                            }

                                            insertStatement.executeUpdate();
                                        }

                                        JOptionPane.showMessageDialog(gpaFrame,"Save successfully");
                                } catch (NumberFormatException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                } catch (SQLException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                } 

                            
                        }
                        else{
                            deleteAllRows(DriverManager.getConnection(url), "GPAscore3");
                            try (Connection connection = DriverManager.getConnection(url)){
                                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                                for(int j =0 ; j< tableModel.getRowCount();j++){
                                    insertStatement.setString(1, (String)tableModel.getValueAt(j, 0));
                                    String creditValue = tableModel.getValueAt(j, 1) != null ? tableModel.getValueAt(j, 1).toString() : "";
                                            if (!creditValue.isEmpty()) {
                                                insertStatement.setInt(2, Integer.parseInt(creditValue));
                                            } else {
                                                insertStatement.setNull(2, Types.INTEGER);
                                            }

                                            String inClassValue = tableModel.getValueAt(j, 2) != null ? tableModel.getValueAt(j, 2).toString() : "";
                                            if (!inClassValue.isEmpty()) {
                                                insertStatement.setInt(3, Integer.parseInt(inClassValue));
                                            } else {
                                                insertStatement.setNull(3, Types.INTEGER);
                                            }

                                            String midtermValue = tableModel.getValueAt(j, 3) != null ? tableModel.getValueAt(j, 3).toString() : "";
                                            if (!midtermValue.isEmpty()) {
                                                insertStatement.setInt(4, Integer.parseInt(midtermValue));
                                            } else {
                                                insertStatement.setNull(4, Types.INTEGER);
                                            }

                                            String finalValue = tableModel.getValueAt(j, 4) != null ? tableModel.getValueAt(j, 4).toString() : "";
                                            if (!finalValue.isEmpty()) {
                                                insertStatement.setInt(5, Integer.parseInt(finalValue));
                                            } else {
                                                insertStatement.setNull(5, Types.INTEGER);
                                            }

                                            String totalValue = tableModel.getValueAt(j, 5) != null ? tableModel.getValueAt(j, 5).toString() : "";
                                            if (!totalValue.isEmpty()) {
                                                insertStatement.setDouble(6, Double.parseDouble(totalValue));
                                            } else {
                                                insertStatement.setNull(6, Types.DOUBLE);
                                            }
                                    insertStatement.executeUpdate();
                                }
                                        JOptionPane.showMessageDialog(gpaFrame,"Save successfully");
                                } catch (NumberFormatException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                } catch (SQLException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                } 


                        }
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    }
                }

        });

        // set event for exit button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (e.getSource() == exitButton){
                    gpaFrame.dispose();
                    new semesterFrame();

                }
            }
        });

        // show data from the database when run the app
        fetch();

        // set the frame become visible
        gpaFrame.setVisible(true);
    }

    // create integer test method 
    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // create string test method
    private static boolean isString(String str){
            return str != null && !str.trim().isEmpty();
    }

    // method to check whether a row with a specific ID exists in the database

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

    // delete all row in database method
    public void deleteAllRows(Connection connection, String tableName) {
        String query = "DELETE FROM " + tableName;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    // method for fetching data 
    private void fetch (){
        List<String[]> data = Database3.fetchData();
        for (String[]row : data){
            tableModel.addRow(row);
        }
    }
    public static void main(String[] args) {
        new GPAFrame3();
    }
}
