package fr.bovoyages.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.bovoyages.entities.Destination;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long>{
	List<Destination> getByRegionStartingWith(String region);
}
