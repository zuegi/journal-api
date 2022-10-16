package ch.wesr.journal.journalapi.features.artikel.domain.event;

import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelEventId;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import lombok.Value;

import java.time.LocalDateTime;

@Value(staticConstructor = "eventOf")
public class SaveArtikelRequested implements ArtikelEvent {

    ArtikelEventId artikelEventId = new ArtikelEventId();
    ArtikelId artikelId;
    LocalDateTime timestamp;


    @Override
    public ArtikelEventId getEventId() {
        return artikelEventId;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
