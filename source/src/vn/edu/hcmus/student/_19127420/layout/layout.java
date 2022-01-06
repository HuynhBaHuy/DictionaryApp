package vn.edu.hcmus.student._19127420.layout;/*..
 * vn.edu.hcmus.student._19127420.layout
 * Created by HuynhBaHuy
 * Date 12/21/2021 1:30 AM
 * Description:...
 */

import vn.edu.hcmus.student._19127420.app.historyChange;
import vn.edu.hcmus.student._19127420.app.historySearch;
import vn.edu.hcmus.student._19127420.app.logChange;
import vn.edu.hcmus.student._19127420.app.quiz;
import vn.edu.hcmus.student._19127420.app.question;
import vn.edu.hcmus.student._19127420.data.dictionary;
import vn.edu.hcmus.student._19127420.data.slangWord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.SwingConstants.CENTER;
import static vn.edu.hcmus.student._19127420.app.logChange.action.*;

/**
 * define main frame
 */
public class layout extends JFrame  implements ActionListener {
    JPanel bodyPanel;
    JButton searchButton;
    JButton settingButton;
    JButton quizButton;
    JButton randomButton;
    JButton backSearchButton;
    JButton backRandomButton;
    JButton backSettingsButton;
    JButton backQuizButton;
    dictionary data;
    historySearch logSearch;
    historyChange logChange;
    quiz test;
    /**
     * define search panel
     */
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
        JList resultSeachList;
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
            JLabel inputLabel = new JLabel("Seach");
            inputTextField = new JTextField();
            resultSeachList = new JList();
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new BoxLayout(inputPanel,BoxLayout.X_AXIS));
            inputPanel.add(inputLabel);
            inputPanel.add(Box.createRigidArea(new Dimension(20,0)));
            inputPanel.add(inputTextField);
            // init component in history card
            String[] columnNames = {"Time start", "Key search","Results"};
            int length = logSearch.getLength();
            String[][] logData = new String[length][3];
            for (int i = 0; i < length; i++){
                logData[i][0] = logSearch.getLog().get(i).getTime();
                logData[i][1] = logSearch.getLog().get(i).getInput();
                String[] resultArray = logSearch.getLog().get(i).getResult();
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
            searchingPanel.add(inputPanel,BorderLayout.PAGE_START);
            JScrollPane scrollPane = new JScrollPane(resultSeachList);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            searchingPanel.add(scrollPane,BorderLayout.CENTER);

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
                String[] row  = logSearch.add(key,results);
                DefaultTableModel model = (DefaultTableModel) historySearchTable.getModel();
                model.insertRow(0,row);
            }
            else if(str.equals("search-by-definition-btn")){
                String key = inputTextField.getText();
                int[] indexes = data.searchByDefinition(key);
                List result = new List();
                if(indexes == null){
                    result.add("404 not found");
                }
                else{
                    for(int i = 0; i < indexes.length; i++){
                        result.add(data.getSlangWordStringAt(indexes[i]));
                    }
                }
                resultSeachList.setListData(result.getItems());
                String[] row  = logSearch.add(key,result.getItems());
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
            slangLabel = new JLabel(randSw.getSlang(), CENTER);
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
            menuLabel = new JLabel("Menu", CENTER);
            menuLabel.setAlignmentX(Label.CENTER);
            bodyPanel = new JPanel(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.insets = new Insets(5,10,5,10);
            constraints.anchor = GridBagConstraints.CENTER;
            constraints.weighty = 1.0;
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.ipadx = 100;
            bodyPanel.add(randomButton,constraints);
            constraints.gridy = 1;
            bodyPanel.add(searchButton,constraints);
            constraints.gridy = 2;
            bodyPanel.add(settingButton,constraints);
            constraints.gridy = 3;
            bodyPanel.add(quizButton,constraints);
            add(menuLabel, BorderLayout.PAGE_START);
            add(bodyPanel, BorderLayout.CENTER);
        }

    }

    /**
     * define quiz panel
     */
    class quizPanel extends JPanel implements ActionListener {
        JLabel header;
        JPanel headerPanel;
        JPanel bodyPanel;
        JPanel questionPanel;
        JPanel answersPanel;
        JPanel footerPanel;
        JButton answerBtnA;
        JButton answerBtnB;
        JButton answerBtnC;
        JButton answerBtnD;
        JLabel questionLabel;
        JButton resetBtn;
        JPanel scorePanel;
        JLabel scoreLabel;
        JLabel countQuestionLabel;
        public quizPanel(){
            setLayout(new BorderLayout());
            test = new quiz(data);

            initializeComponent();
            // header and footer
            headerPanel.add(header);
            footerPanel.add(backQuizButton);
            footerPanel.add(resetBtn);
            //body panel
            bodyPanel.setBackground(Color.WHITE);
            bodyPanel.setBorder(BorderFactory.createLineBorder(new Color(153,153,255)));


            // score
            scorePanel.add(scoreLabel);
            scorePanel.setBackground(new Color(255,229,204));
            scorePanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            scoreLabel.setVerticalAlignment(1);
            // question
            questionPanel.add(questionLabel);
            questionPanel.setBackground(Color.WHITE);
            questionLabel.setFont(new Font("Ink Free", Font.PLAIN,25));

            // answer
            Color buttonColor = new Color(0,128,255);
            Font fontBtn = new Font("Ink Free", Font.BOLD,15);
            answersPanel.setBackground(Color.WHITE);

            answerBtnA.setFont(fontBtn);
            answerBtnA.setBackground(buttonColor);


            answerBtnB.setFont(fontBtn);
            answerBtnB.setBackground(buttonColor);


            answerBtnC.setFont(fontBtn);
            answerBtnC.setBackground(buttonColor);


            answerBtnD.setFont(fontBtn);
            answerBtnD.setBackground(buttonColor);

            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 0.5;
            c.weighty = 0.5;
            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(10,10,5,10);
            answersPanel.add(answerBtnA,c);
            c.gridx = 0;
            c.gridy = 1;
            answersPanel.add(answerBtnB,c);
            c.gridx = 1;
            c.gridy = 0;
            answersPanel.add(answerBtnC,c);
            c.gridx = 1;
            c.gridy = 1;
            answersPanel.add(answerBtnD,c);

            //add grid bag constraint for body pane
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.weightx = 1.0;
            constraints.anchor = GridBagConstraints.FIRST_LINE_START;
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.ipadx = 20;
            constraints.ipady = 10;
            bodyPanel.add(scorePanel,constraints);
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.gridwidth = GridBagConstraints.REMAINDER;
            bodyPanel.add(questionPanel,constraints);
            constraints.gridx = 0;
            constraints.gridy = 4;
            constraints.gridwidth = GridBagConstraints.REMAINDER;
            constraints.gridheight = GridBagConstraints.REMAINDER;
            bodyPanel.add(answersPanel,constraints);

            // set data
            loadQuestion(test.getFirstQuestion());
            add(headerPanel, BorderLayout.PAGE_START);
            add(bodyPanel, BorderLayout.CENTER);
            add(footerPanel,BorderLayout.PAGE_END);
        }
        void initializeComponent(){
            header = new JLabel("Quiz");
            headerPanel = new JPanel(new FlowLayout());
            bodyPanel = new JPanel(new GridBagLayout());
            footerPanel = new JPanel(new FlowLayout());
            questionPanel = new JPanel(new FlowLayout());
            answersPanel = new JPanel(new GridBagLayout());
            answerBtnA = new JButton();
            answerBtnB = new JButton();
            answerBtnC = new JButton();
            answerBtnD = new JButton();
            questionLabel = new JLabel();
            scorePanel = new JPanel(new FlowLayout());
            scoreLabel = new JLabel();
            countQuestionLabel = new JLabel();
            resetBtn = new JButton("Reset");
            // action listener
            answerBtnA.addActionListener(this);
            answerBtnB.addActionListener(this);
            answerBtnC.addActionListener(this);
            answerBtnD.addActionListener(this);
            resetBtn.addActionListener(this);
            resetBtn.setActionCommand("reset_btn");
            answerBtnA.setActionCommand("answerA_btn");
            answerBtnB.setActionCommand("answerB_btn");
            answerBtnC.setActionCommand("answerC_btn");
            answerBtnD.setActionCommand("answerD_btn");

        }
        void loadScore(){
            int score = test.getScore();
            int maxScore = test.getMaxScore();
            int index = test.getIndexQuestion();
            int maxQuestion = test.getMaxQuestion();
            scoreLabel.setText(score+"/"+maxScore);
            countQuestionLabel.setText(index+"/"+maxQuestion);
        }
        void loadQuestion(question q) {
            String question = q.getQuestion();
            String[] answers = q.getFullAnswers();
            questionLabel.setText(question +" là gì? ");
            answerBtnA.setText(answers[0]);
            answerBtnB.setText(answers[1]);
            answerBtnC.setText(answers[2]);
            answerBtnD.setText(answers[3]);
            loadScore();
        }

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            if(str.equals("reset_btn")){
                int option = JOptionPane.showConfirmDialog(bodyPanel,"Do you want to reset the game? ","Confirm reset",JOptionPane.YES_NO_OPTION);
                if(option == JOptionPane.YES_OPTION){
                    test.newGame(data);
                    loadQuestion(test.getFirstQuestion());
                }
            }
            else{
                String answers = "";
                if(str.equals("answerA_btn")){
                    answers = answerBtnA.getText();
                }
                else if(str.equals("answerB_btn")){
                    answers = answerBtnB.getText();
                }
                else if(str.equals("answerC_btn")){
                    answers = answerBtnC.getText();
                }
                else if(str.equals("answerD_btn")){
                    answers = answerBtnD.getText();
                }
                int isCorrect = test.answers(answers);
                if(isCorrect == 1){
                    loadQuestion(test.nextQuestion());
                }
                else if(isCorrect == 0){
                    loadScore();
                    int option = JOptionPane.showConfirmDialog(bodyPanel,"You win. Play again? ","Congratulations!!!",JOptionPane.YES_NO_OPTION);
                    if(option == JOptionPane.YES_OPTION){
                        test.newGame(data);
                        loadQuestion(test.getFirstQuestion());
                    }
                    else{
                    }
                }
                else {
                    int option = JOptionPane.showConfirmDialog(bodyPanel,"Your score: "+test.getScore()+"/"+test.getMaxScore()+". Another game?","Quizz result",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
                    if(option == JOptionPane.YES_OPTION){
                        test.newGame(data);
                        loadQuestion(test.getFirstQuestion());
                    }
                    else {

                    }
                }
            }

        }
    }

    /**
     * define setting panel
     */
    class settingPanel extends JPanel implements  ActionListener{
        final DefaultListModel<String> model = new DefaultListModel<>();
        JPanel bodyPanel;
        JPanel controlPanel;
        JPanel footerPanel;
        JButton addBtn;
        JButton editBtn;
        JButton removeBtn;
        JButton actionBtn;
        JButton resetBtn;
        JPanel addPanel;
        JLabel addSlangLabel;
        JLabel addDefinitionLabel;
        JTextField addSlangTextField;
        JTextArea addDefinitionTextField;

        JPanel editPanel;
        JTextField chooseSlangTextField;
        JButton findBtn;
        JLabel editSlangLabel;
        JLabel editDefinitionLabel;
        JTextArea editDefinitionTextArea;

        JPanel removePanel;
        JList listSlang;

        // first card
        logChange.action action = ADD;
        public settingPanel(){
            // set layout
            setLayout(new BorderLayout());
            // initialize component
            initializeComponent();

            // control panel
            createControlPanel();

            // add card
            createAddCard();

            // edit card
            createEditCard();

            // remove card
            createRemoveCard();

            // body panel
            bodyPanel.add(addPanel,"add_card");
            bodyPanel.add(editPanel,"edit_card");
            bodyPanel.add(removePanel,"remove_card");

            // footer panel
            footerPanel.add(backSettingsButton);
            footerPanel.add(Box.createRigidArea(new Dimension(20,0)));
            footerPanel.add(actionBtn);
            JPanel endPanel = new JPanel(new FlowLayout());
            endPanel.add(footerPanel);

            // add to main panel
            add(controlPanel,BorderLayout.LINE_START);
            add(bodyPanel,BorderLayout.CENTER);
            add(endPanel,BorderLayout.PAGE_END);
        }

        private void createControlPanel() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.anchor= GridBagConstraints.CENTER;
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.insets = new Insets(-1,10 ,-1 ,10 );
            constraints.weighty = 0.3;
            controlPanel.add(addBtn,constraints);
            constraints.gridy = 1;
            controlPanel.add(editBtn,constraints);
            constraints.gridy = 2;
            controlPanel.add(removeBtn,constraints);
            constraints.gridy = 3;
            controlPanel.add(resetBtn,constraints);
        }

        private void createRemoveCard() {
            for(String row:data.getListSlangWords()){
                model.addElement(row);
            }
            listSlang = new JList(model);
            listSlang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scrollPane = new JScrollPane(listSlang);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            removePanel.add(scrollPane,BorderLayout.CENTER);
        }

        private void createEditCard() {
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.insets = new Insets(5,5 ,5 ,5 );
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.weightx = 0.5;
            editPanel.add(editSlangLabel,constraints);
            constraints.gridx = 1;
            constraints.gridy = 0;
            constraints.gridwidth = 10;
            editPanel.add(chooseSlangTextField,constraints);
            constraints.gridx = 11;
            constraints.gridy = 0;
            editPanel.add(findBtn,constraints);

            constraints.gridx=0;
            constraints.gridy = 1;
            constraints.gridwidth=1;
            editPanel.add(editDefinitionLabel,constraints);
            constraints.gridx=1;
            constraints.gridy = 1;
            constraints.gridwidth =GridBagConstraints.REMAINDER;
            editPanel.add(editDefinitionTextArea,constraints);
        }

        private void createAddCard(){
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.anchor = GridBagConstraints.NORTH;
            constraints.insets = new Insets(0, 5, 5, 0);
            constraints.weightx = 0.2;
            constraints.gridx = 0;
            constraints.gridy = 1;
            addPanel.add(addSlangLabel,constraints);

            constraints.gridx = 1;
            constraints.gridy = 1;
            constraints.gridwidth = GridBagConstraints.REMAINDER;
            addPanel.add(addSlangTextField,constraints);

            constraints.gridx = 0;
            constraints.gridy = 2;
            addPanel.add(addDefinitionLabel,constraints);

            constraints.gridx = 1;
            constraints.gridy = 2;
            constraints.gridwidth = GridBagConstraints.REMAINDER;
            addPanel.add(addDefinitionTextField,constraints);
        }
        private void initializeComponent(){
            //initialize Component
            bodyPanel = new JPanel(new CardLayout());
            controlPanel = new JPanel();
            controlPanel.setLayout(new GridBagLayout());
            footerPanel = new JPanel();
            footerPanel.setLayout(new BoxLayout(footerPanel,BoxLayout.X_AXIS));
            addBtn = new JButton("Add");
            editBtn = new JButton("Edit");
            removeBtn = new JButton("Remove");
            resetBtn = new JButton("Reset");
            actionBtn = new JButton("Add");

            addPanel = new JPanel(new GridBagLayout());
            addSlangLabel = new JLabel("Slang");
            addDefinitionLabel = new JLabel("Definition");
            addSlangTextField = new JTextField(20);
            addDefinitionTextField = new JTextArea(7,3);

            editPanel = new JPanel(new GridBagLayout());
            chooseSlangTextField = new JTextField(10);
            findBtn = new JButton("Find");
            editDefinitionLabel = new JLabel("Edit definition of slang");
            editSlangLabel = new JLabel("Choose slang");
            editDefinitionTextArea = new JTextArea(7,3);
            editDefinitionTextArea.setEditable(false);
            removePanel = new JPanel(new BorderLayout());


            // add action listener
            addBtn.addActionListener(this);
            addBtn.setActionCommand("add_card");
            editBtn.addActionListener(this);
            editBtn.setActionCommand("edit_card");
            removeBtn.addActionListener(this);
            removeBtn.setActionCommand("remove_card");
            resetBtn.addActionListener(this);
            resetBtn.setActionCommand("reset_btn");
            actionBtn.addActionListener(this);
            actionBtn.setActionCommand("action_btn");
            findBtn.addActionListener(this);
            findBtn.setActionCommand("find_btn");
        }

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            CardLayout cl = (CardLayout)(bodyPanel.getLayout());
            if(str=="add_card"){
                action = ADD;
                actionBtn.setText("Add Slang");
                cl.show(bodyPanel,"add_card");
            }
            else if(str=="remove_card"){
                action = REMOVE;
                actionBtn.setText("Delete Slang");
                cl.show(bodyPanel,"remove_card");
            }
            else if(str=="edit_card"){
                action = EDIT;
                actionBtn.setText("Edit Slang");
                cl.show(bodyPanel,"edit_card");
            }
            else if(str=="reset_btn"){
                int output = JOptionPane.showConfirmDialog(bodyPanel,"Are you sure reset all changes","Confirm reset",JOptionPane.YES_NO_OPTION);
                if (output == JOptionPane.YES_OPTION) {
                    addSlangTextField.setText("");
                    addDefinitionTextField.setText("");
                    editDefinitionTextArea.setText("");
                    chooseSlangTextField.setText("");
                    model.removeAllElements();
                    data.reset(logChange);
                    logChange.clear();
                    for(String row:data.getListSlangWords()){
                        model.addElement(row);
                    }
                }
            }
            else if(str=="action_btn"){
                if(action.equals(ADD)){
                    String slang = addSlangTextField.getText();
                    String definition = addDefinitionTextField.getText();
                    String[] meanings = definition.split("\n");
                    Boolean isSuccess = data.addSlang(slang,meanings);
                    if(isSuccess){
                        slangWord newSW = new slangWord(slang,meanings);
                        logChange.add(action,newSW);
                        System.out.println("add_success");
                        JOptionPane.showMessageDialog(this,"Add slang world successful!!","Success",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        int output = JOptionPane.showConfirmDialog(this,"This slang already have existed. Do you want overwrite it? ","Failed",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE);
                        if(output == JOptionPane.YES_OPTION){
                            data.editSlang(slang,meanings);
                            slangWord newSW = new slangWord(slang,meanings);
                            vn.edu.hcmus.student._19127420.app.logChange.action edit = EDIT;
                            logChange.add(edit,newSW);
                        }
                        else{
                            addSlangTextField.setText("");
                            addDefinitionTextField.setText("");
                        }
                    }

                }
                else if(action.equals(EDIT)){
                    String slang = chooseSlangTextField.getText();
                    String definition = editDefinitionTextArea.getText();
                    String[] meanings = definition.split("\n");
                    Boolean isSuccess = data.editSlang(slang,meanings);
                    if(isSuccess){
                        slangWord newSW = new slangWord(slang,meanings);
                        logChange.add(action,newSW);
                        JOptionPane.showMessageDialog(this,"Edit slang world successful!!","Success",JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("edit_success");
                    }
                    else {
                        JOptionPane.showMessageDialog(this, "Something wrong. Please try again.", "Failed", JOptionPane.ERROR_MESSAGE);
                        System.out.println("edit_failed");
                    }
                }
                else if(action.equals(REMOVE)){
                    int output = JOptionPane.showConfirmDialog(bodyPanel,"Are you sure delete this slang","Confirm delete",JOptionPane.YES_NO_OPTION);
                    if (output == JOptionPane.YES_OPTION) {
                        String slangChosen = (String)listSlang.getSelectedValue();
                        String[] temp = slangChosen.split(":");
                        if(temp.length >= 1)
                        {
                            String slang = temp[0];
                            String[] meanings = temp[1].split("\\| ");
                            data.removeSlang(slang);
                            System.out.println(listSlang.getSelectedIndex());
                            int index = listSlang.getSelectedIndex();
                            slangWord removeSW = new slangWord(slang,meanings);
                            logChange.add(action,removeSW);
                            if(index != -1){
                                model.remove(index);
                            }

                        }
                    }
                }
            }
            else if(str == "find_btn"){
                editDefinitionTextArea.setText("");
                String slang = chooseSlangTextField.getText();
                int index = data.searchBySlang(slang);
                String[] meanings = data.getDefinitions(index);
                if(meanings == null){
                    editDefinitionTextArea.append("404 Not Found");

                    editDefinitionTextArea.setEditable(false);
                }
                else{
                    editDefinitionTextArea.setEditable(true);
                    for(String meaning : meanings){
                        editDefinitionTextArea.append(meaning);
                        editDefinitionTextArea.append("\n");
                    }
                }

            }
        }
    }
    /**
     * constructor for layout
     */
    public layout(){
        // initialize the dictionary
        data = new dictionary();

        // initialize search log
        logSearch = new historySearch();
        logChange = new historyChange();

        //initialize the component
        initializeComponent();

        // body panel
        bodyPanel = new JPanel(new CardLayout());

        // initialize the card pane
        JPanel menuPanel = new menuPanel();
        JPanel randomPanel = new randomPanel();
        JPanel searchPanel = new searchPanel();
        JPanel settingPanel = new settingPanel();
        JPanel quizPanel = new quizPanel();
        bodyPanel.add(menuPanel,"menu");
        bodyPanel.add(randomPanel,"random");
        bodyPanel.add(searchPanel,"search");
        bodyPanel.add(settingPanel,"settings");
        bodyPanel.add(quizPanel,"quiz");
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
        quizButton = new JButton("Quiz");
        randomButton = new JButton("Random");
        backSearchButton = new JButton();
        backRandomButton = new JButton();
        backSettingsButton = new JButton();
        backQuizButton = new JButton();
        // create back button
        createBackToMainMenu(backSearchButton);
        createBackToMainMenu(backRandomButton);
        createBackToMainMenu(backSettingsButton);
        createBackToMainMenu(backQuizButton);
        // action lister
        randomButton.addActionListener(this);
        randomButton.setActionCommand("random_menu_btn");
        searchButton.addActionListener(this);
        searchButton.setActionCommand("search_menu_btn");
        settingButton.addActionListener(this);
        settingButton.setActionCommand("setting_menu_btn");
        quizButton.addActionListener(this);
        quizButton.setActionCommand("quiz_menu_btn");
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
            cl.show(bodyPanel,"settings");
        }
        else if(str == "quiz_menu_btn"){
            cl.show(bodyPanel,"quiz");
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
