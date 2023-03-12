package paf.day21lecture.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dependant {
    
    // We didn't generate Getters and Setters because we used Lombok dependency in this exercise.
    private Integer id;
    private String fullName;
    private String relationship;
    private Date birthDate;
    private Employee employee;;


}
