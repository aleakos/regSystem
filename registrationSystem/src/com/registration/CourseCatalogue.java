package com.registration;

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

        Course ENGG101 = new Course("ENGG", "101");
        Course ENGG259 = new Course("ENGG", "259");
        Course ENGG500 = new Course("ENGG", "500");
        Course ENSF607 = new Course("ENSF", "607");
        Course ENSF608 = new Course("ENSF", "608");
        Course ENSF610 = new Course("ENSF", "610");
        Course ENSF611 = new Course("ENSF", "611");
        Course ENSF612 = new Course("ENSF", "612");
        Course ENSF613 = new Course("ENSF", "613");
        Course ENSF614 = new Course("ENSF", "614");
        Course ENSF700 = new Course("ENSF", "700");

        imaginaryDB.put(ENGG101.toString(), ENGG101);
        imaginaryDB.put(ENGG259.toString(), ENGG259);
        imaginaryDB.put(ENGG500.toString(), ENGG500);
        imaginaryDB.put(ENSF607.toString(), ENSF607);
        imaginaryDB.put(ENSF608.toString(), ENSF608);
        imaginaryDB.put(ENSF610.toString(), ENSF610);
        imaginaryDB.put(ENSF611.toString(), ENSF611);
        imaginaryDB.put(ENSF612.toString(), ENSF612);
        imaginaryDB.put(ENSF614.toString(), ENSF614);
        imaginaryDB.put(ENSF700.toString(), ENSF700);


        Course preReq = new Course("ENGG", "100");

        for (Course c: imaginaryDB.values()){
            c.addPreReq(preReq);
            c.addOffering(new CourseOffering("Dr.A", 1));
            c.addOffering(new CourseOffering("Dr.B", 2));
            c.addOffering(new CourseOffering("Dr.C", 3));
        }

//        testing out pre req
        imaginaryDB.get(ENSF700.toString()).addPreReq(ENSF607);

        return imaginaryDB;
    }

    @Override
    public String toString(){
        StringBuilder list = new StringBuilder();
        int i = 0;
        for (String course: courseList.keySet()){
            list.append(++i).append(": ").append(course).append("\n");
        }
        return list.toString();
    }

    public static void main(String[] args) {
        CourseCatalogue cat = new CourseCatalogue();
        System.out.println(cat.toString());

        Course c = cat.searchCatalouge("ENSF","607" );
        System.out.println(c);
        Course d = cat.searchCatalouge("ENSF","608" );
        System.out.println(d);
    }
}
