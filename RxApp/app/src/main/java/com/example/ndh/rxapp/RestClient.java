package com.example.ndh.rxapp;

import android.content.Context;
import android.os.SystemClock;

import java.util.ArrayList;
import java.util.List;

class RestClient {
    private Context mContext;

    public RestClient(Context context) {
        mContext = context;
    }

    public List<String> getFavoriteBooks() {
        SystemClock.sleep(800);// "Simulate" the delay of network.
        return createBooks();
    }

    public List<String> getFavoriteBooksWithException() {
        SystemClock.sleep(8000);// "Simulate" the delay of network.
        throw new RuntimeException("Failed to load");
    }

    private List<String> createBooks() {
        List<String> books = new ArrayList<>();
        books.add("Hello world 1");
        books.add("Hello world 2");
        books.add("Hello world 3");
        books.add("Hello world 4");
        books.add("Hello world 5");
        books.add("Hello world 6");
        books.add("Hello world 7");
        books.add("Hello world 8");
        books.add("Hello world 9");
        books.add("Hello world 10");
        books.add("Hello world 11");
        books.add("Hello world 12");
        books.add("Hello world 13");
        books.add("Hello world 14");

        return books;
    }
}
