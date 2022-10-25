package ch.wesr.journal.journalapi.features.artikel.domain;

import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikel;
import ch.wesr.journal.journalapi.features.artikel.domain.entity.ArtikelEntity;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Artikel {

    ArtikelId artikelId;
    ArtikelEntity artikelEntity;

    Author author;

    private Artikel(ArtikelId artikelId, ArtikelEntity artikelEntity) {
        this.artikelId = artikelId;
        this.artikelEntity = artikelEntity;
    }

    // Könnte man an dieser Stelle nicht auch den SaveArtikel (Command) übergeben?
    public static Artikel create(ArtikelId artikelId, String titel, String inhalt, LocalDateTime now) {
        ArtikelEntity artikelEntity = new ArtikelEntity();
        artikelEntity.setArtikelId(artikelId.id);
        artikelEntity.setTitel(titel);
        artikelEntity.setArtikelInhalt(inhalt);
        artikelEntity.setErstellungsTS(now);

        Artikel artikel =  new Artikel(artikelId, artikelEntity);

        artikel.validate();
        return artikel;
    }

    public static Artikel load(ArtikelEntity artikelEntity) {
        Artikel artikel =  new Artikel(new ArtikelId(artikelEntity.getArtikelId()), artikelEntity);
        artikel.validate();
        return artikel;
    }


    public Artikel updateTitel(String titel) {
        ArtikelEntity entity = this.getArtikelEntity();
        entity.setTitel(titel);
        entity.setModifikationTS(LocalDateTime.now());
        this.validate();
        return this;
    }

    public Artikel updateInhalt(String inhalt) {
        ArtikelEntity entity = this.getArtikelEntity();
        entity.setArtikelInhalt(inhalt);
        entity.setModifikationTS(LocalDateTime.now());
        this.validate();
        return this;
    }

    public void addAuthor(Author author) {
       this.author = author;
       this.validate();
    }

    private void validate() {
        // TODO was immer hier zu validieren ist
    }
}
