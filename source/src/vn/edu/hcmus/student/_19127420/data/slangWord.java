package vn.edu.hcmus.student._19127420.data;
/*..
 * vn.edu.hcmus.student._19127420.data
 * Created by HuynhBaHuy
 * Date 12/20/2021 4:33 PM
 * Description: define slang word class which contains symbol and meaning
 */

public class slangWord implements Comparable<slangWord> {
    private String slang;
    private String[] meaning;
    public slangWord(){
        slang = "";
        meaning = new String[0];
    }

    /**
     * Override the method compareTo of Comparable interface
     * @param s
     * @return positive number if this > s, 0 if equal, otherwise negative number
     */
    @Override
    public int compareTo(slangWord s){
        return slang.compareTo(s.slang);
    }
    public slangWord(String slang, String[] meaning) {
        this.slang = slang;
        this.meaning = meaning;
    }
    public String[] getMeaning() {
        return meaning;
    }
    public String getSlang() {
        return slang;
    }
    public void setSlang(String slang) {
        this.slang = slang;
    }
    public void setMeaning(String[] meaning) {
        this.meaning = meaning;
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
            else{
                m+= "| "+meaning[i];
            }

        }
        System.out.println(slang + ": " + m);
    }
}
