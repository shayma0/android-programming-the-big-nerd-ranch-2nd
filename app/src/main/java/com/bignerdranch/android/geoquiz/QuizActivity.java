package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//ch4 shows you how to use android LogCat and the debugging related tools no code here
public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;


    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton, previousButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[]{

            new Question(R.string.question_oceans, true, false),
            new Question(R.string.question_mideast, false,false),
            new Question(R.string.question_africa, false,false),

            new Question(R.string.question_americas, true, false),
            new Question(R.string.question_asia, true, false),
    };


    private int mCurrentIndex = 0;
    private boolean mIsCheater;

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();

        mQuestionTextView.setText(question);
    }


    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();


        int messageResId = 0;

        if (mQuestionBank[mCurrentIndex].getIsCheater()) {
            messageResId = R.string.judgment_toast;
        } else {

            if (userPressedTrue == answerIsTrue) {

                messageResId = R.string.correct_toast;
            } else {

                messageResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                checkAnswer(false);
            }

        });

        mNextButton = (Button) findViewById(R.id.next_button);
        previousButton = (Button) findViewById(R.id.previous_button);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                //mIsCheater = false;
                updateQuestion();
            }

        });


        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent i = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivityForResult(i, REQUEST_CODE_CHEAT);
            }

        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentIndex>0)
                    mCurrentIndex--;
                // mCurrentIndex = (mCurrentIndex -1) % mQuestionBank.length;

                //mIsCheater = false;

                updateQuestion();

            }
        });


        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        updateQuestion();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;

        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;

            }
            mQuestionBank[mCurrentIndex].setIsCheater(CheatActivity.wasAnswerShown(data));
            //mIsCheater= mQuestionBank[mCurrentIndex].getIsCheater();
        }



    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");

        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        savedInstanceState.putBoolean("mIsCheater", mIsCheater);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);


       mIsCheater=savedInstanceState.getBoolean("mIsCheater");
    }



    @Override

    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");

    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {

        super.onResume();
        Log.d(TAG, "onResume() called");

    }

    @Override

    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}
