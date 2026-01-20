package org.example.repository;

import org.example.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EverntsRepository extends JpaRepository<Events,Long> {
}
