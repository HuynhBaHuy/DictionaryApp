package vn.edu.hcmus.student._19127420.app;/*..
 * vn.edu.hcmus.student._19127420.app
 * Created by HuynhBaHuy
 * Date 12/23/2021 2:49 AM
 * Description:...
 */

import vn.edu.hcmus.student._19127420.data.slangWord;

import java.util.ArrayList;
import java.util.List;

public class quiz {
    List<question> test;

    public quiz(){
        test = new ArrayList<question>(20);
    }

    static class question {
        slangWord result;
        String[] results;
        public question(){

        }
    }
}
