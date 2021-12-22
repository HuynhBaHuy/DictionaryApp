package vn.edu.hcmus.student._19127420.data;/*..
 * vn.edu.hcmus.student._19127420.data
 * Created by HuynhBaHuy
 * Date 12/20/2021 4:36 PM
 * Description: define dictionary class which contains a list of slang
 */

import vn.edu.hcmus.student._19127420.app.historyChange;
import vn.edu.hcmus.student._19127420.app.logChange;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class dictionary {
    private final List<slangWord> slangArray;
    HashMap<String, List<String>> map;
    /**
     * Constructor
     */
    public dictionary(){
        slangArray = new ArrayList();
        map = new HashMap();
        loadData("slang.txt");
        Collections.sort(slangArray);
    }
    public String[] getListSlangWords() {
        String[] result = new String[slangArray.size()];
        int i = 0;
        if (slangArray == null) {
            return null;
        } else {
            for (slangWord s : slangArray) {
                String row = s.getSlang() + ": ";
                int length = s.getMeaning().length;
                for (int j=0;j<length;j++) {
                    String meaning = s.getMeaning()[j];
                    if(j==0) {
                        row += meaning;
                    }else{
                        row += "| "+ meaning ;
                    }

                }
                result[i] = row;
                i = i + 1;
            }
            return result;
        }
    }

    /**
     * add slang to dictionary
     * @param symbol :STRING
     * @param meanings: STRING[]
     * @return true if adding slang successful, otherwise return false
     */
    public boolean addSlang(String symbol, String[] meanings){
        int index = searchBySlang(symbol);
        if(index<0) {
            slangArray.add(new slangWord(symbol, meanings));
            Collections.sort(slangArray);
            return true;
        }
        else{
            return false;
        }
    }
    public void reset(historyChange history){
        List<logChange> logArray = history.getLog();
        for(logChange singleLog : logArray){
            if(singleLog.getAction()== logChange.action.ADD){
                String slang = singleLog.getData().getSlang();
                removeSlang(slang);
            }
            else if(singleLog.getAction() == logChange.action.EDIT){
                String slang = singleLog.getData().getSlang();
                String[] meanings = singleLog.getData().getMeaning();
                editSlang(slang,meanings);
            }
            else{
                String slang = singleLog.getData().getSlang();
                String[] meanings = singleLog.getData().getMeaning();
                addSlang(slang,meanings);
            }
        }
    }
    /**
     * edit a slang of dictionary
     * @param symbol: STRING
     * @param meanings: STRING[]
     * @return true if edit success, false for do not exist symbol
     */
    public boolean editSlang(String symbol, String[] meanings){
        int index = searchBySlang(symbol);
        if(index<0){
            return false;
        }
        slangArray.set(index, new slangWord(symbol,meanings));
        return true;
    }

    /**
     * remove a slang by its slang
     * @param slang:STRING
     */
    public void removeSlang(String slang){
        if(slangArray.isEmpty()){
            return;
        }
        int index = searchBySlang(slang);
        if(index<0 || index > slangArray.size()){
            return;
        }
        slangArray.remove(index);
    }

    /**
     * get slang randomly
     * @return slang
     */
    public slangWord randomSlang() {
        Random rand = new Random();
        int index = rand.nextInt(slangArray.size());
        return slangArray.get(index);
    }

    /**
     * get slang at index
     * @param index:INT
     * @return :STRING
     */
    public String getSlang(int index){
        if(index<0 || index>=slangArray.size()){
            return null;
        }
        return slangArray.get(index).getSlang();
    }

    /**
     *  get definition of slang word at index
     * @param index: INT
     * @return :STRING[]
     */
    public String[] getDefinitions(int index){
        if(index<0 || index>=slangArray.size()){
            return null;
        }
        return slangArray.get(index).getMeaning();
    }
    /**
     * Search the definition of slang use binary search
     * @param slang: String
     * @return index of this slang or null if
     */
    public int searchBySlang(String slang){
        slangWord temp = new slangWord(slang,new String[0]);
        int index = Collections.binarySearch(slangArray, temp);

        return index;
    }
    /**
     * search by definition
     * @param key:STRING
     * @return array of index
     */
    public int[] searchByDefinition(String key){
        key = key.toLowerCase();
        List<String> values = map.get(key);
        if(values == null){
            return null;
        }
        int[] result = new int[values.size()];
        for(int i=0;i<values.size();i++){
            String value = values.get(i);
            result[i] = searchBySlang(value);
        }
        return result;
    }

    /**
     * Load data from slang.txt
     * @param source: String, path to slang.txt
     */
    public void loadData(String source){
        try {
            BufferedReader br = new BufferedReader(new FileReader(source));
            br.readLine();
            while (true){
                String str = br.readLine();
                if(str == null){
                    break;
                }
                String[] slang = str.split("`");
                String symbol = slang[0];
                slangWord s = new slangWord();
                s.setSlang(symbol);
                if(slang.length<2){
                    System.out.println(slang[0]);
                }
                String meanings = slang[1];
                String[] meaning = meanings.split("\\| ");
                s.setMeaning(meaning);
                this.slangArray.add(s);
                meanings = meanings.replaceAll("\\W", " ");
                String[] partsMeaning = meanings.split(" ");
                for(String part:partsMeaning){
                    String lowerCasePart = part.toLowerCase();
                    if(map.containsKey(lowerCasePart)){
                        map.get(lowerCasePart).add(symbol);
                    }
                    else{
                        List<String> values = new ArrayList<String>();
                        values.add(symbol);
                        map.put(lowerCasePart,values);
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * only for testing
     * @param args
     */
    public static void main(String[] args){
        dictionary data = new dictionary();
        slangWord random = data.randomSlang();
        String[] definition = data.getDefinitions(data.searchBySlang("23"));
        random.display();
    }
}
