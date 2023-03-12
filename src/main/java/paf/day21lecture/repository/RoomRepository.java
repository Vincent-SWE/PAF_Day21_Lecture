package paf.day21lecture.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import paf.day21lecture.exception.ResourceNotFoundException;
import paf.day21lecture.model.Room;

@Repository
public class RoomRepository implements IRoomRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String countSQL = "SELECT COUNT(*) FROM room";
    private final String findAllSQL = "SELECT * FROM room";
    private final String findByIdSQL = "SELECT * FROM room WHERE id = ?";
    private final String deleteByIdSQL = "DELETE FROM room WHERE id = ?";
    private final String insertSQL = "INSERT INTO room (room_type, price) VALUES (?, ?)";
    private final String updateSQL = "UPDATE room SET price = ? WHERE id = ?";


    // Count the number of room reservations(??)
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


    // CREATE new room
    @Override
    public Boolean save(Room room) {
        Boolean saved = false;

        saved = jdbcTemplate.execute(insertSQL, new PreparedStatementCallback<Boolean>() {

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


    // Find all rooms
    public List<Room> findAll() {
        List<Room> rooms = new ArrayList<Room>();
        rooms = jdbcTemplate.query(findAllSQL, BeanPropertyRowMapper.newInstance(Room.class));
        return rooms;
    }

    // Find room by ID
    public Room findById(Integer id) {

        // // This is one way to do it
        // Room room = jdbcTemplate.queryForObject(findByIdSQL, BeanPropertyRowMapper.newInstance(Room.class), id);
        // if (room == null) {
        //     throw new ResourceNotFoundException("Room " + id + " not found!");
        // } 
        // else {
        //     return room;
        // }

        // This is another way to do it
        try { 
            Room room = jdbcTemplate.queryForObject(findByIdSQL, BeanPropertyRowMapper.newInstance(Room.class), id);
            return room;
        } 
        catch (DataAccessException daex) {
            throw new ResourceNotFoundException("Room " + id + " not found!");
        }
    }


    // Update room
    @Override
    public int update(Room room) {
        int updated = 0;

        updated = jdbcTemplate.update(updateSQL, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setFloat(1, room.getPrice());
                ps.setInt(2, room.getId());
            }
        });
        return updated;
    }


    // Delete by ID
    @Override
    public int deleteById(Integer id) {
        int deleted = 0;

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, id);
            }
        };

        deleted = jdbcTemplate.update(deleteByIdSQL, pss);
        return deleted;
    }
}
