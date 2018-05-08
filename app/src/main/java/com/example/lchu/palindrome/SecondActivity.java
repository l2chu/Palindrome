package com.example.lchu.palindrome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView mMessageViewTextBoolean;
    private TextView mMessageViewTextVowel;
    public static final String REPLY = "reply";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mMessageViewTextBoolean = findViewById(R.id.boolean_result);
        mMessageViewTextVowel = findViewById(R.id.vowel_result);

        //creating a new intent to get the intent that activated this activity (SecondActivity)
        Intent intent = getIntent();

        String word = intent.getStringExtra(MainActivity.WORD_REQUEST);
        boolean bool = intent.getBooleanExtra(MainActivity.BOOL_REQUEST,false);
        String vowel = intent.getStringExtra(MainActivity.VOWEL_REQUEST);


        if (calcPalindrome(word) != bool) {
            if (calcPalindrome(word)) {
                mMessageViewTextBoolean.setText("I am sorry, the word you entered was a palindrome");
            } else {
                mMessageViewTextBoolean.setText("I am sorry, the word you entered was not a palindrome");
            }
        } else {
            if (calcPalindrome(word)) {
                mMessageViewTextBoolean.setText("Congratulations! The word you entered was a palindrome");
            } else {
                mMessageViewTextBoolean.setText("Congratulations! The word you entered was not a palindrome");
            }
        }


        char isVowel = word.charAt(0);

        if (isVowel(isVowel) && vowel.equals("yes")
                || (!isVowel(isVowel) && vowel.equals("no"))) {
            if (isVowel(isVowel)) {
                mMessageViewTextVowel.setText("Congratulations! The first letter of your word is a vowel.");
            } else {
                mMessageViewTextVowel.setText("Congratulations! The first letter of your word is not a vowel.");
            }
        } else {
            if (isVowel(isVowel)) {
                mMessageViewTextVowel.setText("I am sorry, the first letter of your word is a vowel.");
            } else {
                mMessageViewTextVowel.setText("I am sorry, the first letter of your word is not a vowel.");
            }
        }
    }

    public static boolean calcPalindrome(String input) {
        int stringLength = input.length();

        // go through half the String input length
        for (int i = 0; i < (stringLength / 2); ++i) {
            // as soon as one character is not the same, just return false
            if (input.charAt(i) != input.charAt(stringLength - i - 1)) {
                return false;
            }
        }

        // if the for loop completed and did not return false, return true
        return true;
    }

    public static boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }

    public void returnReplyYes(View view) {

        //assign the reply message in a string
        Intent replyIntent = new Intent();

        //send that string to the other activity as an intent??

        //add intent extra for the reply data by creating a key and data pair
        replyIntent.putExtra(REPLY, "yes");
        //send the intent
        setResult(RESULT_OK, replyIntent);

        //closes the activity and return to the main activity
        finish();
    }

    public void returnReplyNo(View view) {
        Intent replyIntent = new Intent();

        //send that string to the other activity as an intent??

        //add intent extra for the reply data by creating a key and data pair
        replyIntent.putExtra(REPLY, "no");
        //send the intent
        setResult(RESULT_OK, replyIntent);

        //closes the activity and return to the main activity
        finish();
    }
}
