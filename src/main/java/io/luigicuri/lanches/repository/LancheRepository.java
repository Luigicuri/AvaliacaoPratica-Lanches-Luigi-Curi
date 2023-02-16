package io.luigicuri.lanches.repository;

import io.luigicuri.lanches.model.Lanche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancheRepository extends JpaRepository<Lanche, Long> {
}
