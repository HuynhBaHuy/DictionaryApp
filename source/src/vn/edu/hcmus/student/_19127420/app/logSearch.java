package vn.edu.hcmus.student._19127420.app;/*..
 * vn.edu.hcmus.student._19127420.app
 * Created by HuynhBaHuy
 * Date 12/20/2021 8:55 PM
 * Description:Define searching class
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class logSearch {
    private String startTime;
    private String input;
    private String[] result;

    /**
     * Fully constructor
     * @param input: STRING
     * @param result:STRING[]
     */
    public logSearch(String input, String[] result) {
        DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.startTime = datetime.format(now);

        this.input = input;
        this.result = result;
    }
    public String getTime() {
        return startTime;
    }
    public String getInput() { return input; }
    public String[] getResult() { return result; }
}
