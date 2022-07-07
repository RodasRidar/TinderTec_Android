package com.example.tindertec.repository;
import com.example.tindertec.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInteresGeneroRepository extends JpaRepository <InteresGenero, Integer>{
	
	//Usuario findByCorreoAndClave(String correo,String clave);
//docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
}
