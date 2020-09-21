package com.everysports.user.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@EqualsAndHashCode
public class EUser {
    @Id
    @GeneratedValue
    @NonNull
    private Long user_ID;

    @NonNull
    private String user_Email;

    @NonNull
    private String user_Name;

    @NonNull
    private boolean user_Gender;

    @NonNull
    private Date user_Birthday;

    @NonNull
    private String user_Phone;

    @NonNull
    private Integer user_Point;

}
