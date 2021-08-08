package com.mid_banchers.starting_again;

import com.google.firebase.Timestamp;

public class DataModelPassword {

    private String password;
    private String email;
    private String user;
    private Timestamp addedOn;
    private Timestamp updatedOn;

    public DataModelPassword() {

    }

    public DataModelPassword(String password, String email, String user, Timestamp addedOn, Timestamp updatedOn) {
        this.password = password;
        this.email = email;
        this.user = user;
        this.addedOn = addedOn;
        this.updatedOn = updatedOn;

    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getUser() {
        return user;
    }

    public Timestamp getAddedOn() {
        return addedOn;
    }

    public Timestamp getUpdatedOn() {
        return updatedOn;
    }


}