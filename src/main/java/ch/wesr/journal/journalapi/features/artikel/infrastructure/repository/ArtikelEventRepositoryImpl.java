package ch.wesr.journal.journalapi.features.artikel.infrastructure.repository;

import ch.wesr.journal.journalapi.features.artikel.domain.entity.ArtikelEventRepository;
import ch.wesr.journal.journalapi.features.artikel.domain.event.ArtikelEvent;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelEventId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class ArtikelEventRepositoryImpl implements ArtikelEventRepository {
    @Override
    public ArtikelEventId store(ArtikelEvent artikelEvent) {
        log.info("Storing artikelEvent {}", artikelEvent);

       // FIXME
        return artikelEvent.getEventId();
    }
}
