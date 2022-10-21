package ch.wesr.journal.journalapi.shared;

import java.util.Collections;
import java.util.Map;

public class AggregateRootBehavior<ID> {

    protected final Map<Class<? extends Command>, CommandHandler<? extends Command, ? extends Event, ID>> commandHandlers;
    protected final Map<Class<? extends Query>, QueryHandler<? extends Query, ? extends Event, ID>> queryHandlers;

    public AggregateRootBehavior(Map<Class<? extends Command>, CommandHandler<? extends Command, ? extends Event, ID>> commandHandlers,
                                 Map<Class<? extends Query>, QueryHandler<? extends Query, ? extends Event, ID>> queryHandlers) {
        this.commandHandlers = Collections.unmodifiableMap(commandHandlers);
        this.queryHandlers = Collections.unmodifiableMap(queryHandlers);
    }

}
