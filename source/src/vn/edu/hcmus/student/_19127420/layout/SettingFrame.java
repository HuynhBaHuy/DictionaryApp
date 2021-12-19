package vn.edu.hcmus.student._19127420.layout;/*..
 * vn.edu.hcmus.student._19127420.layout
 * Created by HuynhBaHuy
 * Date 12/20/2021 1:41 AM
 * Description:...
 */

import javax.swing.*;

public class SettingFrame extends JFrame {
    private JPanel SettingPanel;
    private JButton addSlangButton;
    private JButton editSlangButton;
    private JButton deleteSlangButton;
    private JButton resetChangesButton;
    private JList slangList;

    public SettingFrame(){
        setDefaultCloseOperation(javax.swing.
                WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Setting");
        setContentPane(SettingPanel);
        pack();

    }
}
