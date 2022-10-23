package ch.wesr.journal.journalapi.features.artikel.infrastructure.repository;

import ch.wesr.journal.journalapi.features.artikel.domain.entity.ArtikelEntity;
import ch.wesr.journal.journalapi.features.artikel.domain.entity.ArtikelEventRepository;
import ch.wesr.journal.journalapi.features.artikel.domain.event.ArtikelEvent;
import ch.wesr.journal.journalapi.features.artikel.domain.event.GetArtikekelByIdRequested;
import ch.wesr.journal.journalapi.features.artikel.domain.event.SaveArtikelRequested;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelEventId;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class ArtikelEventRepositoryImpl implements ArtikelEventRepository {

    @Autowired
    ArtikelStore artikelStore;

    @Override
    public ArtikelEventId store(SaveArtikelRequested artikelEvent) {
        log.info("Storing artikelEvent {}", artikelEvent);

        ArtikelEntity artikel = new ArtikelEntity();
        artikel.setArtikelId(artikelEvent.getArtikelId().id);
        artikel.setTitel(artikelEvent.getTitel());
        artikel.setArtikelInhalt(artikelEvent.getArtikelInhalt());
        artikel.setErstellungsTS(artikelEvent.getTimestamp());

        artikelStore.save(artikel);

       // FIXME
        return artikelEvent.getEventId();
    }

    @Override
    public ArtikelEvent getArtikelEventByArtikelId(ArtikelId entityId) {

        Optional<ArtikelEntity> byId = artikelStore.findByArtikelId(entityId.id);
        log.info("artikelId: {}", entityId.toString());

        if (byId.isPresent()) {
            ArtikelEntity artikelEntity = byId.get();
            return GetArtikekelByIdRequested.eventOf(new ArtikelId(artikelEntity.getArtikelId()), artikelEntity.getTitel(), artikelEntity.getArtikelInhalt(), artikelEntity.getErstellungsTS());
        }
        return null;
    }

    @Override
    public List<ArtikelEvent> getAllArtikelEvents() {
        List<ArtikelEntity> alleArtikelEntity = artikelStore.findAll();

        return alleArtikelEntity.stream()
                .map(artikelEntity -> (ArtikelEvent)GetArtikekelByIdRequested.eventOf(new ArtikelId(artikelEntity.getArtikelId()), artikelEntity.getTitel(), artikelEntity.getArtikelInhalt(), artikelEntity.getErstellungsTS()))
                .toList();
    }
}
