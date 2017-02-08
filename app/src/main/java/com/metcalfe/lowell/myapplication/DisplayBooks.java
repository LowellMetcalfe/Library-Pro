package com.metcalfe.lowell.myapplication;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DisplayBooks extends AppCompatActivity {
    //these two lines initialise variables for later use
    int Middle = 0;
    long ISBN = 0;
    //this creats the map and makes it accessible
    public static Map<String, Integer> map = new HashMap<String, Integer>();
    //this creates the suggested array
    String[][] suggested = new String[20][3];
    //this sets the data types for the following variables and makes them accessible by all methods in the class
    public static String position;
    private static TextView textViewTitle;
    private static TextView textViewAuthor;
    private static TextView textViewGenre;
    private static RatingBar ratingBar;
    private static ImageView coverImg;
    private static TextView textViewDesc;
    private static ImageButton suggested1;
    private static ImageButton suggested2;
    private static ImageButton suggested3;
    private static ImageButton suggested4;
    private static Button findButton;
    private static final String BOOKSINFO = "{\n" +
            " \"kind\": \"books#volumes\",\n" +
            " \"totalItems\": 1085,\n" +
            " \"items\": [\n" +
            "  {\n" +
            "   \"kind\": \"books#volume\",\n" +
            "   \"id\": \"09wCs_QXKXkC\",\n" +
            "   \"etag\": \"b/NWb4h/gZY\",\n" +
            "   \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/09wCs_QXKXkC\",\n" +
            "   \"volumeInfo\": {\n" +
            "    \"title\": \"Of Mice and Men\",\n" +
            "    \"authors\": [\n" +
            "     \"John Steinbeck\"\n" +
            "    ],\n" +
            "    \"publisher\": \"Penguin UK\",\n" +
            "    \"publishedDate\": \"2000-09-07\",\n" +
            "    \"description\": \"Streetwise George and his big, childlike friend Lennie are drifters, searching for work in the fields and valleys of California. They have nothing except the clothes on their back, and a hope that one day they'll find a place of their own and live the American dream. But dreams come at a price. Gentle giant Lennie doesn't know his own strength, and when they find work at a ranch he gets into trouble with the boss's daughter-in-law. Trouble so bad that even his protector George may not be able to save him... Contains an introduction by David Wyatt, as well as suggestions for further reading of acclaimed criticisms and references.\",\n" +
            "    \"industryIdentifiers\": [\n" +
            "     {\n" +
            "      \"type\": \"ISBN_13\",\n" +
            "      \"identifier\": \"9780141923475\"\n" +
            "     },\n" +
            "     {\n" +
            "      \"type\": \"ISBN_10\",\n" +
            "      \"identifier\": \"0141923474\"\n" +
            "     }\n" +
            "    ],\n" +
            "    \"readingModes\": {\n" +
            "     \"text\": true,\n" +
            "     \"image\": false\n" +
            "    },\n" +
            "    \"pageCount\": 144,\n" +
            "    \"printType\": \"BOOK\",\n" +
            "    \"categories\": [\n" +
            "     \"Fiction\"\n" +
            "    ],\n" +
            "    \"averageRating\": 4,\n" +
            "    \"ratingsCount\": 3082,\n" +
            "    \"maturityRating\": \"NOT_MATURE\",\n" +
            "    \"allowAnonLogging\": true,\n" +
            "    \"contentVersion\": \"2.7.3.0.preview.2\",\n" +
            "    \"imageLinks\": {\n" +
            "     \"smallThumbnail\": \"http://books.google.co.uk/books/content?id=09wCs_QXKXkC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
            "     \"thumbnail\": \"http://books.google.co.uk/books/content?id=09wCs_QXKXkC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
            "    },\n" +
            "    \"language\": \"en\",\n" +
            "    \"previewLink\": \"http://books.google.co.uk/books?id=09wCs_QXKXkC&printsec=frontcover&dq=mice&hl=&cd=1&source=gbs_api\",\n" +
            "    \"infoLink\": \"http://books.google.co.uk/books?id=09wCs_QXKXkC&dq=mice&hl=&source=gbs_api\",\n" +
            "    \"canonicalVolumeLink\": \"http://books.google.co.uk/books/about/Of_Mice_and_Men.html?hl=&id=09wCs_QXKXkC\"\n" +
            "   }\n" +
            "  }\n" +
            " ]\n" +
            "}";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the display as the layout xml file displaybook
        setContentView(R.layout.displaybook);
        //this is a text area for the title to be displayed in
        textViewTitle = (TextView) findViewById(R.id.Title);
        //this is a text area for the author to be displayed in
        textViewAuthor = (TextView) findViewById(R.id.Author);
        //this is a text area for the genre to be displayed in
        textViewGenre = (TextView) findViewById(R.id.Genre);
        //this is a set of rating stars that can change how many stars are filled
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        //this is a image box for the cover image of a book
        coverImg = (ImageView) findViewById(R.id.imageView);
        //this is a text area for the description to be displayed in
        textViewDesc = (TextView) findViewById(R.id.textViewDescription);
        //this is a image box for the cover image of the first suggested book
        suggested1 = (ImageButton) findViewById(R.id.suggestedbutton1);
        //this is a image box for the cover image of the secound suggested book
        suggested2 = (ImageButton) findViewById(R.id.suggestedbutton2);
        //this is a image box for the cover image of the third suggested book
        suggested3 = (ImageButton) findViewById(R.id.suggestedbutton3);
        //this is a image box for the cover image of the fourth suggested book
        suggested4 = (ImageButton) findViewById(R.id.suggestedbutton4);
        //this is a pressable button that says FIND on it.
        findButton = (Button) findViewById(R.id.button);
        //this is creating the object scanpage
        ScanPage scanpage = new ScanPage();
        //for the sake of a testing array ive used a randomised rating for the book. When the code is fully implemented the rating would be set from the records.
        Random rand = new Random();
        //this part here builds the array. its been initalised and filled all in one using a matrix.
        /*
        final String[][] books = new String[][]{
                //the parts of the array in order are: the ISBN (In this cause only a 1 bit integer), Title, Author, Genre, Rating, Img location, books position, description of the book.
                {"1", "Earagon", "Christopher Paolini", "Fantasy", Integer.toString(rand.nextInt(5) + 1), "eragoncover", "15", "One boy. One dragon. A world of adventure. When Eragon finds a polished blue stone in the forest, he thinks it is the lucky discovery of a poor farm boy; perhaps it will buy his family meat for the winter. But when the stone brings a dragon hatchling, Eragon realizes he has stumbled upon a legacy nearly as old as the Empire itself."},
                {"2", "Heartstone ", "C. J. Sansom", "Crime", Integer.toString(rand.nextInt(5) + 1), "hearstonecover", "40", "Summer, 1545. England is at war. Henry VIII's invasion of France has gone badly wrong, and a massive French fleet is preparing to sail across the Channel. As the English fleet gathers at Portsmouth, the country raises the largest militia army it has ever seen. The King has debased the currency to pay for the war, and England is in the grip of soaring inflation and economic crisis."},
                {"3", "The Lord of The Rings", "J R Tolkien", "Fantasy", Integer.toString(rand.nextInt(5) + 1), "tlotrcover", "75", "In a sleepy village in the Shire, young Frodo Baggins finds himself faced with an immense task, as the Ring is entrusted to his care. He must leave his home and make a perilous journey across the realms of Middle-earth to the Crack of Doom, deep inside the territories of the Dark Lord. There he must destroy the Ring forever and foil the Dark Lord in his evil purpose."},
                {"4", "Fault in Our Stars", "John Green", "Drama", Integer.toString(rand.nextInt(5) + 1), "fioscover", "90", "Despite the tumor-shrinking medical miracle that has bought her a few years, Hazel has never been anything but terminal, her final chapter inscribed upon diagnosis. But when a gorgeous plot twist named Augustus Waters suddenly appears at Cancer Kid Support Group, Hazel's story is about to be completely rewritten."},
                {"5", "The Night Stalker", "Robert Bryndza ", "Crime", Integer.toString(rand.nextInt(5) + 1), "tnscover", "115", "In the dead of a swelteringly hot summer’s night, Detective Erika Foster is called to a murder scene. The victim, a doctor, is found suffocated in bed. His wrists are bound and his eyes bulging through a clear plastic bag tied tight over his head. "},
                {"6", "The Host", "Stephenie Meyer", "Drama", Integer.toString(rand.nextInt(5) + 1), "hostcover", "140", "Melanie Stryder refuses to fade away. The earth has been invaded by a species that takes over the minds of their human hosts while leaving their bodies intact, and most of humanity has succumbed. Wanderer, the invading 'soul' who has been given Melanie's body, knew about the challenges of living inside a human: the overwhelming emotions, the too-vivid memories. But there was one difficulty Wanderer didn't expect: the former tenant of her body refusing to relinquish possession of her mind. Melanie fills Wanderer's thoughts with visions of the man Melanie loves - Jared, a human who still lives in hiding. Unable to separate herself from her body's desires, Wanderer yearns for a man she's never met."},
                {"7", "Ash", "Mary Gentle", "Fantasy", Integer.toString(rand.nextInt(5) + 1), "ashcover", "165", "For the beautiful young woman Ash, life has always been arquebuses and artillery, swords and armour and the true horrors of hand-to-hand combat. War is her job. She has fought her way to the command of a mercenary company, and on her unlikely shoulders lies the destiny of a Europe threatened by the depredations of an Infidel army more terrible than any nightmare."},
                {"8", "Hunger", "Michael Grant", "SciFi", Integer.toString(rand.nextInt(5) + 1), "hungercover", "190", "Food ran out weeks ago. Everyone is starving, but no one wants to figure out a solution. And each day, more and more kids are evolving, developing supernatural abilities that set them apart from the kids without powers. Tension rises and chaos is descending upon the town. It's the normal kids against the mutants. Each kid is out for himself, and even the good ones turn murderous."},
                {"9", "Twilight New Moon", "Stephine Meyer", "Drama", Integer.toString(rand.nextInt(5) + 1), "twilightnmcover", "215", "I knew we were both in mortal danger. Still, in that instant, I felt well. Whole. I could feel my heart racing in my chest, the blood pulsing hot and fast through my veins again. My lungs filled deep with the sweet scent that came off his skin. It was like there had never been any hole in my chest. I was perfect - not healed, but as if there had never been a wound in the first place."},
                {"10", "Gone", "Michael Grant", "SciFi", Integer.toString(rand.nextInt(5) + 1), "gonecover", "240", "Suddenly it’s a world without adults and normal has crashed and burned. When life as you know it ends at 15, everything changes. There are no adults, no answers. What would you do?"},
                {"11", "Light", "Michael Grant", "SciFi", Integer.toString(rand.nextInt(5) + 1), "lightcover", "265", "In the time since every person over the age of fourteen disappeared from the town of Perdido Beach, California, countless battles have been fought: battles against hunger and lies and plague, and epic battles of good against evil. Light, Michael Grant's sixth and final book in the New York Times bestselling Gone series creates a masterful, arresting conclusion to life in the FAYZ."},
                {"12", "Twilight Eclipse", "Stephine Meyer", "Drama", Integer.toString(rand.nextInt(5) + 1), "twlightecover", "290", "As Seattle is ravaged by a string of mysterious killings and a malicious vampire continues her quest for revenge, Bella once again finds herself surrounded by danger. In the midst of it all, she is forced to choose between her love for Edward and her friendship with Jacob - knowing that her decision has the potential to ignite the ageless struggle between vampire and werewolf. With her graduation quickly approaching, Bella has one more decision to make: life or death. But which is which?"},
                {"13", "Exposure", "Kathy Rieches", "SciFi", Integer.toString(rand.nextInt(5) + 1), "exposurecover", "315", "When twin classmates are abducted from Bolton Prep, Tory and the Virals decide there’s no one better equipped than them to investigate. But the gang has other problems to face. Their powers are growing wilder, and becoming harder to control. Chance Claybourne is investigating the disastrous medical experiment that twisted their DNA. The bonds that unite them are weakening, threatening the future of the pack itself."},
                {"14", "Hobbit", "J R Tolkin", "Fantasy", Integer.toString(rand.nextInt(5) + 1), "hobbitcover", "340", "Written for J.R.R. Tolkien’s own children, The Hobbit met with instant critical acclaim when it was first published in 1937. Now recognized as a timeless classic, this introduction to the hobbit Bilbo Baggins, the wizard Gandalf, Gollum, and the spectacular world of Middle-earth recounts of the adventures of a reluctant hero, a powerful and dangerous ring, and the cruel dragon Smaug the Magnificent."},
                {"15", "Posion Artist", "Jonathan Moore", "Crime", Integer.toString(rand.nextInt(5) + 1), "posionartcover", "365", "Dr Caleb Maddox is an expert on pain. A leading San Francisco toxicologist, he is mapping the chemical traces that show how much agony a human body can endure. But now a different kind of pain is distracting him from his life's work - the violent break-up of his relationship with his artist girlfriend, Bridget. Seeking solace in a secluded bar, he meets a beautiful woman who shares an absinthe with him, then disappears into the night"},
                {"16", "Paper Towns", "John Green", "Drama", Integer.toString(rand.nextInt(5) + 1), "papertowncover", "390", "Quentin Jacobsen has spent a lifetime loving the magnificently adventurous Margo Roth Spiegelman from afar. So when she cracks open a window and climbs into his life—dressed like a ninja and summoning him for an ingenious campaign of revenge—he follows. After their all-nighter ends, and a new day breaks, Q arrives at school to discover that Margo, always an enigma, has now become a mystery."},
                {"17", "Light", "Michael Grant", "SciFi", Integer.toString(rand.nextInt(5) + 1), "lightcover", "390", "In the time since every person over the age of fourteen disappeared from the town of Perdido Beach, California, countless battles have been fought: battles against hunger and lies and plague, and epic battles of good against evil. Light, Michael Grant's sixth and final book in the New York Times bestselling Gone series creates a masterful, arresting conclusion to life in the FAYZ."},
                {"18", "Calico Joe", "John Grisham", "Crime", Integer.toString(rand.nextInt(5) + 1), "calicocover", "390", "In the summer of 1973 Joe Castle was the boy wonder of baseball, the greatest rookie anyone had ever seen.  The kid from Calico Rock, Arkansas dazzled Cub fans as he hit home run after home run, politely tipping his hat to the crowd as he shattered all rookie records."},
                {"19", "Game Of Thrones", "George R. R. Martin", "Fantasy", Integer.toString(rand.nextInt(5) + 1), "gotcover", "390", "Long ago, in a time forgotten, a preternatural event threw the seasons out of balance. In a land where summers can last decades and winters a lifetime, trouble is brewing. The cold is returning, and in the frozen wastes to the north of Winterfell, sinister forces are massing beyond the kingdom’s protective Wall. To the south, the king’s powers are failing—his most trusted adviser dead under mysterious circumstances and his enemies emerging from the shadows of the throne. At the center of the conflict lie the Starks of Winterfell, a family as harsh and unyielding as the frozen land they were born to. Now Lord Eddard Stark is reluctantly summoned to serve as the king’s new Hand, an appointment that threatens to sunder not only his family but the kingdom itself."},
                {"20", "Partners", "John Grisham", "Crime", Integer.toString(rand.nextInt(5) + 1), "partnerscover", "390", "Sebastian Rudd, rogue lawyer, defends people other lawyers won’t go near. It’s controversial and dangerous work, which is why Sebastian needs his bodyguard/assistant/sidekick: Partner. So if Sebastian is just about the most unpopular lawyer in town, why is Partner so loyal to him? How did they meet? And what’s the real story of this man of few words who’s as good with a gun as he is with the law? The surprising answers are all in PARTNERS, John Grisham’s first exclusively digital short story."},
        };
        */

        JSONParser();
/*
        //The value for the ISBN which will be used in searching method, is taken from the scanpage class using a getter.
        String isbn = scanpage.getISBN();
        //the value for ISBN that is recieved from scanpage is a string, so it must be converted to a long to be handled further on.
        ISBN = Long.parseLong(isbn);
        //this runs the method of all the mapping
        //this runs the method that sorts the books array
        BubbleSort(books);
        //middle is returned from binary search and stored here after the binary search method is completed
        //Middle = BinarySearch(books, ISBN);
        //this runs the suggestedBooks method
        suggestedBooks(books, Middle, suggested);
        //This holds the actions to take place if the user clicks the findButton button.
        findButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //it starts the findbook class onclick
                Intent intent = new Intent(DisplayBooks.this, FindBook.class);
                startActivity(intent);
            }
        });
        //This holds the actions for clicking the first book suggestion at the bottom of the page

        suggested1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //the ISBN is set as the ISBN of the book first book, instead of the ISBN being the scanned one.
                ISBN = Long.parseLong(suggested[0][0]);
                //It then runs the same process again but now with a new ISBN
                BinarySearch(books, ISBN);
            }
        });
        //This holds the actions for clicking the second book suggestion at the bottom of the page
        suggested2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //same as above but for its the ISBN of the second book
                ISBN = Long.parseLong(suggested[1][0]);
                BinarySearch(books, ISBN);
            }
        });
        //This holds the actions for clicking the third book suggestion at the bottom of the page
        suggested3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //same as above but for its the ISBN of the third book
                ISBN = Long.parseLong(suggested[2][0]);
                BinarySearch(books, ISBN);
            }
        });
        //This holds the actions for clicking the fourth book suggestion at the bottom of the page
        suggested4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //same as above but for its the ISBN of the fourth book
                ISBN = Long.parseLong(suggested[3][0]);
                BinarySearch(books, ISBN);
            }
        });
*/
    }

    public void JSONParser(){
        try {
            JSONObject jsonRootObject = new JSONObject(BOOKSINFO);
            JSONArray itemsArray = jsonRootObject.getJSONArray("items");
            JSONObject firstbook = itemsArray.getJSONObject(0);
            JSONObject volumeInfo = firstbook.getJSONObject("volumeInfo");
            String Title = volumeInfo.getString("title");
            JSONArray authors = volumeInfo.getJSONArray("authors");
            String Author = authors.getString(0);
            JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
            String imageUrl = imageLinks.getString("thumbnail");
            JSONArray genres = volumeInfo.getJSONArray("categories");
            //fills an array with all the categories
            ArrayList<String> genresArray = new ArrayList<String>();
            for(int i = 0, count = genres.length(); i< count; i++)
            {
                try {
                    genresArray.add(genres.getString(i).toString());
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            int Rating = Integer.parseInt(volumeInfo.getString("averageRating"));
            String Description = volumeInfo.getString("description");
            textViewTitle.setText(Title);
            //fills the Author text area with the contents of the array that corresponds too the ISBN.
            textViewAuthor.setText(Author);
            //fills the Genre text area with the contents of the array that corresponds too the ISBN.
            if (genresArray.size() > 2){
                textViewGenre.setText(genresArray.get(0)+" "+genresArray.get(1)+" "+genresArray.get(2));}
            else{
                textViewGenre.setText(genresArray.get(0));
            }
            //sets the amount of Rating starts by the number from the position array that corresponds too the ISBN.
            ratingBar.setRating(Rating);
            //sets the image of the book,by the img location in the array that corresponds too the ISBN.
            new DownloadImageTask(coverImg).execute(imageUrl);
            // coverImg.setImageDrawable(LoadImage(imageUrl));
            //fills the Description text area with the contents of the array that corresponds too the ISBN.
            textViewDesc.setText(Description);
            //sets the value of position with the value that corresponds toot the ISBN.
            position = "80";
        }
        catch(JSONException e){
            Log.e("displaybooks", "Problem parsing the earthquake JSON results", e);
            System.out.print("error");
        }
    }


//this is a method to organise the array into order of ISBN
    public String[][] BubbleSort(String[][] books) {
        //n is the length of the array
        int n = books.length;
        //an unimportant variable that is set as zero to start
        String temp = "0";
        //this for loop increments up from 0 to the highest position in the array.
        for (int i = 0; i < n; i++) {
            // this loop increments from 1 up untill the position before the last position
            for (int j = 1; j < (n - i); j++) {
                //the if statement checks if the value in the 1st position is higher than the value in the 2nd position. as the loops increment the 1st changes to the 2nd and the 2nd too the 3rd and so on.
                if (Integer.parseInt(books[j - 1][0], 13) > Integer.parseInt(books[j][0], 13)) {
                    //the values of the two positions are switched with the help of the temp variable.
                    temp = books[j - 1][0];
                    books[j - 1][0] = books[j][0];
                    books[j][0] = temp;
                }//end of if
            }//end of for
        }//end of for
        //the Bubble sort method returns the now rearanged books array.
        return books;
    }
//this method searches the organised array using a binary search
    /*
    public int BinarySearch(String[][] books, long ISBN) {
        //far left so its 0
        int Left = 0;
        //the right value is the length of the books array
        int Right = books.length;
        //middle starts at zero here
        int Middle = 0;
        //item found is used to stop the while when necessary
        boolean ItemFound = false;
        //a while loop that loops untill either the item is found or that its not found and the left value becomes higher or
        while (Left <= Right && ItemFound == false) {
            //the middle is half of the two positions added together
            Middle = Math.round((Left + Right) / 2);
            //middleVal is the ISBN of the number at the middle position of the array
            long MiddleVal = Long.parseLong(books[Middle][0]);
            //if the middleval is equal to the isbn we're looking for then display the details for that book.
            if (MiddleVal == ISBN) {
                //stops the search
                ItemFound = true;
                //fills the Title text area with the contents of the array that corresponds too the ISBN.
                textViewTitle.setText(books[Middle][1]);
                //fills the Author text area with the contents of the array that corresponds too the ISBN.
                textViewAuthor.setText(books[Middle][2]);
                //fills the Genre text area with the contents of the array that corresponds too the ISBN.
                textViewGenre.setText(books[Middle][3]);
                //sets the amount of Rating starts by the number from the position array that corresponds too the ISBN.
                ratingBar.setRating(Integer.parseInt(books[Middle][4]));
                //sets the image of the book,by the img location in the array that corresponds too the ISBN.
                coverImg.setImageDrawable(LoadImage());
                //fills the Description text area with the contents of the array that corresponds too the ISBN.
                textViewDesc.setText(books[Middle][7]);
                //sets the value of position with the value that corresponds toot the ISBN.
                position = books[Middle][6];
            }//end if
            //if middle is greater than ISBN
            if (MiddleVal > ISBN) {
                //make the right value one less than the middle value
                Right = Middle - 1;
            }//end if
            //if middle is less than ISBN
            if (MiddleVal < ISBN) {
                //make the left value 1 higher than the middle value
                Left = Middle + 1;
            }//end if
        }
        //return the value of the pposition in the array where the chosen book is
        return Middle;
    }*/

    //this is a getter method, its used by other classes to pass values across classes
    public String getPosition() {
        return position;
    }

    //this is a setter method, it sets the value of position that is then used in the getter method
    public void setPosition(String position) {
        this.position = position;
    }

    //currently a simple XML
    public String[][] suggestedBooks(String[][] books, int Middle, String[][] suggested) {
        //genre of the chosen book from the binary search is saved to the String genre.
        String Genre = books[Middle][3];
        //this incrementor is seperate to the For loop and is used to go up 1 each time a value is found in the search and is added to the array so that the array is filled
        int incrementor = 0;
        // this for loop is used to search each book in the array up untill its searched all the books
        for (int i = 0; (i <20) && (suggested != null || suggested[3][0].isEmpty()); i++) {
            //the if checks if each book's genre in the array is equal to the String Genre
            if (Genre.equalsIgnoreCase(books[i][3])) {
                //it sets the ISBN, the IMG and the rating of all the books that have the same genre as the string Genre to an array.
                suggested[incrementor][0] = books[i][0];
                suggested[incrementor][1] = books[i][5];
                suggested[incrementor][2] = books[i][4];
                incrementor = incrementor + 1;
            }
        }
        //n is the length of the suggested array
        int n = suggested.length;
        //sets temp as a string but doesnt set anything to the variable yet.
        String temp;
        //this for loop increments from 0 too the end of the array using n
        for (int i = 0; i < n; i++) {
            //this for loop increments up too the position bellow the highest position AND that the position isnt empty.
            for (int j = 1; j < (n - i) && (suggested[j][2] != null); j++) {
                //this is a bubble sort, so its checking if the position ahead of the first is higher than it
                //sign switch makes it descening instead of ascending
                if (Long.parseLong(suggested[j - 1][2]) < Long.parseLong(suggested[j][2])) {
                    //swap the elements using the temp as a holder.
                    temp = suggested[j - 1][2];
                    suggested[j - 1][2] = suggested[j][2];
                    suggested[j][2] = temp;
                }
            }
        }
        //these bellow set the images at the bottom of the page, each image is relevant to the page which the button will take the user
        //its written like this on 4 lines to set the first 4 from the array to the 4 imagebuttons
        suggested1.setImageResource(map.get(suggested[0][1]));
        suggested2.setImageResource(map.get(suggested[1][1]));
        suggested3.setImageResource(map.get(suggested[2][1]));
        suggested4.setImageResource(map.get(suggested[3][1]));
        //returns the array of suggested books
        return suggested;
    }
    public static Drawable LoadImage(String imageUrl) {
        try {
            InputStream is = (InputStream) new URL(imageUrl).getContent();
            Drawable drawable = Drawable.createFromStream(is, "src name");
            return drawable;
        } catch (Exception e) {
            return null;
        }
    }
    //this method maps many strings which can be found in the array, to image locations.

}