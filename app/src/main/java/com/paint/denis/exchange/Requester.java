package com.paint.denis.exchange;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Requester {
    public static String runRequest(String stringUrl) throws IOException {

        URL url = new URL(stringUrl);

        Scanner in = new Scanner((InputStream) url.getContent());

        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }

        return result;
    }
}
