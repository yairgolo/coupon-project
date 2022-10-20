package com.yair.couponproject.entities;

import lombok.*;

import javax.persistence.*;

@Table(name = "companies")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class Company extends User {

    //--------------- company model -------------------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;


    public Company(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
