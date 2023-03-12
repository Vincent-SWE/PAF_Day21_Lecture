package paf.day21lecture.service;

import org.springframework.stereotype.Service;

import paf.day21lecture.repository.IRoomRepository;

@Service
public class RoomService {

    @Autowired
    IRoomRepository roomRepo;
    
}
