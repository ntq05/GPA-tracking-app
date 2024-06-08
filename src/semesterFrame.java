import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;;

public class semesterFrame {
    public semesterFrame(){
        JFrame semesterFrame = new JFrame("Semester");
        semesterFrame.setSize(500,300);
        semesterFrame.setLayout(null);
        semesterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton semButton1 = new JButton("Semester 1");
        JButton semButton2 = new JButton("Semester 2");
        JButton semButton3 = new JButton("semester 3");

        // set properties for the 3 semester buttons
        semButton1.setBounds(150,50,200,30);
        semButton1.setFont(new Font("Arial",Font.BOLD,20));
        semesterFrame.add(semButton1);

        semButton2.setBounds(150,120,200,30);
        semButton2.setFont(new Font("Arial",Font.BOLD,20));
        semesterFrame.add(semButton2);

        semButton3.setBounds(150,190,200,30);
        semButton3.setFont(new Font ("Arial",Font.BOLD,20));
        semesterFrame.add(semButton3);

        semesterFrame.setVisible(true);

        //set semester button 1 event
        semButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (e.getSource() == semButton1){
                    new GPAFrame();
                    semesterFrame.dispose();
                    }
                }
        });

        // set semester button 2 event
        semButton2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == semButton2){
                new GPAFrame2();
                semesterFrame.dispose();
                }
            }
        });

        // set semester button 3 event
        semButton3.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
        if(e.getSource()==semButton3){
            new GPAFrame3();
            semesterFrame.dispose();
            }
        }
        });
    }
}
