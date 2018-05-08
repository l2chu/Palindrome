package com.example.lchu.palindrome;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private EditText mMessageEditTextWord;
    private RadioGroup radioGroupBool;
    private RadioGroup radioGroupVowel;
    private RadioButton trueButton;
    private RadioButton falseButton;
    private RadioButton yesButton;
    private RadioButton noButton;
    public static final int TEXT_REQUEST = 1;
    public static final int TEXT_REQUEST_2 = 2;
    public static final String WORD_REQUEST = "word";
    public static final String BOOL_REQUEST = "boolean";
    public static final String VOWEL_REQUEST = "vowel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trueButton = findViewById(R.id.radio_true);
        falseButton = findViewById(R.id.radio_false);
        yesButton = findViewById(R.id.radio_true);
        noButton = findViewById(R.id.radio_no);
        radioGroupBool = findViewById(R.id.myBoolRadioGroup);
        radioGroupVowel = findViewById(R.id.myVowelRadioGroup);
        mMessageEditTextWord = findViewById(R.id.edit_text_word);
    }

    public void launchSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        //get the string that is being sent
        String word = mMessageEditTextWord.getText().toString();
        int selectedIdBool = radioGroupBool.getCheckedRadioButtonId();
        int selectedIdVowel = radioGroupVowel.getCheckedRadioButtonId();


        //add that string to an extra using a key
        intent.putExtra(WORD_REQUEST, word);
        if(selectedIdBool == trueButton.getId()){
            intent.putExtra(BOOL_REQUEST, true);
        }else{
            intent.putExtra(BOOL_REQUEST, false);
        }
        if(selectedIdVowel == yesButton.getId()){
            intent.putExtra(VOWEL_REQUEST, "yes");
        }else{
            intent.putExtra(VOWEL_REQUEST, "no");
        }

        //start the activity and wait for a result
        startActivityForResult(intent, TEXT_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //checking which activity result it was
        if (requestCode == TEXT_REQUEST) {
            //checking if the setResult result was ok
            if (resultCode == RESULT_OK) {
                //get the reply from the key
                String reply = data.getStringExtra(SecondActivity.REPLY);

                if (reply.equals("no")) {
                    Intent intent = new Intent(this, EndActivity.class);
                    //get the string that is being sent

                    //start the activity and wait for a result
                    startActivityForResult(intent, TEXT_REQUEST_2);
                } else {
                    mMessageEditTextWord.setText("");
                    RadioGroup radioVowelGroup = findViewById(R.id.myVowelRadioGroup);
                    RadioGroup radioGroup = findViewById(R.id.myBoolRadioGroup);
                    radioGroup.clearCheck();
                    radioVowelGroup.clearCheck();
                }
            }
        }
    }
}
