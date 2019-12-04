package fr.bovoyages.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.bovoyages.entities.Payeur;
@Repository
public interface PayeurRepository extends JpaRepository<Payeur, Long>{
	@Modifying
	@Query(value = "INSERT INTO clients (pk_client, nom, mail, password) VALUES (0, ?1, ?2,?3)", nativeQuery = true)
	@Transactional
	void persistUser( String nom, String mail,String pw);
	
	Payeur findByNom(String nom);
	Payeur findByMail(String mail);
	
	@Query(value="SELECT password FROM clients WHERE nom =?1",nativeQuery =true) String getClientPassword(String nom);
}
