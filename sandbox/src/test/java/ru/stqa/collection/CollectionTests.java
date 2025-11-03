package ru.stqa.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

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

    @Test
    void setTest(){
        //var set = Set.of("a","b","c");
        var set = new HashSet<>(List.of("a","b","c","a"));
        Assertions.assertEquals(3,set.size());

        set.add("d");
        Assertions.assertEquals(4,set.size());
    }

    @Test
    void testMap()
    {
        var digits = new HashMap<Character,String>();
        digits.put('1',"one");
        digits.put('2',"two");
        digits.put('3',"three");
    }
}
