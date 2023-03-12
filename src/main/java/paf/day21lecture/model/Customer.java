package paf.day21lecture.model;

import java.sql.Date;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    // We didn't generate Getters and Setters because we used Lombok dependency in this exercise.
    private Integer id;
    private String firstName;
    private String lastName;
    private Date dob;
    
}
