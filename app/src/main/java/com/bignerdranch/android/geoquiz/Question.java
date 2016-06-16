package com.bignerdranch.android.geoquiz;

/**
 * Created by SamerGigaByte on 08/06/2016.
 */
public class Question {
    private int mTextResId;
    private boolean mAnswerTrue , mIsCheater;

    public Question(int textResId, boolean answerTrue, boolean cheater) {
        mTextResId = textResId;

        mAnswerTrue = answerTrue;
        mIsCheater=cheater;
    }

    public int getTextResId() {
        return mTextResId;
    }
    public boolean getIsCheater(){ return mIsCheater;}

    public void setIsCheater(boolean ch){
        mIsCheater=ch;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;

    }

    public void setAnswerTrue(boolean answerTrue) {

        mAnswerTrue = answerTrue;
    }
}