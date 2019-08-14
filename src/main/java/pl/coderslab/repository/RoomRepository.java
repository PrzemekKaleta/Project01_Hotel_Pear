package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.entity.Room;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room>findAllByCapacity(int capacity);

    @Query("select r from Room r where r.Id = :roomID")
    Room findByID(@Param("roomID") Long roomID);

    }
