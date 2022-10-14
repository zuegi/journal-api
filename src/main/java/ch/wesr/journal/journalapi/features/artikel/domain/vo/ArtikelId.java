package ch.wesr.journal.journalapi.features.artikel.domain.vo;

import ch.wesr.journal.journalapi.shared.RandomUUID;

public class ArtikelId extends RandomUUID {

    public ArtikelId() {
        super();
    }

    public ArtikelId(String id) {
        super(id);
    }

    @Override
    protected String getPrefix() {
        return "JNRL-%s";
    }
}
