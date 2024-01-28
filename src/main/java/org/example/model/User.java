package org.example.model;

public class User {
    private static int nextId = 1;
    private int id;
    private String fullName;
    private int age;
    private String profession;

    public User(String fullName, int age, String profession) {
        this.id = generateId();
        this.fullName = fullName;
        this.age = age;
        this.profession = profession;
    }

    private int generateId() {
        return nextId++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public String getProfession() {
        return profession;
    }

    @Override
    public String toString() {
        return "ID:" + id + ", Full Name:" + fullName + ", Age:" + age + ", Profession:" + profession;
    }

    public String getFormattedInformation() {
        return id + "," + fullName + "," + age + "," + profession;
    }
}
