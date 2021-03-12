package com.Denfop.utils;

public class GuiNumberUtils {

    public static String getString(float number) {
        float g = number;
        float hhh = number;
        float gg = 0;
        int i = 0;
        for (; g >= 10; i++) {
            g = g / 10;


        }
        String maxstorage_2 = "0";
        if (i >= 0 && i < 3 && hhh <= 1000) {


            gg = hhh;
            maxstorage_2 = String.format("%.0f", gg);
        } else if (i >= 3 && i < 6 && hhh >= 1000 && hhh < 1000000) {
            gg = hhh / (1000);
            maxstorage_2 = String.format("%.2fK", gg);
        } else if (i >= 6 && i < 9 && hhh >= 1000000 && hhh < 1000000000) {
            gg = hhh / (1000000);
            maxstorage_2 = String.format("%.2fM", gg);
        } else if (i >= 9 && i < 12 && hhh >= 1000000000 && hhh < 2100000000) {
            gg = hhh / (1000000000);
            maxstorage_2 = String.format("%.2fG", gg);
        }
        return maxstorage_2;

    }
}
