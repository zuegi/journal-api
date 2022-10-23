package ch.wesr.journal.journalapi.features.artikel.domain.query;

import ch.wesr.journal.journalapi.features.artikel.domain.entity.ArtikelEventRepository;
import ch.wesr.journal.journalapi.features.artikel.domain.event.ArtikelEvent;
import ch.wesr.journal.journalapi.features.artikel.domain.event.GetAllArtikelRequested;
import ch.wesr.journal.journalapi.features.artikel.domain.event.GetArtikekelByIdRequested;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import ch.wesr.journal.journalapi.shared.QueryFailure;
import ch.wesr.journal.journalapi.shared.QueryHandler;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class GetAlleArtikelQueryHandler  implements QueryHandler<GetAlleArtikelQuery, GetAllArtikelRequested, ArtikelId> {

    @Autowired
    private ArtikelEventRepository artikelEventRepository;

    @Override
    public Either<QueryFailure, GetAllArtikelRequested> handle(GetAlleArtikelQuery query, ArtikelId entityId) {
        log.info("Alle Artikel werden requested");

        return Either.right(artikelEventRepository.getAllArtikelEvents());
    }

}
