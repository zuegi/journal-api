package ch.wesr.journal.journalapi.features.artikel.domain.service;

import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikel;
import ch.wesr.journal.journalapi.features.artikel.domain.entity.Artikel;
import ch.wesr.journal.journalapi.features.artikel.domain.event.GetArtikekelByIdRequested;
import ch.wesr.journal.journalapi.features.artikel.domain.event.SaveArtikelRequested;
import ch.wesr.journal.journalapi.features.artikel.domain.query.ArtikelResponseDTO;
import ch.wesr.journal.journalapi.features.artikel.domain.query.GetArtikelByIDQuery;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import ch.wesr.journal.journalapi.features.artikel.infrastructure.rest.model.ArtikelResponse;
import ch.wesr.journal.journalapi.shared.CommandFailure;
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

        Artikel artikel = new Artikel(applicationContext, saveArtikel.getArtikelId());

        return artikel.handle(saveArtikel);

    }

    public Either<CommandFailure, GetArtikekelByIdRequested> perform(GetArtikelByIDQuery getArtikelByIDQuery) {

        Artikel artikel = new Artikel(applicationContext, getArtikelByIDQuery.getArtikelId());

        // ToDO ArtikelQueryHandler erstellen und perform Methode ausprogrammieren
        return artikel.perform(getArtikelByIDQuery);
    }
}
