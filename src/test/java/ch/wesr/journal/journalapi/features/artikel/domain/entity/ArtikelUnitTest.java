package ch.wesr.journal.journalapi.features.artikel.domain.entity;

import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikel;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;

@SpringBootTest
class ArtikelUnitTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void saveArtikel() {

        ArtikelId artikelId = new ArtikelId();

        SaveArtikel saveArtikel = SaveArtikel.commandOf(artikelId,
                "Git - entferne lokale Branches",
                "<h1>Git - entferne lokale Branches</h1><p><br></p><p>Entferne lokale git branches, welche schon merged sind.</p><p><br></p><ol><li>Öffne eine Shell</li><li>Fetch den letzten Stand von git</li><li><code>git fetch</code></li><li>Liste alle lokalen git branches auf</li><li><code>git branch</code></li><li>Lösche alle lokalen git branches, welche in den main branch merged wurden.</li><li><code>git branch --merged main | grep -v -e 'main' -e 'release' | xargs -n 1 -r git branch -d</code></li><li>Mit dem -e Switch kann man verschiedene Pattern angeben.</li><li>Liste alle übrig gebliebenen lokalen git branches auf</li><li><code>git branch</code></li></ol><p><br></p>",
                LocalDateTime.now());

        Artikel artikel = new Artikel(applicationContext, new ArtikelId());

        artikel.handle(saveArtikel);

        System.out.println("Test vorbei");
    }

}
