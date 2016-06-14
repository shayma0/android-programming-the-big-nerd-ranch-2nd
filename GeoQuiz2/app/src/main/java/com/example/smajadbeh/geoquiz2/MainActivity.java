package com.example.smajadbeh.geoquiz2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button trueButton, falseButton;
    ImageButton next, previous;
    TextView tv;

    int arrIndex=0;

    Question[] arr=new Question[]{
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton=(Button)findViewById(R.id.trueButtonID);
        falseButton=(Button)findViewById(R.id.falseButtonID);
        next=(ImageButton)findViewById(R.id.nextImageButtonID);
        previous=(ImageButton)findViewById(R.id.previousImageButtonID);
        tv=(TextView)findViewById(R.id.textView1);

        tv.setText(arr[arrIndex].getQ());

        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        next.setOnClickListener(this);
        previous.setOnClickListener(this);
        tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.trueButtonID:

                if(arr[arrIndex].getAnswer())
                    Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                else
                Toast.makeText(getApplicationContext(),"Incorrect!", Toast.LENGTH_SHORT).show();

                break;
            case R.id.falseButtonID:
                if(arr[arrIndex].getAnswer()==false)
                    Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"Incorrect!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nextImageButtonID:

               // arrIndex=(arrIndex+1)%arr.length;
                //tv.setText(arr[arrIndex].getQ());

                arrIndex++;
                if(arrIndex<arr.length)
                    tv.setText(arr[arrIndex].getQ());
                if(arrIndex == arr.length)
                    arrIndex--;

                break;
            case R.id.previousImageButtonID:
                arrIndex--;
                if(arrIndex>=0)
                    tv.setText(arr[arrIndex].getQ());

                if(arrIndex <0)
                    arrIndex++;

                break;

            case R.id.textView1:
                arrIndex++;
                if(arrIndex<arr.length)
                    tv.setText(arr[arrIndex].getQ());
                if(arrIndex == arr.length)
                    arrIndex--;
                break;
        }
    }
}
