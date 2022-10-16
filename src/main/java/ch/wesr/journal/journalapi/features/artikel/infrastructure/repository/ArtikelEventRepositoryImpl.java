package ch.wesr.journal.journalapi.features.artikel.infrastructure.repository;

import ch.wesr.journal.journalapi.features.artikel.domain.entity.ArtikelEntity;
import ch.wesr.journal.journalapi.features.artikel.domain.entity.ArtikelEventRepository;
import ch.wesr.journal.journalapi.features.artikel.domain.event.ArtikelEvent;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelEventId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class ArtikelEventRepositoryImpl implements ArtikelEventRepository {

    @Autowired
    ArtikelStore artikelStore;

    @Override
    public ArtikelEventId store(ArtikelEvent artikelEvent) {
        log.info("Storing artikelEvent {}", artikelEvent);

        ArtikelEntity artikel = new ArtikelEntity();
        artikel.setArtikelId(artikelEvent.getArtikelId().toString());
        artikel.setTitel(artikelEvent.getTitel());
        artikel.setArtikelInhalt(artikelEvent.getArtikelInhalt());
        artikel.setErstellungsTS(artikelEvent.getTimestamp());

        artikelStore.save(artikel);

       // FIXME
        return artikelEvent.getEventId();
    }
}
