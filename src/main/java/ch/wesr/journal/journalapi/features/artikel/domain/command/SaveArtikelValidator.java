package ch.wesr.journal.journalapi.features.artikel.domain.command;

import ch.wesr.journal.journalapi.shared.CommandFailure;
import ch.wesr.journal.journalapi.shared.CommandValidation;
import io.vavr.control.Either;
import org.springframework.stereotype.Component;

@Component
public class SaveArtikelValidator implements CommandValidation<SaveArtikel> {
    @Override
    public Either<CommandFailure, SaveArtikel> acceptOrReject(SaveArtikel command) {
        return Either.right(command);
    }
}
