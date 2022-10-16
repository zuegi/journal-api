package ch.wesr.journal.journalapi.features.artikel.infrastructure.rest.controller;


import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikel;
import ch.wesr.journal.journalapi.features.artikel.domain.event.SaveArtikelRequested;
import ch.wesr.journal.journalapi.features.artikel.domain.service.ArtikelService;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import ch.wesr.journal.journalapi.features.artikel.infrastructure.rest.model.ArtikelRequest;
import ch.wesr.journal.journalapi.features.artikel.infrastructure.rest.model.ArtikelResponse;
import ch.wesr.journal.journalapi.shared.CommandFailure;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/artikel", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArtikelController {

    private ArtikelService artikelService;

    @PostMapping
    public ResponseEntity<ArtikelResponse> createVertraulichkeitsbereich(@RequestBody @Valid ArtikelRequest artikelRequest) {
        log.info("create artikel {} ", artikelRequest.toString());

        SaveArtikel saveArtikel = SaveArtikel.commandOf(new ArtikelId(), LocalDateTime.now());

        // und was mit dem CommandFailure
        Either<CommandFailure, SaveArtikelRequested> commandFailure = artikelService.perform(saveArtikel);

        return ResponseEntity.accepted().body(new ArtikelResponse());
    }


}
