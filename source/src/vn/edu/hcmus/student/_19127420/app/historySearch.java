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

public class historySearch {
    List<logSearch> log;
    public historySearch(){
        log = new ArrayList<logSearch>();
    }
    public historySearch(String input, String[] result){
        log = new ArrayList<logSearch>();
        logSearch timestamp = new logSearch(input,result);
        log.add(timestamp);
    }
    public String[] add(String input, String[] result){
        logSearch logS = new logSearch(input,result);
        log.add(logS);
        String[] row = new String[3];
        row[0] = logS.getTime();
        row[1] = logS.getInput();
        String str = "";
        for(int j=0;j<result.length;j++){
            if(j ==0 || j== result.length - 1){
                str += " "+result[j];
            }
            else{
                str += ", "+result[j];
            }
        }
        row[2] = str;
        return row;
    }
    public List<logSearch> getLog(){return log;}
    public int getLength(){return log.size();}
    /**
     * Only for testing
     * @param args
     */
    public static void main(String[] args){
        dictionary data = new dictionary();
        slangWord a = data.randomSlang();
        historySearch his = new historySearch(a.getSlang(),a.getMeaning());
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
