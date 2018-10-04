package com.example.android.bookmanager2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

public class SimpleBookManager implements BookManagerInterface
{
    private ArrayList<Book> bookList = new ArrayList<>();
    private boolean firstTime = true;

    // Implementation of singleton needs a new instance of class, method that is returning the only one instance and default constructor
    private static final SimpleBookManager ourInstance = new SimpleBookManager();

    public static SimpleBookManager getBookManager() {
        return ourInstance ;
    }

    private SimpleBookManager()
    {
//        createBook("Bill Bryson","A Short History of Nearly Everything ",145,"0-7679-0817-1","Life" );
//        createBook("Elizabeth Kostova","The Historian",99,"0-316-01177-0","English" );
//        createBook("Andrew S. Tanenbaum","Modern Operating Systems",120," 0132199084","Operating systems" );
//        createBook("Bjarne Stroustrup","The C++ Programming Language",999,"978-0321563842","OOP" );
//        createBook("Collins Gem","SAS Survival Guide Handbook",399," 0060849827","Life" );

        // Here we have to load data from SharedPreferences, instead of manually creating the books
        // like we did above.
    }
    // End of singleton implementation

    @Override
    public int count() {
        return bookList.size();
    }

    @Override
    public Book getBook(int index) {
        return bookList.get(index);
    }

    public void setBookList( ArrayList<Book> bookList )
    {
        this.bookList = new ArrayList<>(bookList);
    }

    @Override
    // This is a overloading method to create books with data.
    public Book createBook()
    {
        Book InstanceOfBook = new Book();
        bookList.add(InstanceOfBook);
        return InstanceOfBook;
    }

    public Book createBook(String author, String title, int price, String isbn, String course)
    {
        Book InstanceOfBook = new Book( author, title, price, isbn, course );
        bookList.add(InstanceOfBook);
        return InstanceOfBook;
    }

    @Override
    public ArrayList<Book> getAllBooks() {
        return bookList;
    }

    @Override
    public void removeBook(Book book) {
        bookList.remove(book);
    }

    @Override
    public void moveBook(int from, int to) {
        Collections.swap(bookList, from, to);
    }

    @Override
    public int getMinPrice()
    {
        int minValue = 0;
        for (int i = 0; i < bookList.size(); i++) {
            Book book = (bookList.get(i));
            if (i == 0) minValue = book.getPrice();
            if (book.getPrice() < minValue) minValue = book.getPrice();
        }
        return minValue;
    }

    @Override
    public int getMaxPrice()
    {
        int maxValue = 0;
        for (int i = 0; i < bookList.size(); i++) {
            Book book = (bookList.get(i));
            if (book.getPrice() > maxValue) maxValue = book.getPrice();
        }
        return maxValue;
    }

    @Override
    public float getMeanPrice()
    {
        float sum = 0;

        for (int i = 0; i < bookList.size(); i++) {
            Book book = (bookList.get(i));
            sum = sum + book.getPrice();
        }

        return sum / bookList.size();
    }

    @Override
    public int getTotalCost()
    {
        int sum = 0;
        for (int i = 0; i < bookList.size(); i++) {
            Book book = (bookList.get(i));
            sum = sum + book.getPrice();
        }
        return sum;
    }

    @Override
    public void saveChanges( SharedPreferences prefs )
    {
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(bookList);
        editor.putString("book_list", json);
        editor.apply();
    }

    public boolean isFirstTime()
    {
        return firstTime;
    }

    public void setFirstTimeFalse()
    {
        firstTime = false;
    }
}
