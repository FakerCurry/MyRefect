package com.sjw.myrefect;

/**
 * Created by pc on 2018/7/6.
 */

class A {
    private String name;
    private int age;

    public A() {
    }

    public A(String name) {
        this.name = name;
    }

    public A(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String print(int a, int b) {
//        System.out.println(a + b);
        return (a + b) + "";
    }

    private String print(String a, String b) {
//        System.out.println(a.toUpperCase() + "," + b.toUpperCase());
        return a.toUpperCase() + "," + b.toUpperCase();
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
}
