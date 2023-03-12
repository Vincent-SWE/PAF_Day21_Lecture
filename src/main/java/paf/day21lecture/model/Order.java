package paf.day21lecture.model;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    // the code below is incomplete, go pull from Darryl's github after he has troubleshoot it

private Integer id;

private Integer customerId;

private Time orderDate;

private Time shipDate;

private String shipName;







}
