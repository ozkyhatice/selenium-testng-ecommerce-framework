package com.automation.models;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
//name, email, password, title (for example: Mr, Mrs, Miss), birth_date, birth_month, birth_year, firstname, lastname, company, address1, address2, country, zipcode, state, city, mobile_number
public class UserRequest {
    private String username;
    private String password;
    private String email;
    private String title;
    private String birth_date;
    private String birth_month;
    private String birth_year;
    private String firstname;
    private String lastname;
    private String company;
    private String address1;
    private String address2;
    private String country;
    private String zipcode;
    private String state;
    private String city;
    private String mobile_number;
}
