package ch.wesr.journal.journalapi.shared;

import ch.wesr.journal.journalapi.features.artikel.domain.event.GetArtikekelByIdRequested;
import ch.wesr.journal.journalapi.features.artikel.domain.query.GetArtikelByIDQuery;

import java.util.HashMap;
import java.util.Map;

public class AggregateRootBehaviorBuilder<ID> {

    private final Map<Class<? extends Command>, CommandHandler<? extends Command, ? extends Event, ID>> commandHandlers = new HashMap<>();
    private final Map<Class<? extends Query>, QueryHandler<? extends Query, ? extends Event, ID>> queryHandlers = new HashMap<>();

    public AggregateRootBehavior<ID> build() {
        return new AggregateRootBehavior<>(commandHandlers, queryHandlers);
    }


    public <A extends Command, B extends Event> void setCommandHandler(Class<A> commandClass, CommandHandler<A, B, ID> handler) {
        commandHandlers.put(commandClass, handler);
    }


    public <A extends Query, B extends Event> void setQueryHandler(Class<A> queryClass, QueryHandler<A, B, ID> queryHandler) {
        queryHandlers.put(queryClass, queryHandler);
    }
}
