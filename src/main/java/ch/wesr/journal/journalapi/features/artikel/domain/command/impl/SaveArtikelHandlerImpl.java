package ch.wesr.journal.journalapi.features.artikel.domain.command.impl;

import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikel;
import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikelHandler;
import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikelValidator;
import ch.wesr.journal.journalapi.features.artikel.domain.entity.ArtikelEventRepository;
import ch.wesr.journal.journalapi.features.artikel.domain.event.SaveArtikelRequested;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelEventId;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import ch.wesr.journal.journalapi.shared.CommandFailure;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
public class SaveArtikelHandlerImpl implements SaveArtikelHandler {
    private ApplicationContext applicationContext;
    private SaveArtikelValidator saveArtikelValidator;
    private ArtikelEventRepository artikelEventRepository;


    public SaveArtikelHandlerImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        initHandler();
    }



    @Override
    public Either<CommandFailure, SaveArtikelRequested> handle(SaveArtikel command, ArtikelId entityId) {
        log.info("Handle command: {}", command);

        return saveArtikelValidator.acceptOrReject(command).fold(
                Either::left,
                accept -> {
                    SaveArtikelRequested event = SaveArtikelRequested.eventOf(entityId, command.getTitel(), command.getArtikelInhalt(), command.getTimestamp());
                    ArtikelEventId artikelEventId = artikelEventRepository.store(event);
                    return Either.right(event);
                }
        );
    }

    // FIXME wahrscheinlich in eine Abstracte Klasse auslagern, wie beim Aggregate Root
    // bzw. das Interface in die
    private void initHandler() {
        // FIXME ausprogrammieren - ein RegistryHandler?
    }
}
