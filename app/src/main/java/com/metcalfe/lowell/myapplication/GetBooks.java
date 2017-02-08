package com.metcalfe.lowell.myapplication;

import java.util.Random;

public class GetBooks {
    public static String[][] books = new String[20][6];
    Random rand = new Random();
    public  void GetBooks() {
        //sets up a 2d array that can hold all the book data
        books[0][0] = "0";
        books[0][1] = "Title";
        books[0][2] = "Author";
        books[0][3] = "Genre";
        books[0][4] = "1";
        books[0][5] = "IMG";
        books[0][6] = "16";
        for (int i=1; i <20; i++){
            books[i][0] = Integer.toString(i);
            books[i][1] = "title" + i;
            books[i][2] = "Author" + i;
            books[i][3] = "Genre" + i;
            books[i][4] = Integer.toString(rand.nextInt(5)+1);
            books[i][5] = "img";
            books[i][6] = Integer.toString(Integer.parseInt(books[i-1][6])+25);
        }
        books [1][3] = "Genre6";
        books[1][5] = "hungercover";
        books [2][3] = "Genre6";
        books[2][5] = "gonecover";
        books [5][3] = "Genre6";
        books[5][5] = "lightcover";
        //GetFromXML(books);
        BubbleSort(books);
        //for loop bellow checks if the sorting worked
        //for(int tester = 0; tester < 20; tester++){
        // 	System.out.println(books[tester][0]);
        //}
    }

    public String[][] BubbleSort(String[][] books) {
        //try switching parts too ints by making a seperate variable
        int n = books.length;
        String temp = "0";
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (Integer.parseInt(books[j - 1][0]) > Integer.parseInt(books[j][0])) {
                    //swap the elements!
                    temp = books[j - 1][0];
                    books[j - 1][0] = books[j][0];
                    books[j][0] = temp;
                }
            }
        }
        return books;
    }

    public String[][] sendBooks(){
        return books;
    }


}