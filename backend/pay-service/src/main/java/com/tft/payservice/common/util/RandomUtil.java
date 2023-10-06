package com.tft.payservice.common.util;

import java.util.Random;

public class RandomUtil {

    private static int PWD_LENGTH = 8;
    private static int IDX_LENGTH = 10_000_000;
    private static final char[] passwordTable =  { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '!', '@', '#', '$', '%', '^', '&', '*',
            '(', ')', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

    public static String excuteGenerate() {
        Random random = new Random(System.currentTimeMillis());
        int tablelength = passwordTable.length;
        StringBuffer buf = new StringBuffer();

        for(int i = 0; i < PWD_LENGTH; i++) {
            buf.append(passwordTable[random.nextInt(tablelength)]);
        }

        return buf.toString();
    }

    public static int getRandomNumber() {
        return (int) (Math.random() * IDX_LENGTH);
    }

}
