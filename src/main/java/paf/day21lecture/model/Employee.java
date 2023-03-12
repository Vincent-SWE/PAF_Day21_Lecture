package paf.day21lecture.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    
    private Integer id;

    // firstName (Entity) --> map to --> first_name (mySQL)
    // firstname (entity) --> firstname(mySQL)
    private String firstName;

    private String lastName;

    private Integer salary;

    private List<Dependant> dependants;


}
