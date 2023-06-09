package com.backend.fitters.util;

import java.util.List;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

public final class MyUtils {

    private MyUtils() {

    }

    public static String capitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

    public static String titleCase(String title) {
        List<String> titleCased = Arrays.stream(
                title.split(" "))
                .map(x -> x.substring(0, 1).toUpperCase() + x.substring(1).toLowerCase()).toList();

        return String.join(" ", titleCased);
    }

    public static int paginate(int page, String direction) {
        int currentPage = page;

        if (direction.equals("next")) {
            currentPage = currentPage + 1;
        }

        if (direction.equals("prev") && page > 0) {
            currentPage = currentPage - 1;
        }

        return currentPage;
    }

}
