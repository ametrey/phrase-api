package com.rooftop.seniorityboost.challenge.repositories;

import com.rooftop.seniorityboost.challenge.entities.Text;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepo extends JpaRepository<Text, Integer> {

    boolean existsTextByHash(String hash);

    Page<Text> findByChars(Integer chars, Pageable pageable);

    Page<Text> findAll(Pageable pageable);

    Text findByHash(String hash);

}
