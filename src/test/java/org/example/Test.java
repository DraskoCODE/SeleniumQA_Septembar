package org.example;

import org.openqa.selenium.chrome.ChromeDriver;

public class Test {

    public static void main(String[] args) {
        //People people = new People();
       // people.setName("Milan");
        //people.setAge(34);

        //people.getName();
        //people.getAge();
       People people = new People("Milan", 34);
       people.printName();
       System.out.println(people.isPunoletan());

       People people1 = new People("Ivan");
    }
}


