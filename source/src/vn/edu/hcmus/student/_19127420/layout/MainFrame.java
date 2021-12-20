package vn.edu.hcmus.student._19127420.layout;/*..
 * vn.edu.hcmus.student._19127420.layout
 * Created by HuynhBaHuy
 * Date 12/19/2021 11:49 PM
 * Description:...
 */

import vn.edu.hcmus.student._19127420.data.dictionary;
import vn.edu.hcmus.student._19127420.data.slangWord;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    final int height_frame = 500;
    final int width_frame = 700;

    private JPanel mainPanel;
    private JPanel headerPanel;
    private JLabel appName;
    private JPanel bodyPanel;
    private JButton searchButton;
    private JButton settingButton;
    private JButton quizzButton;
    private JButton randomButton;
    private JPanel randomPane;
    private JLabel sRandomLabel;
    private JLabel mRandomLabel;
    private JButton backButton;

    dictionary data;
    public MainFrame(){
        data = new dictionary();
        createAndShowGUI();
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchFrame searchFrame = new SearchFrame();
                searchFrame.setSize(width_frame,height_frame);
                searchFrame.setVisible(true);
            }
        });
        settingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingFrame settingFrame = new SettingFrame();
                settingFrame.setSize(width_frame,height_frame);
                settingFrame.setVisible(true);
            }
        });
        quizzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = randomPane.isVisible();
                randomPane.setVisible(!flag);
                if(randomPane.isVisible()){
                    slangWord rSlang = data.randomSlang();
                    sRandomLabel.setText(rSlang.getSlang());
                }
            }
        });
    }


    private void createAndShowGUI(){
        JFrame.setDefaultLookAndFeelDecorated(true);

        setTitle("Slang Dictionary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(width_frame,height_frame);
        pack();
        setVisible(true);
    }
}
