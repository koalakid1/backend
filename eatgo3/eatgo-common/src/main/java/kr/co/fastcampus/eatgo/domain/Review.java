package kr.co.fastcampus.eatgo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    private Integer score;

    @NotEmpty
    private String description;

    private Long restaurantId;
}
