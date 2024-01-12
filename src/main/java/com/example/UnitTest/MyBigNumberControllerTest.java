package com.example.UnitTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyBigNumberControllerTest {

    private MyBigNumber myBigNumber;

    @BeforeEach
    public void setUp() {
        myBigNumber = new MyBigNumber();
    }

    @Test
    public void testSum() {
        NumbersRequest numbersRequest = new NumbersRequest();
        numbersRequest.setStn1("123");
        numbersRequest.setStn2("456");

        String result = myBigNumber.sum(numbersRequest);

        assertEquals("579", result);
    }

    @Test
    public void testSumWithEmptyStrings() {
        NumbersRequest numbersRequest = new NumbersRequest();
        numbersRequest.setStn1("0");
        numbersRequest.setStn2("0");

        String result = myBigNumber.sum(numbersRequest);

        assertEquals("0", result);
    }

    @Test
    public void testSumWithDifferentLengths() {
        NumbersRequest numbersRequest = new NumbersRequest();
        numbersRequest.setStn1("123");
        numbersRequest.setStn2("45678");

        String result = myBigNumber.sum(numbersRequest);

        assertEquals("45801", result);
    }
}