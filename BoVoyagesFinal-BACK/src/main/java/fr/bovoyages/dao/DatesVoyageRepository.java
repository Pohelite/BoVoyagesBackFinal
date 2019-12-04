package fr.bovoyages.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.bovoyages.entities.DatesVoyage;

@Repository
public interface DatesVoyageRepository extends JpaRepository<DatesVoyage, Long>{

}
