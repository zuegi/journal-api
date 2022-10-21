package ch.wesr.journal.journalapi.features.artikel.domain.query;

import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikel;
import ch.wesr.journal.journalapi.features.artikel.domain.event.GetArtikekelByIdRequested;
import ch.wesr.journal.journalapi.features.artikel.domain.event.SaveArtikelRequested;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import ch.wesr.journal.journalapi.shared.QueryFailure;
import ch.wesr.journal.journalapi.shared.QueryHandler;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GetArtikelByIdQueryHandler implements QueryHandler<GetArtikelByIDQuery, GetArtikekelByIdRequested, ArtikelId> {

    @Override
    public Either<QueryFailure, GetArtikekelByIdRequested> handle(GetArtikelByIDQuery query, ArtikelId entityId) {
        return null;
    }
}
