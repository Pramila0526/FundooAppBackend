package com.bridgelabz.fundooappbackend.collaborate.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bridgelabz.fundooappbackend.collaborate.model.Collaborate;
/*********************************************************************************************************
 * @author 	:Pramila Mangesh Tawari
 * Purpose	:Collaborate Repository
 *
 ***********************************************************************************************************/
@Repository
public interface CollaborateRepository extends JpaRepository<Collaborate, Object> 
{
	
}