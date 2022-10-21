package ch.wesr.journal.journalapi.shared;

import io.vavr.control.Either;

public interface QueryHandler<C extends Query, E extends Event, ID> {
    Either<QueryFailure, E> handle(C query, ID entityId);
}
