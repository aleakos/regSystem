package com.registration;

public class Registration {
    private CourseOffering courseOffering;
    private Student student;
    private char grade;

    public Registration(Student student, CourseOffering courseOffering) {
        this.courseOffering = courseOffering;
        this.student = student;
        addRegistration();

    }

    public CourseOffering getCourseOffering() {
        return courseOffering;
    }

    public Student getStudent() {
        return student;
    }

    private void addRegistration(){
        student.addRegistration(this);
        courseOffering.addRegistration(this);
    }

    public void removeRegistration(Student student, CourseOffering offering){
        student.removeRegistration(this);
        offering.removeRegistration(this);
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

}
