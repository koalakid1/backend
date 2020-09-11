package com.everysports.user.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EClass {

    @Id
    @GeneratedValue
    private Long class_ID;

    @NotNull
    private Long teacher_ID;

    @NotNull
    private String class_Name;

    @NotNull
    private Long class_Num;

    @NotNull
    private Long class_Category;

    @NotNull
    private String class_Curriculum;

    @NotNull
    private String class_Policy;

    @NotNull
    private Long class_LikeNum;

    @NotNull
    private Long class_Price;

    @NotNull
    private Long class_Lowerbound;

    @NotNull
    private Date class_OpenDate;

    @Transient
    private List<EClassAttach> eClassAttaches;


}
