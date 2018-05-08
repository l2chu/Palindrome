package com.example.lchu.palindrome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mMessageEditTextWord;
    private EditText mMessageEditTextBoolean;
    private EditText mMessageEditTextVowel;
    public static final int TEXT_REQUEST = 1;
    public static final int TEXT_REQUEST_2 = 2;
    public static final String WORD_REQUEST = "word";
    public static final String BOOL_REQUEST = "boolean";
    public static final String VOWEL_REQUEST = "vowel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMessageEditTextWord = findViewById(R.id.edit_text_word);
        mMessageEditTextBoolean = findViewById(R.id.edit_text_boolean);
        mMessageEditTextVowel = findViewById(R.id.edit_text_vowel);
    }

    public void launchSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        //get the string that is being sent
        String word = mMessageEditTextWord.getText().toString();
        String bool = mMessageEditTextBoolean.getText().toString();
        String vowel = mMessageEditTextVowel.getText().toString();

        //add that string to an extra using a key
        intent.putExtra(WORD_REQUEST, word);
        intent.putExtra(BOOL_REQUEST, bool);
        intent.putExtra(VOWEL_REQUEST, vowel);
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

                if(reply.equals("no")) {
                    Intent intent = new Intent(this, EndActivity.class);
                    //get the string that is being sent

                    //start the activity and wait for a result
                    startActivityForResult(intent, TEXT_REQUEST_2);
                }else{
                    mMessageEditTextVowel.setText("");
                    mMessageEditTextBoolean.setText("");
                    mMessageEditTextWord.setText("");
                }
            }
        }
    }
}
