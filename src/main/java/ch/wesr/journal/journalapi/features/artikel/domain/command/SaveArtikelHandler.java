package ch.wesr.journal.journalapi.features.artikel.domain.command;

import ch.wesr.journal.journalapi.features.artikel.domain.event.ArtikelRequestEvent;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import ch.wesr.journal.journalapi.shared.CommandFailure;
import ch.wesr.journal.journalapi.shared.CommandHandler;
import org.springframework.stereotype.Component;

@Component
public class SaveArtikelHandler implements CommandHandler<PerformArtikel, ArtikelRequestEvent, ArtikelId> {
    @Override
    public CommandFailure handle(PerformArtikel command, ArtikelId entityId) {
        // hier wird der gesamte Save des Artikels mittels Repository abgehandelt
        return null;
    }
}
