package ch.wesr.journal.journalapi.features.artikel.domain.vo;

import ch.wesr.journal.journalapi.shared.RandomUUID;

public class ArtikelEventId  extends RandomUUID {
    @Override
    protected String getPrefix() {
        return "AEI-%s";
    }
}
