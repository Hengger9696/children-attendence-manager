package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Class;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {

}