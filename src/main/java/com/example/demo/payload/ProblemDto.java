package com.example.demo.payload;

import com.example.demo.entity.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDto {

    @NotNull(message = "Please enter title")
    private String title;

    @NotNull(message = "Please enter body")
    private String body;

    @NotNull(message = "Please enter problem")
    private String problem;

    @NotNull(message = "Please enter solution")
    private String solution;

    @NotNull(message = "Please enter section id")
    private Integer sectionId;

}
