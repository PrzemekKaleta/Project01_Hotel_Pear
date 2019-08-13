package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Room;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room>findAllByCapacity(int capacity);

    Room findById(Long idRoom);

    }
