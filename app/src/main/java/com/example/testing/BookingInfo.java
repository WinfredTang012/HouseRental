package com.example.testing;

public class BookingInfo {

    public String etFullName;
    public String etApplicantPhone;
    public String etApplicantEmail;
    public String etApplicantBorn;
    public String etMoveIn;

    public BookingInfo() {
    }

    public void setEtFullName(String etFullName) {
        this.etFullName = etFullName;
    }

    public void setEtApplicantPhone(String etApplicantPhone) {
        this.etApplicantPhone = etApplicantPhone;
    }

    public void setEtApplicantEmail(String etApplicantEmail) {
        this.etApplicantEmail = etApplicantEmail;
    }

    public void setEtApplicantBorn(String etApplicantBorn) {
        this.etApplicantBorn = etApplicantBorn;
    }

    public void setEtMoveIn(String etMoveIn) {
        this.etMoveIn = etMoveIn;
    }

    public String getEtFullName() {
        return etFullName;
    }

    public String getEtApplicantPhone() {
        return etApplicantPhone;
    }

    public String getEtApplicantEmail() {
        return etApplicantEmail;
    }

    public String getEtApplicantBorn() {
        return etApplicantBorn;
    }

    public String getEtMoveIn() {return etMoveIn; }
}