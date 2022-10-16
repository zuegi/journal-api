package ch.wesr.journal.journalapi.features.artikel.infrastructure.rest.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArtikelRequest {
    String titel;
    String inhalt;
    LocalDateTime erstellungsDatum;
}
