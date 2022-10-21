package ch.wesr.journal.journalapi.features.artikel.domain.entity;

import ch.wesr.journal.journalapi.features.artikel.domain.event.ArtikelEvent;
import ch.wesr.journal.journalapi.features.artikel.domain.event.SaveArtikelRequested;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelEventId;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;

public interface ArtikelEventRepository {

    ArtikelEventId store(SaveArtikelRequested artikelEvent);

    ArtikelEvent getArtikelEventByArtikelId(ArtikelId entityId);
}
