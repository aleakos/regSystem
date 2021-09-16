package com.registration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class FrontEnd {
//    simulate databases
    private CourseCatalogue catalogue = new CourseCatalogue();
    private HashMap<Integer, Student> studentDB;
    private Student thisStudent;
    private Scanner userIn = new Scanner(System.in);

    public Student getThisStudent() {
        return thisStudent;
    }

    public FrontEnd(){

        populateStudentDB();

    }

    private void populateStudentDB(){
        studentDB = new HashMap<>();

        Student s1 = new Student(new Name("John", "Doe"));
        Student s2 = new Student(new Name("Jane", "Doe"));
        Student s3 = new Student(new Name("Joe", "Schmo"));

        studentDB.put(s1.getSid(), s1);
        studentDB.put(s2.getSid(), s2);
        studentDB.put(s3.getSid(), s3);

        //  dummy method to add ENGG 100 to all students
        s1.addCoursesCompleted(new Course("ENGG", "100"));
        s2.addCoursesCompleted(new Course("ENGG", "100"));
        s3.addCoursesCompleted(new Course("ENGG", "100"));

        //  s1 is the only student who can take ENSF700
        s1.addCoursesCompleted(new Course("ENSF", "607"));
    }

    public void promptStudent(){
        System.out.println("Hello! Welcome to the course registration system\n");
        System.out.println("To start out we need some information:\n");
        Student tempStudent = querySid();

        if (tempStudent != null){
            this.thisStudent = tempStudent;
            System.out.println();
            System.out.println("Welcome back " + thisStudent.getStudentName());
            System.out.println();
        } else {
            System.out.println("We didn't recognize that ID... setting up new student");
            System.out.println();
            createStudent();
        }

    }



    public void createStudent(){

        boolean valid = false;

        String first;
        String last;

        do
        {
            System.out.println("What is your first name?");
            System.out.print(">>");
            first = userIn.next();
            if (!isNumeric(first))
                valid = true;
            else
                System.out.println("Invalid input.");
        }
        while (!valid);

        valid = false;
        do
        {
            System.out.println("What is your last name?");
            System.out.print(">>");
            last = userIn.next();
            if (!isNumeric(last))
                valid = true;
            else
                System.out.println("Invalid input.");
        }
        while (!valid);

        this.thisStudent = new Student(new Name(first, last));
        // adding in the pre req
        this.thisStudent.addCoursesCompleted(new Course("ENGG", "100"));

    }

    public Student querySid(){
        boolean valid = false;
        String sid;
        do
        {
            System.out.println("What is your student ID ?");
            System.out.print(">>");
            sid = userIn.next();
            if (isNumeric(sid))
                valid = true;
            else
                System.out.println("Invalid input.");
        }
        while (!valid);
        return studentDB.get(Integer.parseInt(sid));
    }

    public Integer receiveInput(){
        Integer userSelect;

        do
        {
            System.out.println("Select a menu item: ");
            giveMenu();
            System.out.print(">>");

            userSelect = userIn.nextInt();
            if(userSelect > 0 && userSelect <= 6){
                return userSelect;
            }
            else
                System.out.println("Invalid input.");
                giveMenu();
        }
        while (true);
    }

    public void giveMenu(){
        String option1 = "1. Search catalogue courses\n";
        String option2 = "2. Add course to student courses\n";
        String option3 = "3. Remove course from student courses\n";
        String option4 = "4. View All courses in catalogue\n";
        String option5 = "5. View all courses taken by student\n";
        String option6 = "6. Quit";

        StringBuilder output = new StringBuilder();
        output.append(option1).append(option2).append(option3).append(option4).append(option5).append(option6);

        System.out.println(output.toString());
    }

    public void menuSelect(int response){
        switch(response) {
            case 1:
                searchCatalouge();
                break;
            case 2:
                addCourse();
                break;
            case 3:
                removeCourse();
                break;
            case 4:
                displayAllCatalouge();
                break;
            case 5:
                thisStudent.showClasses();
                break;
            case 6:
                closeScanner();
                System.out.println("Closing program now....");
                System.exit(0);
                break;
        }
    }

    public void displayAllCatalouge(){
        System.out.println("Available Courses in catalogue:");
        System.out.println(catalogue.toString());
    }

    public Course findCourse(){

        String courseName = promptCourseName().toUpperCase(Locale.ROOT);
        String courseNumber = promptCourseNumber();
        return catalogue.searchCatalouge(courseName, courseNumber);

    }

    public void searchCatalouge(){
        Course course = findCourse();
        if (course!=null){
            System.out.println("Course found, offerings available:");
            System.out.println();
            course.showAllOfferings();
        } else System.out.println("Course not found.");
    }

    public void addCourse(){
        Course course = findCourse();
        if (course != null ){
            Integer section = promptSectionNumber();
            thisStudent.register(catalogue, course.getCourseName(), course.getCourseNumber(), section);
        } else {
            System.out.println("This course does not exist, please try again.");
            System.out.println();
        }

    }

    public void removeCourse(){
        Course course = findCourse();
        if (course != null ){
            Integer section = promptSectionNumber();
            thisStudent.unRegister(catalogue, course.getCourseName(), course.getCourseNumber(), section);

        } else {
            System.out.println("This course does not exist, please try again.");
            System.out.println();
        }

    }


    public String promptCourseName() {
        do
        {
            System.out.println("Enter the 4 character course name (eg ENGG): ");
            System.out.print(">>");

            String courseName = userIn.next();
            if(courseName.length() == 4 && !isNumeric(courseName)){
                return courseName;
            }
            else
                System.out.println("Invalid input.");

        }
        while (true);
    }

    public String promptCourseNumber() {
        do
        {
            System.out.println("Enter the 3 character course number (eg 100): ");
            System.out.print(">>");

            String courseNumber = userIn.next();
            if(courseNumber.length() == 3 && isNumeric(courseNumber)){
                return courseNumber;
            }
            else
                System.out.println("Invalid input.");
        }
        while (true);
    }

    public Integer promptSectionNumber() {
        do
        {
            System.out.println("Enter the section number: ");

            String sectionNumber = userIn.next();
            if(isNumeric(sectionNumber)){
                return Integer.parseInt(sectionNumber);
            }
            else
                System.out.println("Invalid input.");
        }
        while (true);
    }

    public void closeScanner(){
        userIn.close();
    }


    public static boolean isNumeric(String string) {
        int intVal;
        if (string == null || string.equals("")) {
            return false;
        }
        try {
            intVal = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static void main(String[] args){

        FrontEnd commandLine = new FrontEnd();
        commandLine.promptStudent();
//        loop through until the user exits
        while(true){
            int userSelection = commandLine.receiveInput();
            commandLine.menuSelect(userSelection);
        }
    }
}
