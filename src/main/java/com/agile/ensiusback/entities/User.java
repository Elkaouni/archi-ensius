package com.agile.ensiusback.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 50)
    private String username;

    private String token;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String secondName;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(length = 50)
    private String email;

    @Column(length = 50)
    private String password;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean locked = false;
    private Boolean enabled = false;


}
