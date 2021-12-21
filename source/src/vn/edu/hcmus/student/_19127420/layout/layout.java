package vn.edu.hcmus.student._19127420.layout;/*..
 * vn.edu.hcmus.student._19127420.layout
 * Created by HuynhBaHuy
 * Date 12/21/2021 1:30 AM
 * Description:...
 */

import vn.edu.hcmus.student._19127420.data.dictionary;
import vn.edu.hcmus.student._19127420.data.slangWord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class layout extends JFrame  implements ActionListener {
    JPanel bodyPanel;
    JButton searchButton;
    JButton settingButton;
    JButton quizzButton;
    JButton randomButton;
    JButton backButton;
    dictionary data;

    class randomPanel extends JPanel implements ActionListener {
        JPanel headerPanel;
        JButton randBtn;
        JPanel bodyPanel;
        JLabel slangLabel;
        JList<String> meanings;
        slangWord randSw;
        public randomPanel()
        {
            setLayout(new BorderLayout());

            // header panel
            headerPanel = new JPanel(new FlowLayout());
            randBtn = new JButton("Random");
            headerPanel.add(randBtn);
            headerPanel.add(backButton);

            // add action listener for button
            randBtn.addActionListener(this);


            // prepare data
            slangWord randSw = data.randomSlang();
            slangLabel = new JLabel(randSw.getSlang(),SwingConstants.CENTER);
            meanings = new JList<String>();
            meanings.setListData(randSw.getMeaning());
            meanings.setPreferredSize(new Dimension(10,20));

            //body panel
            bodyPanel = new JPanel(new BorderLayout());
            bodyPanel.add(slangLabel,BorderLayout.PAGE_START);
            bodyPanel.add(meanings,BorderLayout.CENTER);



            add(headerPanel,BorderLayout.PAGE_START);
            add(bodyPanel,BorderLayout.CENTER);
        }
        private void loadBodyPanel() {
            slangLabel.setText(randSw.getSlang());
            meanings.setListData(randSw.getMeaning());


        }
        public void clear(){
            slangLabel.setText("");
            meanings.removeAll();
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            //clear old data
            clear();
            // prepare data
            randSw = data.randomSlang();
            // load data
            loadBodyPanel();
        }
    }

    class menuPanel extends JPanel {
        JLabel menuLabel;
        JPanel bodyPanel;
        public menuPanel(){
            setLayout(new BorderLayout());
            menuLabel = new JLabel("Menu",SwingConstants.CENTER);
            menuLabel.setAlignmentX(Label.CENTER);
            bodyPanel = new JPanel(new GridLayout(4,1,5,10));
            bodyPanel.add(randomButton);
            bodyPanel.add(searchButton);
            bodyPanel.add(settingButton);
            bodyPanel.add(quizzButton);
            add(menuLabel, BorderLayout.PAGE_START);
            add(bodyPanel, BorderLayout.CENTER);
        }

    }

    public layout(){
        // initialize the dictionary
        data = new dictionary();

        //initialize the component
        initializeComponent();

        // body panel
        bodyPanel = new JPanel(new CardLayout());

        // initizliza the menu pane
        JPanel menuPanel = new menuPanel();
        JPanel randomPanel = new randomPanel();

        bodyPanel.add(menuPanel,"menu");
        bodyPanel.add(randomPanel,"random");
        // show GUI
        createAndShowGUI(bodyPanel);
    }

    private void initializeComponent(){
        // init
        searchButton = new JButton("Search");
        settingButton = new JButton("Settings");
        quizzButton = new JButton("Quizz");
        randomButton = new JButton("Random");
        backButton = new JButton("Back");

        // action lister
        randomButton.addActionListener(this);
        randomButton.setActionCommand("random_menu_btn");
        backButton.addActionListener(this);
        backButton.setActionCommand("back");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        CardLayout cl = (CardLayout)(bodyPanel.getLayout());
        if(str == "random_menu_btn"){
            cl.show(bodyPanel,"random");
        }
        else if(str == "search_menu_btn"){

        }
        else if(str == "setting_menu_btn"){

        }
        else if(str == "quizz_menu_btn"){

        }
        else if(str == "back")
        {
            cl.previous(bodyPanel);
        }
    }
    private void createAndShowGUI(Container container){
        JFrame.setDefaultLookAndFeelDecorated(true);

        setTitle("Slang Dictionary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(container);
        pack();
        setSize(new Dimension(500,300));
        setLocation(new Point(500,150));
        setVisible(true);
    }
}
