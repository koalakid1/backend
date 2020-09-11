package com.everysports.user.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EClassAttach {

    @Id
    private String class_Uuid;

    @NotNull
    private String uploadPath;

    @NotNull
    private String fileName;

    @NotNull
    private Long class_ID;


}
