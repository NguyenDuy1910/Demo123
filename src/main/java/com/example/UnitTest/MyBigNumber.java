package com.example.UnitTest;

import java.lang.StringBuilder;
import org.apache.logging.log4j.*;

public class MyBigNumber {
    private static final Logger logger = LogManager.getLogger(MyBigNumber.class);

    public static String sum(NumbersRequest numbersRequest) {
        String stn1=numbersRequest.getStn1();
        String stn2=numbersRequest.getStn2();
        int maxLength = Math.max(stn1.length(), stn2.length());
        int carry = 0;
        StringBuilder result = new StringBuilder();
        try {
            for (int i = 0; i < maxLength; i++) {
                int digit1 = getDigit(stn1, stn1.length() - i - 1);
                int digit2 = getDigit(stn2, stn2.length() - i - 1);
                int sum = digit2 + digit1 + carry;
                int resultDigit = sum % 10;
                carry = sum / 10;
                // Ghi log thứ tự thực hiện

                result.insert(0, resultDigit);
            }
            if (carry > 0) {
                result.insert(0, carry);
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ và trả về thông báo lỗi
            return "Error: " + e.getMessage();
        }

        return result.toString();
    }

    private static int getDigit(String number, int index) {
        if (index < 0 || index >= number.length()) {
            return 0;
        }

        return Character.getNumericValue(number.charAt(index));
    }
}