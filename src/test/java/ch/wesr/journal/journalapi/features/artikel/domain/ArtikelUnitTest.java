package ch.wesr.journal.journalapi.features.artikel.domain;

import ch.wesr.journal.journalapi.features.artikel.domain.entity.ArtikelEntity;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ArtikelUnitTest {


    @Test
    void create_artikel() {
        // given
        ArtikelId artikelId = new ArtikelId();
        String titel = "titel";
        String inhalt = "inhalt";
        LocalDateTime now = LocalDateTime.now();

        // when
        Artikel artikel = Artikel.create(artikelId, titel, inhalt, now);

        // then
        Assertions.assertThat(artikel).isInstanceOf(Artikel.class).isNotNull()
                .extracting(Artikel::getArtikelId)
                .isInstanceOf(ArtikelId.class)
                .isNotNull()
                .isSameAs(artikelId);

        // then
        Assertions.assertThat(artikel.getArtikelEntity()).isInstanceOf(ArtikelEntity.class).isNotNull()
                .extracting(ArtikelEntity::getArtikelId, ArtikelEntity::getTitel, ArtikelEntity::getArtikelInhalt, ArtikelEntity::getErstellungsTS, ArtikelEntity::getModifikationTS)
                .contains(artikelId.id, titel, inhalt,now, null);

    }

    @Test
    void load_artikel() {
        // given
        ArtikelId artikelId = new ArtikelId();
        LocalDateTime now = LocalDateTime.now();
        ArtikelEntity artikelEntity = new ArtikelEntity();
        artikelEntity.setArtikelId(artikelId.id);
        artikelEntity.setTitel("titel");
        artikelEntity.setArtikelInhalt("inhalt");
        artikelEntity.setErstellungsTS(now);
        artikelEntity.setModifikationTS(null);
        // when
        Artikel artikel = Artikel.load(artikelEntity);

        // then
        Assertions.assertThat(artikel).isNotNull()
                .isInstanceOf(Artikel.class)
                .extracting(Artikel::getArtikelId)
                .isInstanceOf(ArtikelId.class)
                .isNotNull()
                .extracting(id -> id.id)
                .isEqualTo(artikelId.id);

        Assertions.assertThat(artikel.getArtikelEntity()).isNotNull()
                .isInstanceOf(ArtikelEntity.class)
                .isEqualTo(artikelEntity);

    }

    @Test
    void update_titel() {
        // given
        ArtikelId artikelId = new ArtikelId();
        String titel = "titel";
        String inhalt = "inhalt";
        LocalDateTime now = LocalDateTime.now();

        Artikel artikel = Artikel.create(artikelId, titel, inhalt, now);
        String modifizierterTitel = "Das ist ein  modifizierter Titel";

        // when
        Artikel updatedTitelArtikel = artikel.updateTitel(modifizierterTitel);

        // then
        Assertions.assertThat(artikel).isSameAs(updatedTitelArtikel);
        Assertions.assertThat(artikel.getArtikelEntity()).isInstanceOf(ArtikelEntity.class).isNotNull()
                .extracting(ArtikelEntity::getArtikelId, ArtikelEntity::getTitel,
                        ArtikelEntity::getArtikelInhalt, ArtikelEntity::getErstellungsTS,
                        artikelEntity -> artikelEntity.getModifikationTS().isAfter(now))
                .contains(artikelId.id, modifizierterTitel, inhalt,now, true);
    }
}
