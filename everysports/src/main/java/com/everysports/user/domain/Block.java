package com.everysports.user.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Block {

    @Id
    @GeneratedValue
    private Long id;

    private String reason;

    private LocalDate startDate;

    private LocalDate endDate;

    @NonNull
    private String name;
}
