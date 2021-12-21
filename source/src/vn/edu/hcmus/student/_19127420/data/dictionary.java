package vn.edu.hcmus.student._19127420.data;/*..
 * vn.edu.hcmus.student._19127420.data
 * Created by HuynhBaHuy
 * Date 12/20/2021 4:36 PM
 * Description: define dictionary class which contains a list of slang
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class dictionary {
    private final List<slangWord> slangArray;

    /**
     * Constructor
     */
    public dictionary(){
        slangArray = new ArrayList();
        loadData("slang.txt");
        Collections.sort(slangArray);
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
     * remove slang by its index
     * @param index: INT
     */
    public void removeSlang(int index){
        if(slangArray.isEmpty()){
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
        return new int[3];
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
