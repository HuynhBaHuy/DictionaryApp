package vn.edu.hcmus.student._19127420.app;/*..
 * vn.edu.hcmus.student._19127420.app
 * Created by HuynhBaHuy
 * Date 12/22/2021 8:37 PM
 * Description:...
 */

import vn.edu.hcmus.student._19127420.data.slangWord;

import java.util.ArrayList;
import java.util.List;

public class historyChange {
    List<logChange> log;

    public historyChange(){
        log = new ArrayList();
    }
    public void add(logChange.action action, slangWord data){
        logChange newLog = new logChange(action,data);
        log.add(newLog);
    }

    public void clear(){
        log.removeAll(log);
    }

    public List<logChange> getLog(){return log;}

}
