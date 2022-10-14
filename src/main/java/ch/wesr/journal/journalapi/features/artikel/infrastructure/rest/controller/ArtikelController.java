package ch.wesr.journal.journalapi.features.artikel.infrastructure.rest.controller;


import ch.wesr.journal.journalapi.features.artikel.domain.command.PerformArtikel;
import ch.wesr.journal.journalapi.features.artikel.domain.service.ArtikelService;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import ch.wesr.journal.journalapi.features.artikel.infrastructure.rest.model.ArtikelRequest;
import ch.wesr.journal.journalapi.features.artikel.infrastructure.rest.model.ArtikelResponse;
import ch.wesr.journal.journalapi.shared.CommandFailure;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/artikel", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArtikelController {

    private ArtikelService artikelService;

    @PostMapping
    public ResponseEntity<ArtikelResponse> createVertraulichkeitsbereich(@RequestBody @Valid ArtikelRequest artikelRequest) {
        log.info("create artikel {} ", artikelRequest.toString());

        PerformArtikel performArtikel = PerformArtikel.commandOf(new ArtikelId());

        // und was mit dem CommandFailure
        CommandFailure commandFailure = artikelService.perform(performArtikel);

        return ResponseEntity.accepted().body(new ArtikelResponse());
    }


}
