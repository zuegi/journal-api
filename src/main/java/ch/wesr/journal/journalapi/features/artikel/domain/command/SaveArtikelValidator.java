package ch.wesr.journal.journalapi.features.artikel.domain.command;

import ch.wesr.journal.journalapi.shared.CommandFailure;
import ch.wesr.journal.journalapi.shared.CommandValidation;
import io.vavr.control.Either;
import org.springframework.stereotype.Component;

public interface SaveArtikelValidator extends CommandValidation<SaveArtikel> {

}
