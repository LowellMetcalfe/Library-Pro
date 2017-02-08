package com.metcalfe.lowell.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class SuggestAndReserve extends AppCompatActivity {
    Spinner yearSpinner;
    EditText FullNameField, TitleField, AuthorField, NotesField;
    Button buttonSend;
    String radioChosen;
    RadioGroup radioGroup;
    int selectedId;
    int filledBoxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the display as the layout xml file suggest_reserve
        setContentView(R.layout.suggest_reserve);

        radioGroup = (RadioGroup) findViewById(R.id.SuggestOrReserve);
        FullNameField = (EditText) findViewById(R.id.EditTextFullName);
        yearSpinner = (Spinner) findViewById(R.id.SpinnerYear);
        TitleField = (EditText) findViewById(R.id.EditTextTitle);
        AuthorField = (EditText) findViewById(R.id.EditTextAuthor);
        NotesField = (EditText) findViewById(R.id.EditTextNotes);
        buttonSend = (Button) findViewById(R.id.ButtonSendEnquiry);
        buttonSend.setClickable(false);

        //this adds a listener that will do code at certain times, ive used it to run code after the text has been changed in the FullNameField field
        FullNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(FullNameField.length()>0 && FullNameField.length()<30){

                    filledBoxes = filledBoxes +1;
                }
            }
        });
        //this adds a listener that will do code at certain times, ive used it to run code after the text has been changed in the TitleField field
        TitleField.addTextChangedListener(new TextWatcher() {
                                              @Override
                                              public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                              }

                                              @Override
                                              public void onTextChanged(CharSequence s, int start, int before, int count) {
                                              }

                                              @Override
                                              public void afterTextChanged(Editable s) {
                                                  //this is an attempt to validate the user's input. If what they enter is longer than 0 and less that 50
                                                  if(FullNameField.length()>0 && FullNameField.length()<50){
                                                      //then set a variable as 1 more.
                                                      filledBoxes = filledBoxes +1;
                                                  }
                                              }
                                          }

        );
        //this adds a listener that will do code at certain times, ive used it to run code after the text has been changed in the AuthorField field
        AuthorField.addTextChangedListener(new TextWatcher() {
                                               @Override
                                               public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                               }

                                               @Override
                                               public void onTextChanged(CharSequence s, int start, int before, int count) {
                                               }

                                               @Override
                                               public void afterTextChanged(Editable s) {
                                                   if (AuthorField.length() > 0 && AuthorField.length() < 30) {
                                                       //then set a variable as 1 more.
                                                       filledBoxes = filledBoxes +1;
                                                   }
                                               }
                                           }
        );
        //this adds a listener that will do code at certain times, ive used it to run code after the text has been changed in the NotesField field
        NotesField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (NotesField.length() > 0 && NotesField.length() < 50) {
                    //then set a variable as 1 more.
                    filledBoxes = filledBoxes + 1;
                }
            }
        });
        //this if uses the filledboxes and tries to see if all boxes have something in them.
        //if they all meet the validation checks then the variable inside the afterTextChanged then the variable filledBoxes should total 4
        //AND if one of the two radio buttons is selected
        if (filledBoxes == 4 && radioGroup.getCheckedRadioButtonId() != -1) {
            //then allow the button at the bottom to be pressed
            buttonSend.setClickable(true);
        }
        else {
            //if not all the boxes have been filled or all the validation checks havent passed and the varaible doesnt equal 4. Then display a pop up message
            Toast.makeText(SuggestAndReserve.this, "Please fill all required fields.", Toast.LENGTH_LONG).show();
        }//end if else statement


        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                radioChosen = radioButton.getText().toString();
                //makes a pop up message
                Toast.makeText(SuggestAndReserve.this, "Choose your email App of choice then hit SEND.", Toast.LENGTH_LONG).show();

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                //configures the way of sending the email, this bellow will make the device show all the programs on the users device that are compatible with this format, like gmail for example.
                emailIntent.setType("message/rfc822");
                //sets the recepient of the email
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"lowellmetcalfe@googlemail.com"});
                //sets the subject of the email using the user's entered data
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, radioChosen + " of book " + TitleField.getText().toString() + " by " + AuthorField.getText().toString());
                //sets the text of the email using the user's entered data
                emailIntent.putExtra(Intent.EXTRA_TEXT, FullNameField.getText().toString() + " from " + yearSpinner.getSelectedItem().toString() + ", " + radioChosen + "s the book " + TitleField.getText().toString() + " by " + AuthorField.getText().toString() + ". Additional notes: " + NotesField.getText().toString());
                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                    //Intent intent = new Intent(SuggestAndReserve.this, MainActivity.class);
                    //startActivity(intent);
                } catch (android.content.ActivityNotFoundException ex) {
                    //makes a pop up message to tell them they havent got an email client installed
                    Toast.makeText(SuggestAndReserve.this,
                            "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

