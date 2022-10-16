package ch.wesr.journal.journalapi.features.artikel.domain.command;

import ch.wesr.journal.journalapi.features.artikel.domain.entity.ArtikelEventRepository;
import ch.wesr.journal.journalapi.features.artikel.domain.event.SaveArtikelRequested;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelEventId;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import ch.wesr.journal.journalapi.shared.CommandFailure;
import ch.wesr.journal.journalapi.shared.CommandHandler;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SaveArtikelHandler implements CommandHandler<SaveArtikel, SaveArtikelRequested, ArtikelId> {

    @Autowired
    private SaveArtikelValidator saveArtikelValidator;

    @Autowired
    private ArtikelEventRepository artikelEventRepository;



    @Override
    public Either<CommandFailure, SaveArtikelRequested> handle(SaveArtikel command, ArtikelId entityId) {
        log.info("Handle command: {}", command);

        return saveArtikelValidator.acceptOrReject(command).fold(
                Either::left,
                accept -> {
                    SaveArtikelRequested event = SaveArtikelRequested.eventOf(entityId, command.getTimestamp());
                    ArtikelEventId artikelEventId = artikelEventRepository.store(event);
                    return Either.right(event);
                }
        );
    }
}
