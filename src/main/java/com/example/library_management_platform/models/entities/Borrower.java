package com.example.library_management_platform.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
 @Table(name = "borrower", uniqueConstraints = {
        @UniqueConstraint(columnNames = "contact_email"),@UniqueConstraint(columnNames = "contact_number")
})
public class Borrower extends BaseEntity {

    @Column(name="username")
    private String username;

    @Column(name="contact_email")
    private String contactEmail;

    @Column(name="contact_number")
    private String contactNumber;

    @Column(name="password")
    private String password;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name="gender")
    private GenderEnum gender;

    @Column(name="dob")
    private String dob;

    @Column(name="issuance_ids")
    private List<Long> issuanceIds;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private StatusEnum status;

    @Column(name="address")
    private String address;


    public enum StatusEnum{
        ACTIVE,
        IN_ACTIVE
    }

    public enum GenderEnum{
        MALE,
        FEMALE
    }
}
