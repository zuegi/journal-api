package ch.wesr.journal.journalapi.features.artikel.domain.query;

import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikel;
import ch.wesr.journal.journalapi.features.artikel.domain.entity.ArtikelEventRepository;
import ch.wesr.journal.journalapi.features.artikel.domain.event.ArtikelEvent;
import ch.wesr.journal.journalapi.features.artikel.domain.event.GetArtikekelByIdRequested;
import ch.wesr.journal.journalapi.features.artikel.domain.event.SaveArtikelRequested;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import ch.wesr.journal.journalapi.shared.Event;
import ch.wesr.journal.journalapi.shared.QueryFailure;
import ch.wesr.journal.journalapi.shared.QueryHandler;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GetArtikelByIdQueryHandler implements QueryHandler<GetArtikelByIDQuery, GetArtikekelByIdRequested, ArtikelId> {

    @Autowired
    private ArtikelEventRepository artikelEventRepository;

    @Override
    public Either<QueryFailure, GetArtikekelByIdRequested> handle(GetArtikelByIDQuery query, ArtikelId entityId) {
        log.info("query: {}", query.getArtikelId().toString());

        ArtikelEvent artikelEvent = artikelEventRepository.getArtikelEventByArtikelId(entityId);

        GetArtikekelByIdRequested getArtikekelByIdRequested = GetArtikekelByIdRequested.eventOf(artikelEvent.getArtikelId(), artikelEvent.getTitel(), artikelEvent.getArtikelInhalt(), artikelEvent.getTimestamp());
        // FIXME Ist das korrekt so
        return Either.right(getArtikekelByIdRequested);
    }
}
