package ch.wesr.journal.journalapi.features.artikel.domain.query;

import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import ch.wesr.journal.journalapi.shared.RandomUUID;
import lombok.Data;
import lombok.Value;

@Value(staticConstructor = "eventOf")
public class GetArtikelByIDQuery {
    ArtikelId artikelId;
}
