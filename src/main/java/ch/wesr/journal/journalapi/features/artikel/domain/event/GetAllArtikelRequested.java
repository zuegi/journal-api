package ch.wesr.journal.journalapi.features.artikel.domain.event;

import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelEventId;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import lombok.Value;

import java.time.LocalDateTime;

@Value(staticConstructor = "eventOf")
public class GetAllArtikelRequested implements ArtikelEvent{

    ArtikelEventId artikelEventId = new ArtikelEventId();
    ArtikelId artikelId;
    String titel;
    String artikelInhalt;
    LocalDateTime timestamp;

    @Override
    public ArtikelEventId getEventId() {
        return this.artikelEventId;
    }
}
