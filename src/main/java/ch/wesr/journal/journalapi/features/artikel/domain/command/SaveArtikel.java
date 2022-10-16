package ch.wesr.journal.journalapi.features.artikel.domain.command;

import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import lombok.Value;

import java.time.LocalDateTime;

@Value(staticConstructor = "commandOf")
public class SaveArtikel implements ArtikelCommand {
    ArtikelId artikelId;
    String titel;
    String artikelInhalt;
    LocalDateTime timestamp;
}
