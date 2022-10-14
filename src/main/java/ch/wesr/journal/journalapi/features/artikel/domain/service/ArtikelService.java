package ch.wesr.journal.journalapi.features.artikel.domain.service;

import ch.wesr.journal.journalapi.features.artikel.domain.command.PerformArtikel;
import ch.wesr.journal.journalapi.features.artikel.domain.entity.Artikel;
import ch.wesr.journal.journalapi.shared.CommandFailure;
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
    public CommandFailure perform(PerformArtikel performArtikel) {

        Artikel artikel = new Artikel(applicationContext, performArtikel.getArtikelId());

        return artikel.handle(performArtikel);

    }
}
