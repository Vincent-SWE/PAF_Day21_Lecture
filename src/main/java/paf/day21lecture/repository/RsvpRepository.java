package paf.day21lecture.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import paf.day21lecture.model.RSVP;

@Repository
public class RsvpRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    // Get the number of RSVPs - get the number of people who have RSVPs
    String countSQL = "SELECT COUNT(*) FROM rsvp";

    // Get all RSVPs
    String selectAllSQL = "SELECT * FROM rsvp";

    // Search for a RSVP - Search a RSVP by ID
    String selectByIdSQL = "SELECT * FROM rsvp WHERE id = ?";
   
    // Search for a RSVP - search a RSVP by name (or parts of)
    String selectByNameSQL = "SELECT * FROM rsvp WHERE full_name like ?";

    // Add a new RSVP and overwrite if there is an existing RSVP
    String insertSQL = "INSERT INTO rsvp (full_name, email, phone, confirmation_date, comments) VALUES (?, ?, ?, ?, ?)";

    // Update an existing RSVP
    String updateSQL = "UPDATE rsvp" + "SET full_name = ?, email = ?, phone = ?, confirmation_date = ?, comments = ?" +
    "WHERE id = ?";

    
    public Integer countAll() {
        return jdbcTemplate.queryForObject(countSQL,Integer.class);
    }

    public List<Map<String, Object>> findAll() {
        return jdbcTemplate.queryForList(selectAllSQL, BeanPropertyRowMapper.newInstance(RSVP.class));
    }


    public RSVP findById(Integer id) {
        return jdbcTemplate.queryForObject(selectByIdSQL, BeanPropertyRowMapper.newInstance(RSVP.class), id);
    }


    public RSVP findByName(String fullName) {
        return jdbcTemplate.queryForObject(selectByNameSQL, BeanPropertyRowMapper.newInstance(RSVP.class), fullName);
    }


    public Boolean save(RSVP rsvp) {
        Integer iResult = jdbcTemplate.update(insertSQL, rsvp.getFullName(), rsvp.getEmail(), rsvp.getPhone(),
                rsvp.getConfirmationDate(), rsvp.getComments());

        return iResult > 0 ? true : false;
    }



    public Boolean update(RSVP rsvp) {
        Integer iResult = 0;

        iResult = jdbcTemplate.update(updateSQL, rsvp.getFullName(), rsvp.getEmail(), rsvp.getPhone(),
                rsvp.getConfirmationDate(), rsvp.getComments(), rsvp.getId());

        return iResult > 0 ? true : false;
    }


    // update with batch update
    public int[] batchUpdate(List<RSVP> rsvps) {

        return jdbcTemplate.batchUpdate(insertSQL,new BatchPreparedStatementSetter() {

            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, rsvps.get(i).getFullName());
                ps.setString(2, rsvps.get(i).getEmail());
                ps.setString(3, rsvps.get(i).getPhone());
                ps.setDate(4, rsvps.get(i).getConfirmationDate());
                ps.setString(5, rsvps.get(i).getComments());
            }
            public int getBatchSize() {
                return rsvps.size();
            }
        });
        // alternate way
        // return jdbcTemplate.batchUpdate(insertSQL, rsvps);
    }





}

