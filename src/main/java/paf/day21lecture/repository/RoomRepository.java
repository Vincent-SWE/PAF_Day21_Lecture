package paf.day21lecture.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import paf.day21lecture.model.Room;

public class RoomRepository implements IRoomRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String countSQL = "SELECT COUNT(*) FROM room";
    private final String findAllSQL = "SELECT * FROM room";
    private final String findByIdSQL = "SELECT * FROM room WHERE id = ?";
    private final String deleteByIdSQL = "DELETE FROM room WHERE id = ?";
    private final String insertSQL = "INSERT INTO room (room_type, price) VALUES ('deluxe', 250)";
    private final String updateSQL = "UPDATE room SET price = ? WHERE id = ?";


    @Override
    public int count() {
        Integer result = 0;
        result = jdbcTemplate.queryForObject(countSQL, Integer.class);
        if (result == null) {
            return 0;
        } 
        else {
            return result;
        }
    }


    // // CREATE new Room
    // @Override
    // public Boolean save(Room room) {
    //    Boolean saved = false;

    //     saved = jdbcTemplate.execute(insertSQL, PreparedStatementCallback<Boolean>() {

    //         @Override
    //         public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException{
    //             ps.setString(1, room.getRoomType());
    //             ps.setFloat(2, room.getPrice());
    //             Boolean rslt = ps.execute();

    //             return rslt;
    //         }
    //     });
    //     return saved;
    // }

    // my code directly below has errors, go and take darryl's code
    @Override
    public Boolean save(Room room) {
        Boolean saved;


        saved = jdbcTemplate.execute(insertSQL, new PreparedStatementCallback<Boolean>(){
            
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException {
                ps.setString(1, room.getRoomType());
                ps.setFloat(2, room.getPrice());
                Boolean rslt = ps.execute();
                return rslt;
            }
        });



        return saved;
    }


    @Override
    public List<Room> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Room findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public int update(Room room) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
    


    
}
