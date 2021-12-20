package vn.edu.hcmus.student._19127420.app;/*..
 * vn.edu.hcmus.student._19127420.app
 * Created by HuynhBaHuy
 * Date 12/21/2021 12:11 AM
 * Description: define history class
 */

import vn.edu.hcmus.student._19127420.data.dictionary;
import vn.edu.hcmus.student._19127420.data.slangWord;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class history {
    List<logSearch> log;
    public history(){
        log = new ArrayList<logSearch>();
    }
    public history(String input, String[] result){
        log = new ArrayList<logSearch>();
        logSearch timestamp = new logSearch(input,result);
        log.add(timestamp);
    }
    public void add(String input, String[] result){
        log.add(new logSearch(input,result));
    }
    public List<logSearch> getLog(){return log;}
    /**
     * Only for testing
     * @param args
     */
    public static void main(String[] args){
        dictionary data = new dictionary();
        slangWord a = data.randomSlang();
        history his = new history(a.getSlang(),a.getMeaning());
        try {
            TimeUnit.MINUTES.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a = data.randomSlang();
        his.add(a.getSlang(),a.getMeaning());
        System.out.println(his.getLog().get(0).getTime() +" "+ his.getLog().get(0).getInput() +": "+ his.getLog().get(0).getResult()[0]);
        System.out.println(his.getLog().get(1).getTime() +" "+ his.getLog().get(1).getInput() +": "+ his.getLog().get(1).getResult()[0]);
    }
}
