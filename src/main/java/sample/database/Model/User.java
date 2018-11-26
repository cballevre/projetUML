package sample.database.Model;

import java.util.ArrayList;

public class User {

    /**
     * @id
     */
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String service;
    private Role role;
    private float holidayBalance;
    private ArrayList<DayoffRequest> dayoffRequests;

    public User(String firstname, String lastname, String email, String password, String service, Role role, float holidayBalance, ArrayList<DayoffRequest> dayoffRequests) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.service = service;
        this.role = role;
        this.holidayBalance = holidayBalance;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public float getHolidayBalance() {
        return holidayBalance;
    }

    public void setHolidayBalance(float holidayBalance) {
        this.holidayBalance = holidayBalance;
    }

    public ArrayList<DayoffRequest> getDayoffRequests() {
        return dayoffRequests;
    }

    public void setDayoffRequests(ArrayList<DayoffRequest> dayoffRequests) {
        this.dayoffRequests = dayoffRequests;
    }

}