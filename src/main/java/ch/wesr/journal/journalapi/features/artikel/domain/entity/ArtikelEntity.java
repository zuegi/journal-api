package ch.wesr.journal.journalapi.features.artikel.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class ArtikelEntity {

    @Id
    private String artikelId;

    private String titel;

    @Lob
    private String artikelInhalt;

    private LocalDateTime erstellungsTS;

    private LocalDateTime modifikationTS;
}
