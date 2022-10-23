package ch.wesr.journal.journalapi.features.artikel.infrastructure.rest.controller;


import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikel;
import ch.wesr.journal.journalapi.features.artikel.domain.event.SaveArtikelRequested;
import ch.wesr.journal.journalapi.features.artikel.domain.query.GetArtikelByIDQuery;
import ch.wesr.journal.journalapi.features.artikel.application.ArtikelService;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import ch.wesr.journal.journalapi.features.artikel.infrastructure.rest.model.ArtikelRequest;
import ch.wesr.journal.journalapi.features.artikel.infrastructure.rest.model.ArtikelResponse;
import ch.wesr.journal.journalapi.shared.CommandFailure;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/artikel", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArtikelController {

    private ArtikelService artikelService;

    @PostMapping
    public ResponseEntity<ArtikelResponse> createArtikel(@RequestBody @Valid ArtikelRequest artikelRequest) {
        log.info("create artikel {} ", artikelRequest.toString());

        SaveArtikel saveArtikel = SaveArtikel.commandOf(new ArtikelId(), artikelRequest.getTitel(), artikelRequest.getTitel(), artikelRequest.getErstellungsDatum());

        Either<CommandFailure, SaveArtikelRequested> commandFailure = artikelService.perform(saveArtikel);
        // FIXME commandFailure in ArtikelResponse
        return ResponseEntity.accepted().body(new ArtikelResponse());
    }

    @GetMapping("{aggregateId}")
    public ResponseEntity<ArtikelResponse> getArtikelByAggregateId(@PathVariable String aggregateId) {
        log.info("get artikel with aggregateId {}", aggregateId);
        final var result = artikelService.perform(GetArtikelByIDQuery.eventOf(new ArtikelId(aggregateId)));

        log.info("Get Artikel result: {}", result);

        // FIXME commandFailure in ArtikelResponse ohne new mit staticContructor = valueOf oder so
        return ResponseEntity.ok(new ArtikelResponse());
    }


}
