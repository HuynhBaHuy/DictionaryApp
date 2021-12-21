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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    history log;
    class searchPanel extends JPanel implements  ActionListener{
        final String notFound = "404 NOT FOUND";

        JPanel headerPanel;
        JPanel bodySearchPanel;
        JPanel searchingPanel;
        JPanel historyPanel;

        JButton searchBySlangBtn;
        JButton searchByDefinitionBtn;
        JButton historyBtn;
        JButton backBtn;
        JTextField inputTextField;
        JList<String> resultSeachList;
        JTable historySearchTable;


        /**
         * constructor for search panel
         */
        public searchPanel(){
            setLayout(new BorderLayout());
            // init panel
            headerPanel = new JPanel(new FlowLayout());
            bodySearchPanel = new JPanel(new CardLayout());
            searchingPanel = new JPanel(new BorderLayout());
            historyPanel = new JPanel(new BorderLayout());



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

            backBtn = new JButton("Back to search");
            backBtn.addActionListener(this);
            backBtn.setActionCommand("back-to-search-btn");
            // init other component in search card
            inputTextField = new JTextField();
            resultSeachList = new JList<String>();

            // init component in history card
            String[] columnNames = {"Time start", "Key search","Results"};
            int length = log.getLength();
            String[][] logData = new String[length][3];
            for (int i = 0; i < length; i++){
                logData[i][0] = log.getLog().get(i).getTime();
                logData[i][1] = log.getLog().get(i).getInput();
                String[] resultArray = log.getLog().get(i).getResult();
                String result = "";
                for(int j=0;j<resultArray.length;j++){
                    if(j ==0 || j== resultArray.length - 1){
                        result += " "+resultArray[j];
                    }
                    else{
                        result += ", "+resultArray[j];
                    }
                }
                logData[i][2] = result;
            }
            historySearchTable = new JTable(new DefaultTableModel(logData,columnNames));

            // add button to header pane
            headerPanel.add(searchBySlangBtn);
            headerPanel.add(searchByDefinitionBtn);
            headerPanel.add(historyBtn);

            // add component to search card
            searchingPanel.add(inputTextField,BorderLayout.PAGE_START);
            searchingPanel.add(resultSeachList,BorderLayout.CENTER);

            // add component to history card
            historyPanel.add(new JLabel("History search"), BorderLayout.PAGE_START);
            historyPanel.add(historySearchTable, BorderLayout.CENTER);
            historyPanel.add(backBtn,BorderLayout.LINE_END);
            // add card to body panel
            bodySearchPanel.add(searchingPanel,"search-pane");
            bodySearchPanel.add(historyPanel,"history-pane");

            // add to main panel
            add(headerPanel, BorderLayout.PAGE_START);
            add(bodySearchPanel,BorderLayout.CENTER);
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
            CardLayout cl = (CardLayout)(bodySearchPanel.getLayout());
            if(str.equals("search-by-slang-btn")){
                String key = inputTextField.getText();
                int index = data.searchBySlang(key);
                String[]results = data.getDefinitions(index);
                if(results == null){
                    results = new String[1];
                    results[0] = notFound;
                }
                resultSeachList.setListData(results);
                String[] row  = log.add(key,results);
                DefaultTableModel model = (DefaultTableModel) historySearchTable.getModel();
                model.insertRow(0,row);
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
                String[] row  = log.add(key,results.getItems());
                DefaultTableModel model = (DefaultTableModel) historySearchTable.getModel();
                model.insertRow(0,row);
            }
            else if(str.equals("history-btn")){
                searchBySlangBtn.setEnabled(false);
                searchByDefinitionBtn.setEnabled(false);
                cl.show(bodySearchPanel,"history-pane");
            }
            else if(str.equals("back-to-search-btn")){
                searchBySlangBtn.setEnabled(true);
                searchByDefinitionBtn.setEnabled(true);
                cl.show(bodySearchPanel,"search-pane");
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
        // initialize search log
        log = new history();


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
