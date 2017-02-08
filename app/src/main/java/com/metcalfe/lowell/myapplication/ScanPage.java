package com.metcalfe.lowell.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ScanPage extends Activity {
    //makes ISBN global variable
    public static String ISBN = null;
    //sets the strintg ACTION_SCAN as com.google.zxing.client.android.SCAN
    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the display as the layout xml file scanpage
        setContentView(R.layout.scanpage);
    }

    //barcode mode method
    public void scanBar(View v) {
        //try to catch any errors launching scanning
        try {
            //start the scanning activity from the com.google.zxing.client.android.SCAN intent
            Intent intent = new Intent(ACTION_SCAN);
            //adds to the intent that its a scan mode and is a bar code
            intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
            //starts the ACTION SCAN activity and is ready for a result
            startActivityForResult(intent, 0);
            //the catch of any errors
        } catch (ActivityNotFoundException anfe) {
            //on catch, show the download dialog
            showDialog(ScanPage.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }

    //qr code mode method
    public void scanQR(View v) {
        //try to catch any errors launching scanning
        try {
            //start the scanning activity from the com.google.zxing.client.android.SCAN intent
            Intent intent = new Intent(ACTION_SCAN);
            //adds to the intent that its a scan mode and is a QR code
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            //starts the ACTION SCAN activity and is ready for a result
            startActivityForResult(intent, 0);
            //the catch of any errors
        } catch (ActivityNotFoundException anfe) {
            //on catch, show the download dialog
            showDialog(ScanPage.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }

    //this method is what happens when one of the above try catches, catches an error. It displays the dialog to guide the user to downloading a new scanning program
    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        //this sets the title of the displayed dialog
        downloadDialog.setTitle(title);
        //this sets the message of the displayed dialog
        downloadDialog.setMessage(message);
        //set the onclick listener for when they click the yes button
        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            //this is everything to do once they click yes
            public void onClick(DialogInterface dialogInterface, int i) {
                //this is what to search the app store for
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                //try to start the intent to search the app store
                try {
                    act.startActivity(intent);
                } catch (ActivityNotFoundException anfe) {

                }
            }
        });
        //set the onclic listener for when the click no
        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            //do nothing when they click no
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        //return the actual display the dialog
        return downloadDialog.show();
    }

    //on ActivityResult method
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //if the requestcode is 0
        if (requestCode == 0) {
            //if the result is valid
            if (resultCode == RESULT_OK) {
                //get the extras that are returned from the intent
                ISBN = intent.getStringExtra("SCAN_RESULT");
                //quick validation check, if the value from the scan is less than or equal to 13 bits and if its all numbers(thats in BNF)
                if (ISBN.length() <= 13 && ISBN.matches("\\d+")) {
                    //if it passes the validation checks then it will start the DisplayBooks acitivty
                    Intent intentl = new Intent(ScanPage.this, DisplayBooks.class);
                    startActivity(intentl);
                    // else is for when the validation checks are failed
                } else {
                    //makes a pop up message
                    Toast toast = Toast.makeText(this, "This QR is incorrect. It is either not a number or not an ISBN", Toast.LENGTH_LONG);
                    toast.show();
                }//end else
            }//end if
        }//end if
    }

    //getter for the ISBN
    public static String getISBN() {
        return ISBN;
    }
    //setter for the ISBN
    public static void setISBN(String ISBN) {
        ScanPage.ISBN = ISBN;
    }
}