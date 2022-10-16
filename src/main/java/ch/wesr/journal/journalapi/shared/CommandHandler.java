package ch.wesr.journal.journalapi.shared;

import ch.wesr.journal.journalapi.features.artikel.domain.event.SaveArtikelRequested;
import io.vavr.control.Either;

public interface CommandHandler<C extends Command, E extends Event, ID> {
    Either<CommandFailure, E> handle(C command, ID entityId);
}
