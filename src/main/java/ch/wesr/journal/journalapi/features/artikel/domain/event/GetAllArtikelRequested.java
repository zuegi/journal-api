package ch.wesr.journal.journalapi.features.artikel.domain.event;

import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelEventId;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value(staticConstructor = "eventOf")
public class GetAllArtikelRequested implements ArtikelEvent {

    ArtikelEventId artikelEventId = new ArtikelEventId();
    LocalDateTime localDateTime;

    List<GetArtikekelByIdRequested> getArtikekelByIdRequestedList;

    @Override
    public ArtikelEventId getEventId() {
        return artikelEventId;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return localDateTime;
    }
}
