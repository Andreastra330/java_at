package ru.stqa.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CollectionTests {

    @Test
    void arrayTest(){
        var array = new String[]{"a","b","c"};
        Assertions.assertEquals("a",array[0]);

        array[0] = "d";
        Assertions.assertEquals("d",array[0]);
    }

    @Test
    void listTest(){
        var list = new ArrayList<>(List.of("a","b","c"));
        Assertions.assertEquals(3,list.size());
        Assertions.assertEquals("a",list.getFirst());
        list.set(0,"d");
        Assertions.assertEquals("d",list.getFirst());
    }
}
