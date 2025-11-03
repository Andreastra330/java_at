package ru.stqa.utils;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {
    public static String randomString(int n) {
        var rnd = new Random();
        Supplier<Integer> randomInt = () -> rnd.nextInt(26);
        var  result = Stream.generate(randomInt)
                .limit(n)
                .map(i-> 'a' + i)
                .map(Character::toString)
                .collect(Collectors.joining());
        return result;
    }

    public static String randomInt(int n) {
        var result = new Random().nextInt(90000);
        return Integer.toString(result);

    }

    public static String randomFile(String path) {
        var fileNames = new File(path).list();
        assert fileNames != null;
        var index = new Random().nextInt(fileNames.length);
        return Paths.get(path, fileNames[index]).toString();
    }
}
