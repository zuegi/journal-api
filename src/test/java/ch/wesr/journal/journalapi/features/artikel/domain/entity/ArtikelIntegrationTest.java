package ch.wesr.journal.journalapi.features.artikel.domain.entity;

import ch.wesr.journal.journalapi.features.artikel.domain.Artikel;
import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikel;
import ch.wesr.journal.journalapi.features.artikel.domain.event.ArtikelEvent;
import ch.wesr.journal.journalapi.features.artikel.domain.event.GetAllArtikelRequested;
import ch.wesr.journal.journalapi.features.artikel.domain.event.GetArtikekelByIdRequested;
import ch.wesr.journal.journalapi.features.artikel.domain.event.SaveArtikelRequested;
import ch.wesr.journal.journalapi.features.artikel.domain.query.GetAlleArtikelQuery;
import ch.wesr.journal.journalapi.features.artikel.domain.query.GetArtikelByIDQuery;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import ch.wesr.journal.journalapi.shared.CommandFailure;
import ch.wesr.journal.journalapi.shared.Event;
import ch.wesr.journal.journalapi.shared.QueryFailure;
import io.vavr.control.Either;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.mock;

@SpringBootTest
class ArtikelIntegrationTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void saveArtikel() {
        // given
        ArtikelId artikelId = new ArtikelId();

        SaveArtikel saveArtikel = SaveArtikel.commandOf(artikelId,
                "Git - entferne lokale Branches",
                "<h1>Git - entferne lokale Branches</h1><p><br></p><p>Entferne lokale git branches, welche schon merged sind.</p><p><br></p><ol><li>Öffne eine Shell</li><li>Fetch den letzten Stand von git</li><li><code>git fetch</code></li><li>Liste alle lokalen git branches auf</li><li><code>git branch</code></li><li>Lösche alle lokalen git branches, welche in den main branch merged wurden.</li><li><code>git branch --merged main | grep -v -e 'main' -e 'release' | xargs -n 1 -r git branch -d</code></li><li>Mit dem -e Switch kann man verschiedene Pattern angeben.</li><li>Liste alle übrig gebliebenen lokalen git branches auf</li><li><code>git branch</code></li></ol><p><br></p>",
                LocalDateTime.now());

        Artikel artikel = new Artikel(applicationContext, artikelId);

        Either<CommandFailure, Event> events = artikel.handleCommand(saveArtikel);
        // assert das ein Event zurueck kommt
        Assertions.assertThat(events.isRight()).isTrue();

        SaveArtikelRequested event = (SaveArtikelRequested) events.get();

        Assertions.assertThat(event)
                .isNotNull()
                .extracting(SaveArtikelRequested::getArtikelId, SaveArtikelRequested::getTitel, SaveArtikelRequested::getArtikelInhalt, SaveArtikelRequested::getTimestamp)
                .contains(saveArtikel.getArtikelId(), saveArtikel.getTitel(), saveArtikel.getArtikelInhalt(), saveArtikel.getTimestamp());

    }

    @Test
    void getArtikelByArtikelId() {
        SaveArtikel saveArtikel = SaveArtikel.commandOf(new ArtikelId(),
                "Git - entferne lokale Branches",
                "<h1>Git - entferne lokale Branches</h1><p><br></p><p>Entferne lokale git branches, welche schon merged sind.</p><p><br></p><ol><li>Öffne eine Shell</li><li>Fetch den letzten Stand von git</li><li><code>git fetch</code></li><li>Liste alle lokalen git branches auf</li><li><code>git branch</code></li><li>Lösche alle lokalen git branches, welche in den main branch merged wurden.</li><li><code>git branch --merged main | grep -v -e 'main' -e 'release' | xargs -n 1 -r git branch -d</code></li><li>Mit dem -e Switch kann man verschiedene Pattern angeben.</li><li>Liste alle übrig gebliebenen lokalen git branches auf</li><li><code>git branch</code></li></ol><p><br></p>",
                LocalDateTime.now());

        GetArtikelByIDQuery getArtikelByIDQuery = GetArtikelByIDQuery.eventOf(saveArtikel.getArtikelId());
        Artikel artikel = new Artikel(applicationContext, saveArtikel.getArtikelId());

        Either<CommandFailure, Event> events = artikel.handleCommand(saveArtikel);

        // then get from DB
        Either<QueryFailure, Event> either = artikel.handleQuery(getArtikelByIDQuery);

        Assertions.assertThat(either.isRight()).isTrue();

        GetArtikekelByIdRequested artikelEventFromDb = (GetArtikekelByIdRequested) either.get();

        Assertions.assertThat(artikelEventFromDb).isNotNull()
                .extracting(artikelEvent -> artikelEvent.getArtikelId().id, GetArtikekelByIdRequested::getTitel, GetArtikekelByIdRequested::getArtikelInhalt, GetArtikekelByIdRequested::getTimestamp)
                .contains(saveArtikel.getArtikelId().id, saveArtikel.getTitel(), saveArtikel.getArtikelInhalt(), saveArtikel.getTimestamp());


    }

    @Test
    void getAlleArtikel() {
        // save Events
        // given
        ArtikelId artikelId = new ArtikelId();
        SaveArtikel saveArtikel = SaveArtikel.commandOf(artikelId, "Titel", "Inhalt", LocalDateTime.now());
        Artikel artikel = new Artikel(applicationContext, artikelId);
        artikel.handleCommand(saveArtikel);

        ArtikelId artikelId1 = new ArtikelId();
        SaveArtikel saveArtikel1 = SaveArtikel.commandOf(artikelId1, "Titel", "Inhalt", LocalDateTime.now());
        Artikel artikel1 = new Artikel(applicationContext, artikelId1);
        artikel1.handleCommand(saveArtikel1);


        ArtikelId artikelId2 = new ArtikelId();
        SaveArtikel saveArtikel2 = SaveArtikel.commandOf(artikelId2, "Titel", "Inhalt", LocalDateTime.now());
        Artikel artikel2 = new Artikel(applicationContext, artikelId2);
        artikel2.handleCommand(saveArtikel2);


        Artikel artikel4 = new Artikel(applicationContext, null);
        Either<QueryFailure, Event> events = artikel4.handleQuery(new GetAlleArtikelQuery());

        // assert das ein Event zurueck kommt
        Assertions.assertThat(events.isRight()).isTrue();

        GetAllArtikelRequested getAllArtikelRequested = (GetAllArtikelRequested) events.get();

        Assertions.assertThat(getAllArtikelRequested.getGetArtikekelByIdRequestedList())
                .isNotNull().isNotEmpty()
                .hasSize(3);


    }

}
