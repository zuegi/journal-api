package ch.wesr.journal.journalapi.features.artikel.application;

import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikel;
import ch.wesr.journal.journalapi.features.artikel.domain.ArtikelDings;
import ch.wesr.journal.journalapi.features.artikel.domain.event.GetArtikekelByIdRequested;
import ch.wesr.journal.journalapi.features.artikel.domain.event.SaveArtikelRequested;
import ch.wesr.journal.journalapi.features.artikel.domain.query.GetAlleArtikelQuery;
import ch.wesr.journal.journalapi.features.artikel.domain.query.GetArtikelByIDQuery;
import ch.wesr.journal.journalapi.shared.CommandFailure;
import ch.wesr.journal.journalapi.shared.QueryFailure;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRES_NEW)
@Service
public class ArtikelService {

    @Autowired
    ApplicationContext applicationContext;


    public Either<CommandFailure, SaveArtikelRequested> perform(SaveArtikel saveArtikel) {

        ArtikelDings artikelDings = new ArtikelDings(applicationContext, saveArtikel.getArtikelId());

        return artikelDings.handleCommand(saveArtikel);

    }

    public Either<QueryFailure, GetArtikekelByIdRequested> perform(GetArtikelByIDQuery getArtikelByIDQuery) {

        ArtikelDings artikelDings = new ArtikelDings(applicationContext, getArtikelByIDQuery.getArtikelId());

        return artikelDings.handleQuery(getArtikelByIDQuery);
    }

    public Either<QueryFailure, GetArtikekelByIdRequested> perform(GetAlleArtikelQuery getAlleArtikelQuery) {

        ArtikelDings artikelDings = new ArtikelDings(applicationContext, null);

        return artikelDings.handleQuery(getAlleArtikelQuery);
    }
}
