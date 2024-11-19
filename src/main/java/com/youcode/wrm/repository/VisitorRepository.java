package com.youcode.wrm.repository;

import com.youcode.wrm.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {

}
