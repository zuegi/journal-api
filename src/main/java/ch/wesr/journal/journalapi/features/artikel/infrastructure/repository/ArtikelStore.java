package ch.wesr.journal.journalapi.features.artikel.infrastructure.repository;

import ch.wesr.journal.journalapi.features.artikel.domain.entity.ArtikelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ArtikelStore extends JpaRepository<ArtikelEntity, String> {
    Optional<ArtikelEntity> findByArtikelId(String id);
}
