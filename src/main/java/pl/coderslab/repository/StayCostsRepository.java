package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.entity.StayCosts;

public interface StayCostsRepository extends JpaRepository<StayCosts, Long> {

    @Query("select sc.price from StayCosts sc where sc.residentsNumber = :residentsNumber and sc.roomCapacity = :roomCapacity")
    double findPriceByResidentsNumberAndRoomCapacity(@Param("residentsNumber") int residenstsNumber, @Param("roomCapacity") int roomCapacity);



}
