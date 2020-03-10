package model;

import java.util.function.Predicate;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String universityGroup;
    private String email;
    private Predicate<String> isWord = (s) -> (s.matches("[a-zA-Z ]+"));
    private Predicate<String> isNotEmpty = (s) -> (s!=null && s.trim().length()>0);
    private Predicate<String> isEmail = (s) -> (s!=null && s.trim().length()>0 && s.matches("^.{3,}@[a-zA-Z]{3,}\\.[a-zA-Z\\.]{3,}$"));

    public Student(int id,String firstName, String lastName, String universityGroup, String email) {
        this.id=id;
        if(isNotEmpty.and(isWord).test(firstName)){
            this.firstName = firstName;
        }
        if(isNotEmpty.and(isWord).test(lastName)){
            this.lastName = lastName;
        }
        if(isNotEmpty.test(universityGroup)){
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
        if (isNotEmpty.and(isWord).test(firstName)) {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (isNotEmpty.and(isWord).test(lastName)) {
            this.lastName = lastName;
        }
    }

    public String getUniversityGroup() {
        return universityGroup;
    }

    public void setUniversityGroup(String universityGroup) {
        if (isNotEmpty.test(universityGroup)) {
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

    public boolean isAllFields(){
        if(firstName!=null && lastName!=null && universityGroup!=null && email!=null){
            if (firstName.trim().length()>0 && lastName.trim().length()>0 && universityGroup.trim().length()>0 && email.trim().length()>0){
                return true;
            }
        }
        return false;
    }
}
