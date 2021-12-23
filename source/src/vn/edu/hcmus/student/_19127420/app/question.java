package vn.edu.hcmus.student._19127420.app;/*..
 * vn.edu.hcmus.student._19127420.app
 * Created by HuynhBaHuy
 * Date 12/23/2021 6:35 PM
 * Description:...
 */

import vn.edu.hcmus.student._19127420.data.dictionary;
import vn.edu.hcmus.student._19127420.data.slangWord;

import java.util.Random;

public class question {
    private String ques;
    private String correctAnswer;
    private String[] wrongAnswer;
    public question(dictionary data){
        slangWord sw = data.randomSlang();
        ques = sw.getSlang();
        Random rand = new Random();
        wrongAnswer = new String[3];
        for(int i=0;i<3;i++){
            slangWord temp = data.randomSlang();
            while(temp == sw){
                temp = data.randomSlang();
            }
            String[] meaningTemp = temp.getMeaning();
            int length = meaningTemp.length;
            int index = rand.nextInt(length);
            wrongAnswer[i] = meaningTemp[index];
        }
        String[] means = sw.getMeaning();
        int length = means.length;
        int index = rand.nextInt(length);
        correctAnswer = means[index];
    }
    public String getQuestion(){
        return ques;
    }
    public String getCorrectAnswer(){return correctAnswer;}
    public String[] getFullAnswers(){
        String [] answers = new String[4];
        Random rand = new Random();
        int indexCorrect = rand.nextInt(4);
        answers[indexCorrect] = correctAnswer;
        for(int i=0;i<4;i++){
            if(i!=indexCorrect){
                if(i<indexCorrect){
                    answers[i] = wrongAnswer[i];
                }
                else{
                    answers[i] = wrongAnswer[i-1];
                }
            }
        }
        return answers;
    }
}
