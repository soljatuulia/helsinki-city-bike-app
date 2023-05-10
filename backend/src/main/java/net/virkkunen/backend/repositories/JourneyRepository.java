package net.virkkunen.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.virkkunen.backend.entities.Journey;

public interface JourneyRepository extends JpaRepository<Journey,Integer> {

}
