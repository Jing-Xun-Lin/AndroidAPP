package com.paperscissorsstonegame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    private Handler hd;

    @Before
    public void setUp(){
        hd = new Handler();
    }

    @After
    public void tearDown(){
        hd = null;
    }
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(1, hd.foo(1, 3));
        assertEquals(1, hd.foo(2, 1));
        assertEquals(1, hd.foo(3, 2));

        assertEquals(2, hd.foo(1, 2));
        assertEquals(2, hd.foo(2, 3));
        assertEquals(2, hd.foo(3, 1));

        assertEquals(3, hd.foo(1, 1));
        assertEquals(3, hd.foo(2, 2));
        assertEquals(3, hd.foo(3, 3));
    }
}