package com.everysports.user.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@EqualsAndHashCode
@Table(name = "eClass")
public class EClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_ID")
    private Long classID;

    @Column(name = "teacher_ID")
    private Long teacherID;

    @Column(name = "class_Name")
    private String className;

    @Column(name = "class_Num")
    private Integer classNum;

    @Column(name = "class_Category")
    private Integer classCategory;

    @Column(name = "class_Curriculum")
    private String classCurriculum;

    @Column(name = "class_Policy")
    private String classPolicy;

    @Column(name = "class_LikeNum")
    private Integer classLikeNum;

    @Column(name = "class_Price")
    private Integer classPrice;

    @Column(name = "class_Lowerbound")
    private Integer classLowerbound;

    @Column(name = "class_OpenDate")
    private Date classOpenDate;
}
