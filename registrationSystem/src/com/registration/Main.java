package com.registration;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
//        hard coded for ease of use
        Term term = new Term("FALL", "2021");

//         acting as database of sorts
        HashMap<String, CourseOffering> offeringSink = new HashMap<>();

        CourseCatalogue catalogue = new CourseCatalogue();

        Course ENSF688 = new Course("ENSF", "688");
        Course ENSF699 = new Course("ENSF", "699");
        Course ENSF622 = new Course("ENSF", "622");
        Course ENSF621 = new Course("ENSF", "621");

        catalogue.addCourse(ENSF688);
        catalogue.addCourse(ENSF699);
        catalogue.addCourse(ENSF622);
        catalogue.addCourse(ENSF621);

        offeringSink.put(ENSF688.toString(), new CourseOffering(ENSF688, term));
        offeringSink.put(ENSF699.toString(), new CourseOffering(ENSF699, term));
        offeringSink.put(ENSF622.toString(), new CourseOffering(ENSF622, term));
        offeringSink.put(ENSF621.toString(), new CourseOffering(ENSF621, term));
        offeringSink.put(ENSF621.toString(), new CourseOffering(ENSF621, term));



        Student alex = new Student("Alex", "Leakos", "200");
        Student jon = new Student("Jon", "Leakos", "100");

        alex.register(offeringSink.get(ENSF621.toString()), offeringSink, catalogue);
        alex.register(offeringSink.get(ENSF622.toString()), offeringSink, catalogue);
        jon.register(offeringSink.get(ENSF621.toString()), offeringSink, catalogue);
//         issue with this one - adding duplicates because the hash set in register reads the students as different - need to use ID
        jon.register(offeringSink.get(ENSF621.toString()), offeringSink, catalogue);
        alex.register(offeringSink.get(ENSF621.toString()), offeringSink, catalogue);


    }
}
