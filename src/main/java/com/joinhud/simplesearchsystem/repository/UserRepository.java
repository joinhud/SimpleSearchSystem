package com.joinhud.simplesearchsystem.repository;


import com.joinhud.simplesearchsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    /*@Query("select u from users u where b.name = :name")
    User findByName(@Param("name") String name);*/

}
