package com.registration;

public class FrontEnd {

    public static void main(String[] args){
        CourseCatalogue catalogue = new CourseCatalogue();
        System.out.println(catalogue.toString());
        Name alex = new Name("Alex", "Leakos");
        Student alexStudent = new Student(alex, 100);


    }
}
