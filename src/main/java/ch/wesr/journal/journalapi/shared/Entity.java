package ch.wesr.journal.journalapi.shared;

import java.io.Serializable;

public interface Entity<E, ID extends Serializable> {
    /**
     * Entities compare by identity, not by attributes.
     *
     * @param other The other entity.
     * @return true if the identities are the same, regardles of other attributes.
     */
    boolean sameIdentityAs(E other);

    ID id();
}
