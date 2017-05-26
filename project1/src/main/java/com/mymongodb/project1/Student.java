/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymongodb.project1;

import java.util.ArrayList;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Hemonth.Mandava
 */
public class Student {
       
    @Autowired
    Person person;
    static String[] an = {"13","2","5","21","3","1"};
    static ArrayList<Integer> inl = new ArrayList<>();
    public static void main(String []args){
        for(String s: an){
            inl.add(Integer.valueOf(s));
            
        }
        Collections.sort(inl);
        for(Integer integer: inl){
            System.out.println(integer);
        }
        
    }
}
