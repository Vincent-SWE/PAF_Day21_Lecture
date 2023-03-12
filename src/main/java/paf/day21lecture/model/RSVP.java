package paf.day21lecture.model;

import java.sql.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RSVP {

    private Integer Id;

    @NotNull
    @Size(min = 2, max = 150, message = "Full name must be between 2 and 150 characters.")
    private String fullName;

    // @Email auto makes it valid the proper email format.
    @Email(message = "Email must not be blank and must not be in an invalid format.")
    @Size(max = 150, message = "Email can only contain a maximum of 150 characters.")
    private String email;

    private String phone;

    private Date confirmationDate;

    private String comments;

    
}
