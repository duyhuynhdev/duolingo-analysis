package jp.ac.jaist;

import jp.ac.jaist.Common.Path;
import jp.ac.jaist.Crawler.DuolingoCrawler;

import java.io.IOException;

/**
 * Name: Huynh Phuong Duy
 * Id: 1610161
 * Date 12:40 PM 7/3/17.
 */
public class Main {
    public static void main(String... args) {
        try {
            init();
            new DuolingoCrawler().run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void init() throws IOException {
        Path.buildRoot();
    }

}
