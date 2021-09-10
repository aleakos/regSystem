package com.registration;

import java.util.Set;

public class Term {

    private String term;
    private String year;

    public Term(String term, String year){
        Set<String> terms = Set.of("FALL", "WINTER", "SUMMER", "SPRING");

        if (year.length() == 4 && terms.contains(term)){
            this.term = term;
            this.year = year;

        } else System.out.println("Invalid input");
    }

    public String getYear() {
        return year;
    }

    public String getTerm() {
        return term;
    }
}
