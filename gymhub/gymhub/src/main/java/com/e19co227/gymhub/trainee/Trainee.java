package com.e19co227.gymhub.trainee;

import java.util.UUID;

public class Trainee {

    private final UUID traineeID;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final String nic;
    private final String password;
    private final String age;
    private final Gender gender;

    public Trainee(UUID traineeID,
                   String name,
                   String email,
                   String phoneNumber,
                   String nic,
                   String password,
                   String age,
                   Gender gender) {
        this.traineeID = traineeID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nic = nic;
        this.password = password;
        this.age = age;
        this.gender = gender;
    }

    public UUID getTraineeID() {
        return traineeID;
    }

    public String getName() {
        return name;
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

    public String getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    enum Gender {
        Male, Female
    }
}

