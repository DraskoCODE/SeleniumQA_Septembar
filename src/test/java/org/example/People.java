package org.example;

public class People {

    //atributi
    private String name;
    private int age;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public People(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void printName() {
        System.out.println(getName());
    }

    public boolean isPunoletan() {
        if(getAge() > 18) {
            return true;
        }
        else {
            return false;
        }
    }


}
