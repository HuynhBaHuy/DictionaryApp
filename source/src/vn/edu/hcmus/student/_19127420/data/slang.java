package vn.edu.hcmus.student._19127420.data;
/*..
 * vn.edu.hcmus.student._19127420.data
 * Created by HuynhBaHuy
 * Date 12/20/2021 4:33 PM
 * Description: define slang word class which contains symbol and meaning
 */

public class slang implements Comparable<slang> {
    private String symbol;
    private String[] meaning;
    public slang(){
        symbol = "";
        meaning = new String[0];
    }

    /**
     * Override the method compareTo of Comparable interface
     * @param s
     * @return positive number if this > s, 0 if equal, otherwise negative number
     */
    @Override
    public int compareTo(slang s){
        return symbol.compareTo(s.symbol);
    }
    public slang(String symbol, String[] meaning) {
        this.symbol = symbol;
        this.meaning = meaning;
    }
    public String[] getMeaning() {
        return meaning;
    }
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public void setMeaning(String[] meaning) {
        this.meaning = meaning;
    }
    public void addMeaning(String meaning) {
        if( this.meaning.length <=0) {
            this.meaning = new String[1];
            this.meaning[1] = meaning;
        }
        else{
            int length = this.meaning.length;
            String[] temp = new String[length + 1];
            for(int i=0;i<length;i++){
                temp[i] = this.meaning[i];
            }
            temp[length] = meaning;
            this.meaning = temp;
        }
    }

    /**
     * only for testing or logging
     * print to console
     */
    public void display(){
        String m ="";
        for (int i=0;i<meaning.length;i++){
            if(i==0 || i== meaning.length-1){
                m+=meaning[i];
            }
            m+= "| "+meaning[i];
        }
        System.out.println(symbol + ": " + m);
    }
}
