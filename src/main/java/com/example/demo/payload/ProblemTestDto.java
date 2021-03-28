package com.example.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemTestDto {

    @NotNull(message = "Please enter arguments")
    private String arguments;

    @NotNull(message = "Please enter result")
    private String result;

    @NotNull(message = "Please enter problem id")
    private Integer problemId;

}
