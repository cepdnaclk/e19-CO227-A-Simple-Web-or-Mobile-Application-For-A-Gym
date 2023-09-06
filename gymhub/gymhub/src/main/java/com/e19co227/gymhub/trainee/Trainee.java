package com.e19co227.gymhub.trainee;

import java.util.UUID;

public class Trainee {
    private final UUID traineeId;
    private final String userName;
    private final String name;
    private final Gender gender;
    private final double weight;
    private final String email;
    private final String phoneNumber;
    private final String nic;
    private final String password;
    private final int age;

    public Trainee(UUID traineeId,
                   String userName,
                   String name,
                   Gender gender,
                   double weight,
                   String email,
                   String phoneNumber,
                   String nic,
                   String password, int age) {
        this.traineeId = traineeId;
        this.userName = userName;
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nic = nic;
        this.password = password;
        this.age = age;
    }

    enum Gender {
        Male, Female
    }

    public UUID getTraineeId() {
        return traineeId;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public double getWeight() {
        return weight;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNic() {
        return nic;
    }

    public String getPassword() {
        return password;
    }

    /*public int getAge() {
        return age;
    }*/
}
