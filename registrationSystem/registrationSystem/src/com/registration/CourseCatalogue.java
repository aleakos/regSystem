package com.registration;

import java.util.HashMap;

public class CourseCatalogue {
    private HashMap<String, Course> courses = new HashMap<>();

    public void addCourse(Course course){
        courses.put(course.toString(), course);
    }

    public boolean courseExists(Course course){
//        TODO shouldn't be referencing course object - change to DB query
        return courses.containsKey(course.toString());
    }


}
