package com.example.guavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.common.base.Charsets;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readFile();
    }

    void listCreate(){
        //JDK
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        //guava
        List<String> list1 = Lists.newArrayList("a","b","c","d");
    }

    void readFile(){
        String fileName = "D:/test.txt";
        try {
            List<String> list = Files.readLines(new File(fileName), Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setTest(){
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("a");
        multiset.add("b", 5);

        System.out.println(multiset.elementSet());//[a, b]
        System.out.println(multiset.count("a"));//2
        System.out.println(multiset.count("b"));//5
        System.out.println(multiset.count("c"));//0
    }

    void mapTest(){
        Multimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("a", 1);
        multimap.put("a", 2);
        multimap.put("a", 4);
        multimap.put("b", 3);
        multimap.put("c", 5);

        System.out.println(multimap.keys());//[a x 3, b, c]
        System.out.println(multimap.get("a"));//[1 ,2, 4]
        System.out.println(multimap.get("b"));//[3]
        System.out.println(multimap.get("c"));//[5]
        System.out.println(multimap.get("d"));//[]

        System.out.println(multimap.asMap());//{a=[1, 2, 4], b=[3], c=[5]}
    }

    String hashTest(String str){
        return Hashing.sha256().newHasher()
                .putString(str, Charsets.UTF_8)
                .hash()
                .toString();
    }
}
