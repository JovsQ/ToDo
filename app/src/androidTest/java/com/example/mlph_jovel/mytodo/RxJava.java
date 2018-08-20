package com.example.mlph_jovel.mytodo;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.Observable;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class RxJava {

    String result;

    @Test
    public void returnAValue() {
        result = "";
        Observable<String> observer = Observable.just("Hello");

        observer.subscribe(s -> result = s);
        assertTrue(result.equals("Hello"));
    }
}
