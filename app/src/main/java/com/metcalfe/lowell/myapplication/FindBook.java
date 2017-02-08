package com.metcalfe.lowell.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class FindBook extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_book);
        //This sets up the image view which is in the middle of the page. It is being named myImageView here.
        ImageView myImageView = (ImageView) findViewById(R.id.imageView2);
        //This creates an object of DisplayBooks and calls it DisplayB.
        DisplayBooks DisplayB = new DisplayBooks();
        //this sets a String called position as the value of position which is found in DisplayBooks. It uses the object DisplayB and the getter method inside DisplayBooks to pass it across classes.
        String position = DisplayB.getPosition();

        //this turns the String position to an integer because it is a string in DisplayBooks but needs to be an int for the following if statements.
        int Pos = Integer.parseInt(position);

        //this first if statement checks if the position (Pos) is lower than 25 and therefore is inside
        if (Pos <= 25) {
            //this line sets the image in the middle of the page as the image in the drawable file named Libraryfloorplan1. This image is an image of the floor plan with the top left shelf highlighted red.
            myImageView.setBackgroundResource(R.drawable.libraryfloorplan1);
            //the rest of the ifs go on to check for the section that the position is part of and then sets the appropriate image.
        } else if (Pos > 25 && Pos <= 50) {
            myImageView.setBackgroundResource(R.drawable.libraryfloorplan2);
        } else if (Pos > 50 && Pos <= 75) {
            myImageView.setBackgroundResource(R.drawable.libraryfloorplan4);
        } else if (Pos > 75 && Pos <= 100) {
            myImageView.setBackgroundResource(R.drawable.libraryfloorplan5);
        } else if (Pos > 100 && Pos <= 125) {
            myImageView.setBackgroundResource(R.drawable.libraryfloorplan6);
        } else if (Pos > 125 && Pos <= 150) {
            myImageView.setBackgroundResource(R.drawable.libraryfloorplan7);
        } else if (Pos > 150 && Pos <= 175) {
            myImageView.setBackgroundResource(R.drawable.libraryfloorplan8);
        } else if (Pos > 175 && Pos <= 200) {
            myImageView.setBackgroundResource(R.drawable.libraryfloorplan9);
        }
        //bottom 4
        else if (Pos > 200 && Pos <= 225) {
            myImageView.setBackgroundResource(R.drawable.libraryfloorplan10);
        } else if (Pos > 225 && Pos <= 250) {
            myImageView.setBackgroundResource(R.drawable.libraryfloorplan11);
        } else if (Pos > 250 && Pos <= 275) {
            myImageView.setBackgroundResource(R.drawable.libraryfloorplan12);
        } else if (Pos > 275 && Pos <= 300) {
            myImageView.setBackgroundResource(R.drawable.libraryfloorplan13);
        } else if (Pos > 300 && Pos <= 325) {
            myImageView.setBackgroundResource(R.drawable.libraryfloorplan14);
        } else if (Pos > 325 && Pos <= 350) {
            myImageView.setBackgroundResource(R.drawable.libraryfloorplan15);
        } else if (Pos > 350 && Pos <= 375) {
            myImageView.setBackgroundResource(R.drawable.libraryfloorplan15);
        } else if (Pos > 375 && Pos <= 400) {
            myImageView.setBackgroundResource(R.drawable.libraryfloorplan16);
        }
        //end of block of if else statement
    }

}
