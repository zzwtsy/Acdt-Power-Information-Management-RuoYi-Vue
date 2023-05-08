package com.ruoyi.electric;

import okhttp3.Headers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyHeader {
    private static final Random rand = new Random();

    public static Headers header(String cookie) {
        return new Headers.Builder()
                .add("Cookie", cookie)
                .add("User-Agent", userAgent())
                .add("Host", "df.acdt.edu.cn")
                .build();
    }

    /**
     * userAgent
     *
     * @return {@link String}
     */
    private static String userAgent() {
        List<String> list = new ArrayList<>();
        list.add("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.5410.0 Safari/537.36");
        list.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.5410.0 Safari/537.36");
        list.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36 Edg/111.0.1661.51");
        list.add("Mozilla/5.0 (Windows NT 10; Win64; x64; rv:83.0) Gecko/20100101 Firefox/83.0");
        list.add("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36");
        return list.get(rand.nextInt(list.size()));
    }
}
