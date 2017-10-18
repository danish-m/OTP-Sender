package com.example.otpsender;


public class MessageModel {

    private int m_id;
    private String name;
    private int otp;

    private String time;

    public MessageModel()
    {

    }
    public MessageModel(String name, int otp) {
        this.name = name;
        this.otp = otp;
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
