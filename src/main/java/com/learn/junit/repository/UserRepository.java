package com.learn.junit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.learn.junit.entity.UserEntity;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(nativeQuery = true, name = "queries.user.findUsersCreatedToday")
    List<UserEntity> findUsersCreatedToday();
    
    Optional<UserEntity> findByName(String name);

    @Query("From UserEntity where name like %:nameToFind%" )
	List<UserEntity> findByNameLike(@Param("nameToFind") String nameToFind);
    
    @Query(nativeQuery = true, name = "queries.user.findUsersCreatedByYear")
    List<UserEntity> findUsersCreatedByYear(Integer year);
    
    Optional<UserEntity> findByEmail(String email);
}
