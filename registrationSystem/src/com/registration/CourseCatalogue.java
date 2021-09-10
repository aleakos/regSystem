package com.registration;

import javax.swing.plaf.IconUIResource;
import java.util.HashMap;

public class CourseCatalogue {
    private HashMap<String, Course> courseList = new HashMap<>();

    public CourseCatalogue(){
        courseList = loadFromDB();
    }

    public void addCourse(Course course){
        courseList.put(course.toString(), course);
    }

    public Course searchCatalouge(String courseName, String courseNumber){
        Course course = new Course(courseName, courseNumber);
        return courseList.get(course.toString());
    }
    private static HashMap<String, Course> loadFromDB(){
        HashMap<String, Course> imaginaryDB = new HashMap<>();

        Course ENGG233 = new Course("ENGG", "233");
        Course ENGG259 = new Course("ENGG", "259");
        Course ENGG607 = new Course("ENSF", "607");

        imaginaryDB.put(ENGG233.toString(), ENGG233);
        imaginaryDB.put(ENGG259.toString(), ENGG259);
        imaginaryDB.put(ENGG607.toString(), ENGG607);

        return imaginaryDB;
    }

    @Override
    public String toString(){
        StringBuilder list = new StringBuilder();

        for (String course: courseList.keySet()){
            list.append(course).append("\n");
        }
        return list.toString();
    }

    public static void main(String[] args) {
        CourseCatalogue cat = new CourseCatalogue();
        System.out.println(cat.toString());

        Course c = cat.searchCatalouge("ENSF","607" );
        Course d = cat.searchCatalouge("ENSF","608" );
        System.out.println("hello");
    }
}
