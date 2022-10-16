package ch.wesr.journal.journalapi.features.artikel.domain.entity;

import ch.wesr.journal.journalapi.features.artikel.domain.event.ArtikelEvent;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelEventId;

public interface ArtikelEventRepository {

    ArtikelEventId store(ArtikelEvent artikelEvent);
}
