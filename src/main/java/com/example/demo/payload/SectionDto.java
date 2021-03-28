package com.example.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionDto {

    @NotNull(message = "Please enter  name")
    private String name;

    @NotNull(message = "Please enter description")
    private String description;

    @NotNull(message = "Please enter course id")
    private Integer courseId;

}
