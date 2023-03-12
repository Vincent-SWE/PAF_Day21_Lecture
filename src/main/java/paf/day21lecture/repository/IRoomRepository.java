package paf.day21lecture.repository;

import java.util.List;

import paf.day21lecture.model.Room;

public interface IRoomRepository {
    
    int count();
    Boolean save (Room room);
    List<Room> findAll();
    Room findById(Integer id);
    int update(Room room);
    int deleteById(Integer id);
    

}
