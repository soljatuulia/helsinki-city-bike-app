package net.virkkunen.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.virkkunen.backend.entities.Station;

public interface StationRepository extends JpaRepository<Station,Integer> {

}
