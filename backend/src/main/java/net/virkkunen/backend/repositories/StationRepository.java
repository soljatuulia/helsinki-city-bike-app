package net.virkkunen.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.virkkunen.backend.entities.Station;

public interface StationRepository extends JpaRepository<Station,Integer> {

  @Query("SELECT s FROM Station s WHERE s.name LIKE %:filter% OR s.address LIKE %:filter%")
  public Page<Station> filterStations(Pageable pageable, @Param("filter") String filter);

}
