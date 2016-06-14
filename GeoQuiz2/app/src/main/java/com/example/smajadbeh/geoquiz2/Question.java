package com.example.smajadbeh.geoquiz2;

/**
 * Created by smajadbeh on 6/14/2016.
 */
public class Question {

    private int q;
    private boolean answer;


    public Question(int ques, boolean ans){
        q=ques;
        answer=ans;
    }

    public int getQ(){
        return q;
    }
    public void setQ(int ques){
        q=ques;
    }

    public boolean getAnswer(){
        return answer;
    }
    public void setAnswer(boolean ans){
        answer=ans;
    }


}
