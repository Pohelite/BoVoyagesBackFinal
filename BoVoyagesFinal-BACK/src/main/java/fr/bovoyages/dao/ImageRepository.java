package fr.bovoyages.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.bovoyages.entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, String>{

}
