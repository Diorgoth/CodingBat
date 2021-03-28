package com.example.demo.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPracticeDto {


    @NotNull(message = "Please enter solution")
    private String userSolution;

    @NotNull(message = "Please enter score")
    private String score;

    @NotNull(message = "Please enter the date")
    private Date date;

    @NotNull(message = "Please enter user id")
    private Integer userId;

    @NotNull(message = "Please enter problem id")
    private Integer problemId;

}
