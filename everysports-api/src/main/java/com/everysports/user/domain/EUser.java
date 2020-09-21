package com.everysports.user.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@EqualsAndHashCode
@Table(name = "eUser")
public class EUser {
    @Id
    @Column(name = "user_ID")
    private Long userID;

    @NonNull
    @Column(name="user_Email")
    private String userEmail;

    @NonNull
    @Column(name="user_Name")
    private String userName;

    @NonNull
    @Column(name="user_Gender")
    private boolean userGender;

    @NonNull
    @Column(name="user_Birthday")
    private Date userBirthday;

    @NonNull
    @Column(name="user_Phone")
    private String userPhone;

    @Column(name="user_Point")
    private Integer userPoint;

}
