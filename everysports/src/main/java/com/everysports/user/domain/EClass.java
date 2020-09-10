package com.everysports.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

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

    @NotEmpty
    private String class_Name;

    @NotNull
    private Long class_Num;

    @NotNull
    private Long class_Category;

    @NotEmpty
    private String class_Curriculum;

    @NotEmpty
    private String class_Policy;

    @NotNull
    private Long class_LikeNum;

    @NotNull
    private Long class_Price;

    @NotNull
    private Long class_Lowerbound;

    @NotEmpty
    private Date class_OpenDate;

}
