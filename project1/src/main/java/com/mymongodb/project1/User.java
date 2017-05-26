/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymongodb.project1;

/**
 *
 * @author Hemonth.Mandava
 */
public class User {
    
    protected Person person;
    public static void main(String [] args){
        Person p1 = null;
        Person p2 = new Person();
        
       // p1.hello();
        p2.hello();
       // Person.hello1();
        p1.hello();
    }
    public void hello(){
        System.out.println("hello");
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
}
