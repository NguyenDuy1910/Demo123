package com.example.demo;

import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.StringBuilder;
import org.apache.logging.log4j.*;

@RestController
public class MyBigNumberController {
    private static final Logger logger = LogManager.getLogger(MyBigNumberController.class);

    @PostMapping("/sum")
    public String sum(@RequestBody NumbersRequest numbers) {
        String stn1 = numbers.getStn1();
        String stn2 = numbers.getStn2();
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
                logger.info("Digit 1: {}", digit1);
                logger.info("Digit 2: {}", digit2);
                logger.info("Sum: {}", sum);
                logger.info("Result Digit: {}", resultDigit);
                logger.info("Carry: {}", carry);
                result.insert(0, resultDigit);
            }
            if (carry > 0) {
                result.insert(0, carry);
            }
            logger.info("Result: {}", result);
        } catch (Exception e) {
            logger.error("An error occurred during sum calculation: {}", e.getMessage());
            // Xử lý ngoại lệ và trả về thông báo lỗi
            return "Error: " + e.getMessage();
        }

        return result.toString();
    }

    private int getDigit(String number, int index) {
        if (index < 0 || index >= number.length()) {
            return 0;
        }

        return Character.getNumericValue(number.charAt(index));
    }
}