package ch.wesr.journal.journalapi.features.artikel.infrastructure.repository;

import ch.wesr.journal.journalapi.features.artikel.domain.entity.ArtikelEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ArtikelStore extends CrudRepository<ArtikelEntity, String> {
    Optional<ArtikelEntity> findByArtikelId(String id);
}
