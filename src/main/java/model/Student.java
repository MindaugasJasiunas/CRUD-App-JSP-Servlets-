package model;

import java.util.function.Predicate;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String universityGroup;
    private String email;
    private Predicate<String> isWordAndNotEmpty = (s) -> (s!=null && s.trim().length()>0 && s.matches("[a-zA-Z ]+"));
    private Predicate<String> isEmail = (s) -> (s!=null && s.trim().length()>0 && s.matches("^.{3,}@[a-zA-Z]{3,}\\.[a-zA-Z\\.]{3,}$"));

    public Student(int id,String firstName, String lastName, String universityGroup, String email) {
        this.id=id;
        if(isWordAndNotEmpty.test(firstName)){
            this.firstName = firstName;
        }
        if(isWordAndNotEmpty.test(lastName)){
            this.lastName = lastName;
        }
        if(isWordAndNotEmpty.test(universityGroup)){
            this.universityGroup = universityGroup;
        }
        if(isEmail.test(email)){
            this.email = email;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (isWordAndNotEmpty.test(firstName)) {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (isWordAndNotEmpty.test(lastName)) {
            this.lastName = lastName;
        }
    }

    public String getUniversityGroup() {
        return universityGroup;
    }

    public void setUniversityGroup(String universityGroup) {
        if (isWordAndNotEmpty.test(universityGroup)) {
            this.universityGroup = universityGroup;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(isEmail.test(email)){
            this.email = email;
        }
    }

    public int getId() {
        return id;
    }
}
