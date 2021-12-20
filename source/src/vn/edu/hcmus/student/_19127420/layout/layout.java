package vn.edu.hcmus.student._19127420.layout;/*..
 * vn.edu.hcmus.student._19127420.layout
 * Created by HuynhBaHuy
 * Date 12/21/2021 1:30 AM
 * Description:...
 */

import vn.edu.hcmus.student._19127420.data.dictionary;

import javax.swing.*;
import java.awt.*;

public class layout extends JFrame {
    JPanel menuPanel;
    JPanel headerPanel;


    JButton searchButton;
    JButton settingButton;
    JButton quizzButton;
    JButton randomButton;
    JButton backButton;
    dictionary data;

    class menuPanel extends JPanel{
        JLabel menuLabel;
        JPanel bodyPanel;
        public menuPanel(){
            setLayout(new BorderLayout());
            menuLabel = new JLabel("Menu");
            bodyPanel = new JPanel(new GridBagLayout());

            GridBagConstraints constraints = new GridBagConstraints();
            


            add(menuLabel, BorderLayout.PAGE_START);
            add(bodyPanel, BorderLayout.CENTER);
        }
    }

    public layout(){
        // initialize the dictionary
        data = new dictionary();

        //initialize the component
        initializeComponent();



        // show GUI
        createAndShowGUI();
    }

    private void initializeComponent(){
        searchButton = new JButton("Search");
        settingButton = new JButton("Settings");
        quizzButton = new JButton("Quizz");
        randomButton = new JButton("Random");
        backButton = new JButton("Back");
    }

    private void createAndShowGUI(){
        JFrame.setDefaultLookAndFeelDecorated(true);

        setTitle("Slang Dictionary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(menuPanel);
        pack();
        setVisible(true);
    }
}
