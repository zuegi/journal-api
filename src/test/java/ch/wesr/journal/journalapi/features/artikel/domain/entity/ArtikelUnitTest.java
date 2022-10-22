package ch.wesr.journal.journalapi.features.artikel.domain.entity;

import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikel;
import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikelHandler;
import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikelValidator;
import ch.wesr.journal.journalapi.features.artikel.domain.command.impl.SaveArtikelHandlerImpl;
import ch.wesr.journal.journalapi.features.artikel.domain.command.impl.SaveArtikelValidatorImpl;
import ch.wesr.journal.journalapi.features.artikel.domain.event.ArtikelEvent;
import ch.wesr.journal.journalapi.features.artikel.domain.event.GetArtikekelByIdRequested;
import ch.wesr.journal.journalapi.features.artikel.domain.query.GetArtikelByIDQuery;
import ch.wesr.journal.journalapi.features.artikel.domain.query.GetArtikelByIdQueryHandler;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelEventId;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import ch.wesr.journal.journalapi.features.artikel.infrastructure.repository.ArtikelEventRepositoryImpl;
import ch.wesr.journal.journalapi.features.artikel.infrastructure.repository.ArtikelStore;
import ch.wesr.journal.journalapi.shared.Event;
import ch.wesr.journal.journalapi.shared.QueryFailure;
import io.vavr.control.Either;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class ArtikelUnitTest {

    ApplicationContext applicationContext = Mockito.mock(ApplicationContext.class);
    ArtikelEventRepository artikelEventRepository;

    ArtikelStore artikelStore = Mockito.mock(ArtikelStore.class);

    SaveArtikelHandler saveArtikelHandler;
    SaveArtikelValidator saveArtikelValidator;

    GetArtikelByIdQueryHandler getArtikelByIdQueryHandler;
    ArgumentCaptor<ArtikelEntity> artikelEntityCaptor;
    ArgumentCaptor<String> artikelIdCaptor;

    @BeforeEach
    void init() throws IllegalAccessException {
        Mockito.reset(artikelStore);
        saveArtikelHandler = new SaveArtikelHandlerImpl();
        saveArtikelValidator = new SaveArtikelValidatorImpl();
        artikelEventRepository = new ArtikelEventRepositoryImpl();
        getArtikelByIdQueryHandler = new GetArtikelByIdQueryHandler();

        FieldUtils.writeField(saveArtikelHandler, "saveArtikelValidator", saveArtikelValidator, true);
        FieldUtils.writeField(saveArtikelHandler, "artikelEventRepository", artikelEventRepository, true);
        FieldUtils.writeField(getArtikelByIdQueryHandler, "artikelEventRepository", artikelEventRepository, true);
        FieldUtils.writeField(artikelEventRepository, "artikelStore", artikelStore, true);

        when(applicationContext.getBean(SaveArtikelHandler.class)).thenReturn(saveArtikelHandler);
        when(applicationContext.getBean(GetArtikelByIdQueryHandler.class)).thenReturn(getArtikelByIdQueryHandler);

        artikelEntityCaptor = ArgumentCaptor.forClass(ArtikelEntity.class);
        artikelIdCaptor = ArgumentCaptor.forClass(String.class);
    }

    @Test
    void saveArtikel() {
        // given
        ArtikelId artikelId = new ArtikelId();
        LocalDateTime now = LocalDateTime.now();
        SaveArtikel saveArtikel = SaveArtikel.commandOf(artikelId,
                "Git - entferne lokale Branches",
                "<h1>Git - entferne lokale Branches</h1><p><br></p><p>Entferne lokale git branches, welche schon merged sind.</p><p><br></p><ol><li>Öffne eine Shell</li><li>Fetch den letzten Stand von git</li><li><code>git fetch</code></li><li>Liste alle lokalen git branches auf</li><li><code>git branch</code></li><li>Lösche alle lokalen git branches, welche in den main branch merged wurden.</li><li><code>git branch --merged main | grep -v -e 'main' -e 'release' | xargs -n 1 -r git branch -d</code></li><li>Mit dem -e Switch kann man verschiedene Pattern angeben.</li><li>Liste alle übrig gebliebenen lokalen git branches auf</li><li><code>git branch</code></li></ol><p><br></p>",
                now);

        Artikel artikel = new Artikel(applicationContext, artikelId);

        // when
        artikel.handleCommand(saveArtikel);

        // then
        Mockito.verify(artikelStore, times(1)).save(artikelEntityCaptor.capture());

        ArtikelEntity artikelEntity = artikelEntityCaptor.getValue();
        Assertions.assertThat(artikelEntity).isNotNull()
                .extracting(ArtikelEntity::getArtikelId, ArtikelEntity::getTitel, ArtikelEntity::getArtikelInhalt, ArtikelEntity::getErstellungsTS)
                .contains(saveArtikel.getArtikelId().id, saveArtikel.getTitel(), saveArtikel.getArtikelInhalt(), saveArtikel.getTimestamp());


    }


    @Test
    void getArtikelById() {
        // given
        ArtikelId artikelId = new ArtikelId();
        Artikel artikel = new Artikel(applicationContext, artikelId);
        GetArtikelByIDQuery getArtikelByIDQuery = GetArtikelByIDQuery.eventOf(artikelId);

        ArtikelEntity artikelEntity = new ArtikelEntity();
        artikelEntity.setArtikelId(artikelId.id);
        artikelEntity.setTitel("Titel");
        artikelEntity.setArtikelInhalt("Inhalt");
        artikelEntity.setErstellungsTS(LocalDateTime.now());

        when(artikelStore.findByArtikelId(any(String.class))).thenReturn(Optional.of(artikelEntity));

        // when
        Either<QueryFailure, Event> event = artikel.handleQuery(getArtikelByIDQuery);


        // then
        Assertions.assertThat(event.isRight()).isTrue();

        ArtikelEvent artikelEventFromRepo = (ArtikelEvent) event.get();
        Assertions.assertThat(artikelEventFromRepo).isNotNull();

    }

}
