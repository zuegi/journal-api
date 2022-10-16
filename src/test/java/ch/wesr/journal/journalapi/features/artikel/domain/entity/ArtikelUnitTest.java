package ch.wesr.journal.journalapi.features.artikel.domain.entity;

import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikel;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArtikelUnitTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void saveArtikel() {

        ArtikelId artikelId = new ArtikelId();

        SaveArtikel saveArtikel = SaveArtikel.commandOf(artikelId, LocalDateTime.now());

        Artikel artikel = new Artikel(applicationContext, new ArtikelId());

        artikel.handle(saveArtikel);
    }

}
