package com.everysports.user.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@EqualsAndHashCode
@Table(name = "eAll")
public class EAll {
    @Id
    @Column(name = "all_ID")
    private Long id;

    @NonNull
    @Column(name = "all_Type")
    private Integer allType;
}