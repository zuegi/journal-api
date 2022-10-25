package ch.wesr.journal.journalapi.features.artikel.domain.command;

import ch.wesr.journal.journalapi.features.artikel.domain.event.SaveArtikelRequested;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import ch.wesr.journal.journalapi.shared.CommandHandler;

public interface SaveArtikelHandler extends CommandHandler<SaveArtikel, SaveArtikelRequested, ArtikelId> {

}
