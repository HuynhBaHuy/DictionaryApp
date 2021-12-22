package vn.edu.hcmus.student._19127420.app;/*..
 * vn.edu.hcmus.student._19127420.app
 * Created by HuynhBaHuy
 * Date 12/22/2021 8:26 PM
 * Description:...
 */

import vn.edu.hcmus.student._19127420.data.slangWord;

public class logChange {
    public enum action{ADD,EDIT,REMOVE};
    action action;
    slangWord data;
    public logChange(action action,slangWord data){
        this.action = action;
        this.data = data;
    }
    public action getAction(){
      return this.action;
    }
    public slangWord getData(){ return this.data;}
}
