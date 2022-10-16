package ch.wesr.journal.journalapi.features.artikel.infrastructure.repository;

import ch.wesr.journal.journalapi.features.artikel.domain.entity.ArtikelEntity;
import org.springframework.data.repository.CrudRepository;

public interface ArtikelStore extends CrudRepository<ArtikelEntity, String> {
}
