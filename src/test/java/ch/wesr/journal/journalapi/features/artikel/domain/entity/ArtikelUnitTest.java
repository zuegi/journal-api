package ch.wesr.journal.journalapi.features.artikel.domain.entity;

import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikel;
import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikelHandler;
import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikelValidator;
import ch.wesr.journal.journalapi.features.artikel.domain.command.impl.SaveArtikelHandlerImpl;
import ch.wesr.journal.journalapi.features.artikel.domain.command.impl.SaveArtikelValidatorImpl;
import ch.wesr.journal.journalapi.features.artikel.domain.event.SaveArtikelRequested;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import ch.wesr.journal.journalapi.features.artikel.infrastructure.repository.ArtikelEventRepositoryImpl;
import ch.wesr.journal.journalapi.features.artikel.infrastructure.repository.ArtikelStore;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

//@SpringBootTest
class ArtikelUnitTest {

    ApplicationContext applicationContext = Mockito.mock(ApplicationContext.class);
    ArtikelEventRepository artikelEventRepository;

    ArtikelStore artikelStore = Mockito.mock(ArtikelStore.class);

    // FIXME Handler und Validator müssen keine Interfaces haben
    SaveArtikelHandler saveArtikelHandler;
    SaveArtikelValidator saveArtikelValidator;

    ArgumentCaptor<ArtikelEntity> artikelEntityCaptor;

    @BeforeEach
    void init() throws IllegalAccessException {
        saveArtikelHandler = new SaveArtikelHandlerImpl(applicationContext);
        saveArtikelValidator = new SaveArtikelValidatorImpl();
        artikelEventRepository = new ArtikelEventRepositoryImpl();

        FieldUtils.writeField(saveArtikelHandler, "saveArtikelValidator", saveArtikelValidator, true);
        FieldUtils.writeField(saveArtikelHandler, "artikelEventRepository", artikelEventRepository, true);
        FieldUtils.writeField(artikelEventRepository, "artikelStore", artikelStore, true);

        when(applicationContext.getBean(SaveArtikelHandler.class)).thenReturn(saveArtikelHandler);

        artikelEntityCaptor = ArgumentCaptor.forClass(ArtikelEntity.class);
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
        artikel.handle(saveArtikel);

        // then
        Mockito.verify(artikelStore, times(1)).save(artikelEntityCaptor.capture());

        ArtikelEntity artikelEntity = artikelEntityCaptor.getValue();
        Assertions.assertThat(artikelEntity).isNotNull()
                .extracting(ArtikelEntity::getArtikelId, ArtikelEntity::getTitel, ArtikelEntity::getArtikelInhalt, ArtikelEntity::getErstellungsTS)
                .contains(saveArtikel.getArtikelId().toString(), saveArtikel.getTitel(), saveArtikel.getArtikelInhalt(), saveArtikel.getTimestamp());


    }

}
