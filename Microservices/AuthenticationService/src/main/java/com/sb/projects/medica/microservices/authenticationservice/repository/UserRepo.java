package com.sb.projects.medica.microservices.authenticationservice.repository;

import com.sb.projects.medica.microservices.authenticationservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

}
