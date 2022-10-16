package ch.wesr.journal.journalapi.features.artikel.domain.command.impl;

import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikel;
import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikelValidator;
import ch.wesr.journal.journalapi.shared.CommandFailure;
import ch.wesr.journal.journalapi.shared.CommandValidation;
import io.vavr.control.Either;
import org.springframework.stereotype.Component;

@Component
public class SaveArtikelValidatorImpl implements SaveArtikelValidator {
    @Override
    public Either<CommandFailure, SaveArtikel> acceptOrReject(SaveArtikel command) {
        return Either.right(command);
    }
}
