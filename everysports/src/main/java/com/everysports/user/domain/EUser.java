package com.everysports.user.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@EqualsAndHashCode
public class EUser {
    @Id
    @GeneratedValue
    private Long user_ID;

    @NotNull
    private String user_Email;

    @NotNull
    private String user_Name;

    @NotNull
    private boolean user_Gender;

    @NotNull
    private Date user_Birthday;

    @NotNull
    private String user_Phone;

    @NotNull
    private Integer user_Point;

}
