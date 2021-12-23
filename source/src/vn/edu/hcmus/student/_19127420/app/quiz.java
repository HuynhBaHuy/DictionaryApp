package vn.edu.hcmus.student._19127420.app;/*..
 * vn.edu.hcmus.student._19127420.app
 * Created by HuynhBaHuy
 * Date 12/23/2021 2:49 AM
 * Description:...
 */

import vn.edu.hcmus.student._19127420.data.dictionary;

import java.util.ArrayList;
import java.util.List;


public class quiz {
    List<question> test;
    int currentQuestion;
    int score;
    final int maxQuestion = 10;
    final int scoreEachQuestion = 10;
    public quiz(dictionary data){
        newGame(data);
    }
    public void newGame(dictionary data){
        currentQuestion = 0;
        score = 0;
        test = new ArrayList<>();
        for(int i = 0;i<maxQuestion;i++){
            test.add(new question(data));
        }
    }
    public int getMaxQuestion(){
        return maxQuestion;
    }
    public  int getScore(){
        return score;
    }
    public int getIndexQuestion(){
        return currentQuestion;
    }
    public int getMaxScore(){
        return maxQuestion* scoreEachQuestion;
    }
    public question getFirstQuestion(){
        return test.get(currentQuestion);
    }
    public question nextQuestion(){
        currentQuestion++;
        return test.get(currentQuestion);
    }

    /**
     * check answer
     * @param ans: STRING
     * @return -1 if wrong answers, 0 if win game, 1 if correct answer but not win game
     */
    public int answers(String ans){
        if(test.get(currentQuestion).getCorrectAnswer().equals(ans)){
            if(score + scoreEachQuestion < getMaxScore()){
                return 1;
            }
            score += scoreEachQuestion;
            return 0;
        }
        else{
            return -1;
        }
    }

}
