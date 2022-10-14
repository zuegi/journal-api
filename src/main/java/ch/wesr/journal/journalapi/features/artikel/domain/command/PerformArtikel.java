package ch.wesr.journal.journalapi.features.artikel.domain.command;

import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import lombok.Value;

@Value(staticConstructor = "commandOf")
public class PerformArtikel implements ArtikelCommand {
    ArtikelId artikelId;
}
