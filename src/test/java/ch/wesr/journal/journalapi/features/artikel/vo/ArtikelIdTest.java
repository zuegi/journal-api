package ch.wesr.journal.journalapi.features.artikel.vo;

import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ArtikelIdTest {

    @Test
    void notSameId() {
        ArtikelId artikelId = new ArtikelId();
        ArtikelId anotherArtikelId = new ArtikelId();
        Assertions.assertThat(artikelId.sameValueAs(anotherArtikelId)).isFalse();
    }

    @Test
    void sameId() {
        ArtikelId artikelId = new ArtikelId();
        ArtikelId sameArtikelId = artikelId;

        Assertions.assertThat(artikelId.sameValueAs(sameArtikelId)).isTrue();
    }

}
