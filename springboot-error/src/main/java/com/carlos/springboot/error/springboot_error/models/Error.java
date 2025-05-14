package com.carlos.springboot.error.springboot_error.models;

import java.util.Date;

public class Error {

    private String message;
    private String error;
    private int statutCode;
    private Date date;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatutCode() {
        return statutCode;
    }

    public void setStatutCode(int statutCode) {
        this.statutCode = statutCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
