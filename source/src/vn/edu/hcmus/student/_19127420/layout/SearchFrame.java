package vn.edu.hcmus.student._19127420.layout;/*..
 * vn.edu.hcmus.student._19127420.layout
 * Created by HuynhBaHuy
 * Date 12/20/2021 1:28 AM
 * Description:...
 */

import javax.swing.*;

public class SearchFrame extends JFrame {
    private JPanel SearchPane;
    private JPanel headerPanel;
    private JPanel controlPanel;
    private JButton searchBySlangWordButton;
    private JButton searchByDefinitionButton;
    private JPanel bodyPanel;
    private JTextField textField1;
    private JButton searchButton;
    private JList resultList;
    private JLabel nameSearch;
    private JButton historySearchButton;

    public SearchFrame(){
        setDefaultCloseOperation(javax.swing.
                WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(SearchPane);
        pack();
        setVisible(true);
    }
}
