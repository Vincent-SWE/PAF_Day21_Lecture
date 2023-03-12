package paf.day21lecture.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paf.day21lecture.exception.ResourceNotFoundException;
import paf.day21lecture.model.Room;
import paf.day21lecture.service.RoomService;

@RequestMapping("/api/rooms")
@RestController
public class RoomController {
    
    @Autowired
    RoomService roomSvc;

    // An example of how the URL would look like
    // http://localhost:8080/api/rooms/count
    @GetMapping("/count")
    public ResponseEntity<Integer> getRoomCount() {
        Integer roomCount = roomSvc.count();
        return ResponseEntity.ok().body(roomCount);
    }

    // Writing code to get all rooms
    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        rooms = roomSvc.findAll();

        if (rooms.isEmpty()) {
            // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // return ResponseEntity.noContent().build();
            // The above 2 codes are working codes, just different ways to return
            // The code directly below is used if we want to throw a custom error 
            throw new ResourceNotFoundException("No rooms found");
        }
        else {
            // return new ResponseEntity<>(rooms, HttpStatus.OK);
            return ResponseEntity.ok().body(rooms);
        }

    }

    // Writing code to get room by ID
    // If 500 internal server error means that room does not exist so need to 
    // code an error for it.
    @GetMapping("/{room-id}")
    public ResponseEntity<Room> getRoomById(@PathVariable("room-id") Integer id) {
        Room room = roomSvc.findById(id);
        return ResponseEntity.ok().body(room);
    }


    @PostMapping
    public ResponseEntity<Boolean> createRoom(@RequestBody Room room) {
        Boolean created = roomSvc.save(room);

        if (created) {
            return new ResponseEntity<Boolean>(created, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<Boolean>(created, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PutMapping
    public ResponseEntity<Integer> updateRoom(@RequestBody Room room) {
        int updated = 0;

        updated = roomSvc.update(room);

        if (updated == 1) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>(updated, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteRoomById(@PathVariable("id") Integer id) {

        int deleted = 0;

        deleted = roomSvc.deleteById(id);

        if (deleted == 1) {
            return new ResponseEntity<>(deleted, HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>(deleted, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
