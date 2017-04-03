/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymongodb.project1;

import com.google.gson.Gson;
import java.math.BigDecimal;
import java.time.Clock;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author hemonth.mandava
 */
public class GsonExample {
    
    public static void main(String[] args) {
        Person person = new Person();
        person.setAge(12);
        person.setName("hemonth");
        person.setPosition("java developer");
        person.setSalary(BigDecimal.valueOf(150000));
        List<String> skills = Arrays.asList("hibernate","spring","c","c++");
        person.setSkills(skills);
        Gson gson =  new Gson();
        String json = gson.toJson(person);
        System.out.println("Person1 JSON:"+json);
    }
}
