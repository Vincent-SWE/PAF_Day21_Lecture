package paf.day21lecture.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Room {
    
    private Integer id;
    private String roomType;
    private Float price;

}
