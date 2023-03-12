package paf.day21lecture.model;

import java.sql.Date;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private Integer id;
    
    private String firstName;

    private String lastName;

    private Date dob;
    
}
