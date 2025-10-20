package ru.stqa.utils;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

public class Utils {
    public static String randomString(int n) {
        var rnd = new Random();
        var result = "";
        for (int i = 0; i < n; i++) {
            result = result + (char) ('a' + rnd.nextInt(26));
        }
        return result;
    }

    public static String randomInt(int n){
       var result = new Random().nextInt(90000);
       return Integer.toString(result);

    }

    public static String randomFile(String path){
        var fileNames = new File(path).list();
        assert fileNames != null;
        var index = new Random().nextInt(fileNames.length);
        return Paths.get(path,fileNames[index]).toString();
    }
}
