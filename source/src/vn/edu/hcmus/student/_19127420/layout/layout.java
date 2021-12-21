package vn.edu.hcmus.student._19127420.layout;/*..
 * vn.edu.hcmus.student._19127420.layout
 * Created by HuynhBaHuy
 * Date 12/21/2021 1:30 AM
 * Description:...
 */

import vn.edu.hcmus.student._19127420.app.history;
import vn.edu.hcmus.student._19127420.data.dictionary;
import vn.edu.hcmus.student._19127420.data.slangWord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * define main frame
 */
public class layout extends JFrame  implements ActionListener {
    JPanel bodyPanel;
    JButton searchButton;
    JButton settingButton;
    JButton quizzButton;
    JButton randomButton;
    JButton backSearchButton;
    JButton backRandomButton;
    dictionary data;

    class searchPanel extends JPanel implements  ActionListener{
        final String notFound = "404 NOT FOUND";

        JPanel headerPanel;
        JPanel bodyPanel;
        JPanel searchingPanel;
        JPanel historyPanel;

        JButton searchBySlangBtn;
        JButton searchByDefinitionBtn;
        JButton historyBtn;
        JTextField inputTextField;
        JList<String> resultSeachList;
        JTable historySeachTable;

        history log;
        /**
         * constructor for search panel
         */
        public searchPanel(){
            setLayout(new BorderLayout());
            // init panel
            headerPanel = new JPanel(new FlowLayout());
            bodyPanel = new JPanel(new CardLayout());
            searchingPanel = new JPanel(new BorderLayout());
            historyPanel = new JPanel(new BorderLayout());

            // prepare data
            log = new history();

            //init button
            searchBySlangBtn = new JButton("Seach by slang");
            searchBySlangBtn.addActionListener(this);
            searchBySlangBtn.setActionCommand("search-by-slang-btn");
            searchByDefinitionBtn = new JButton("Search by definition");
            searchByDefinitionBtn.addActionListener(this);
            searchByDefinitionBtn.setActionCommand("search-by-definition-btn");
            historyBtn = new JButton("History");
            historyBtn.addActionListener(this);
            historyBtn.setActionCommand("history-btn");

            // init other component in search card
            inputTextField = new JTextField();
            resultSeachList = new JList<String>();

            // init component in history card
            historySeachTable = new JTable();

            // add button to header pane
            headerPanel.add(searchBySlangBtn);
            headerPanel.add(searchByDefinitionBtn);
            headerPanel.add(historyBtn);

            // add component to search card
            searchingPanel.add(inputTextField,BorderLayout.PAGE_START);
            searchingPanel.add(resultSeachList,BorderLayout.CENTER);

            // add component to history card
            historyPanel.add(new JLabel("History search"), BorderLayout.PAGE_START);
            historyPanel.add(historySeachTable, BorderLayout.CENTER);

            // add card to body panel
            bodyPanel.add(searchingPanel,"search-pane");
            bodyPanel.add(historySeachTable,"history-pane");

            // add to main panel
            add(headerPanel, BorderLayout.PAGE_START);
            add(bodyPanel,BorderLayout.CENTER);
            add(backSearchButton,BorderLayout.PAGE_END);
        }


        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            if(str.equals("search-by-slang-btn")){
                String key = inputTextField.getText();
                int index = data.searchBySlang(key);
                String[]results = data.getDefinitions(index);
                if(results == null){
                    results = new String[1];
                    results[0] = notFound;
                }
                resultSeachList.setListData(results);
                log.add(key,results);
            }
            else if(str.equals("search-by-definition-btn")){
                String key = inputTextField.getText();
                int[] indexes = data.searchByDefinition(key);
                if(indexes == null){

                }
                List results = new List(indexes.length);
                for(int i = 0; i < indexes.length; i++){

                    results.add(data.getSlang(indexes[i]));
                }

                resultSeachList.setListData(results.getItems());
                log.add(key,results.getItems());
            }
            else if(str.equals("history-btn")){

            }

        }
    }


    /**
     * define random panel
     */
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
            headerPanel.add(backRandomButton);

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

    /**
     * define menu panel
     */
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

    /**
     * constructor for layout
     */
    public layout(){
        // initialize the dictionary
        data = new dictionary();

        //initialize the component
        initializeComponent();

        // body panel
        bodyPanel = new JPanel(new CardLayout());

        // initialize the card pane
        JPanel menuPanel = new menuPanel();
        JPanel randomPanel = new randomPanel();
        JPanel searchPanel = new searchPanel();
        bodyPanel.add(menuPanel,"menu");
        bodyPanel.add(randomPanel,"random");
        bodyPanel.add(searchPanel,"search");
        // show GUI
        createAndShowGUI(bodyPanel);
    }

    private void createBackToMainMenu(JButton back) {
        back.setText("Back to main menu");
        back.addActionListener(this);
        back.setActionCommand("back");
    }
    private void initializeComponent(){
        // init
        searchButton = new JButton("Search");
        settingButton = new JButton("Settings");
        quizzButton = new JButton("Quizz");
        randomButton = new JButton("Random");
        backSearchButton = new JButton();
        backRandomButton = new JButton();

        // create back button
        createBackToMainMenu(backSearchButton);
        createBackToMainMenu(backRandomButton);

        // action lister
        randomButton.addActionListener(this);
        randomButton.setActionCommand("random_menu_btn");
        searchButton.addActionListener(this);
        searchButton.setActionCommand("search_menu_btn");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        CardLayout cl = (CardLayout)(bodyPanel.getLayout());
        if(str == "random_menu_btn"){
            cl.show(bodyPanel,"random");
        }
        else if(str == "search_menu_btn"){
            cl.show(bodyPanel,"search");
        }
        else if(str == "setting_menu_btn"){

        }
        else if(str == "quizz_menu_btn"){

        }
        else if(str == "back")
        {
            cl.show(bodyPanel,"menu");
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
