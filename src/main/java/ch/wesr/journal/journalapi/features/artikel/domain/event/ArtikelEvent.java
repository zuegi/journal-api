package ch.wesr.journal.journalapi.features.artikel.domain.event;

import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelEventId;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import ch.wesr.journal.journalapi.shared.Event;

import java.time.LocalDateTime;

public interface ArtikelEvent extends Event {

    ArtikelEventId getEventId();
    ArtikelId getArtikelId();

    LocalDateTime getTimestamp();
}
