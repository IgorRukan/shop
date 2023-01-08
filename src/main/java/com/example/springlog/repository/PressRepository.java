package com.example.springlog.repository;

import com.example.springlog.model.Press;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PressRepository extends JpaRepository<Press,Long> {
}
