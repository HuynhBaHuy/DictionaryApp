package vn.edu.hcmus.student._19127420.layout;/*..
 * vn.edu.hcmus.student._19127420.layout
 * Created by HuynhBaHuy
 * Date 12/19/2021 11:49 PM
 * Description:...
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainFrame {
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JLabel appName;
    private JPanel bodyPanel;
    private JButton searchButton;
    private JButton settingButton;
    private JButton quizzButton;

    public MainFrame(){
        createAndShowGUI();
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchFrame searchFrame = new SearchFrame();
            }
        });
        settingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingFrame settingFrame = new SettingFrame();
                settingFrame.setVisible(true);
            }
        });
        quizzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public static void main(String []args){
        MainFrame app = new MainFrame();
    }

    private void createAndShowGUI(){
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
