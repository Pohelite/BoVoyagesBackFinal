package fr.bovoyages.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.bovoyages.entities.Commercial;

@Repository
public interface CommercialRepository extends JpaRepository<Commercial, Long>{
Optional<Commercial> getByUsername(String username);
@Query(value="SELECT u.digest FROM commerciaux u WHERE u.username =?1",nativeQuery =true) String getCommercialSaltedHash(String nom);
@Query(value="SELECT u.salt FROM commerciaux u WHERE u.username =?1",nativeQuery =true) String getCommercialSalt(String nom);
@Transactional
@Modifying
@Query(value="UPDATE commerciaux u SET u.salt =?2, u.digest=?3 WHERE u.username =?1",nativeQuery =true) void saveCommercialSaltAndSaltedHash(String nom, String salt, String saltedHash);

}
