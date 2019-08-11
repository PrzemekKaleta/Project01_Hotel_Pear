package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.entity.Stay;
import pl.coderslab.entity.StayState;

import java.util.List;

public interface StayRepository extends JpaRepository<Stay, Long> {


    @Query("select s from Stay s where s.stayState = :state1 and s.room.Id = :roomId or s.stayState = :state2 and s.room.Id = :roomId")
    List<Stay> findAllByStayStatesAndRoomId(@Param("state1") StayState stayState1, @Param("state2") StayState stayState2, @Param("roomId") Long id);


/*
    @Query("select p.password from Person p where p.email = :email")
    String findPasswordByEmail(@Param("email") String email);
    */
}
