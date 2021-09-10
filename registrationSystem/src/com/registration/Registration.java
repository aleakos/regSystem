package com.registration;

import java.util.HashMap;

public class Registration {
    private final CourseOffering courseOffering;
    private String sid;
    private char grade;

    public Registration(CourseOffering courseOffering, String sid, HashMap<String, CourseOffering> offeringSink) throws Exception {
        this.courseOffering = courseOffering;
        this.sid = sid;
//         check if course is available in the course catalogue
        updateOfferingSink(offeringSink);
    }

    public void updateOfferingSink(HashMap<String, CourseOffering> offeringSink) throws Exception {
        String courseKey = this.courseOffering.getCourse().toString();
        CourseOffering courseOffering = offeringSink.get(courseKey);
        checkSink(courseOffering, offeringSink);
        courseOffering.addRegistration(this);
    }

    public void checkSink(CourseOffering courseOffering, HashMap<String, CourseOffering> offeringSink) throws Exception{
        if (offeringSink.containsKey(courseOffering.getCourse().toString())) {
            CourseOffering currentOffering = offeringSink.get(courseOffering.getCourse().toString());
            if(!currentOffering.getRegistrations().containsKey(this.sid)) return;
        }
        throw new Exception();
    }

    public String getSid(){
        return this.sid;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

}
