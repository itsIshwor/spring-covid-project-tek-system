package com.hackerrank.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackerrank.api.model.Covid;


@Repository
public interface CovidRepository extends JpaRepository<Covid, Long> {
}
