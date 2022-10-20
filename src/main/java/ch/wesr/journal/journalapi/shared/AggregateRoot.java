package ch.wesr.journal.journalapi.shared;

import io.vavr.control.Either;
import org.springframework.context.ApplicationContext;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class AggregateRoot <E, ID extends Serializable> implements Entity<E, ID> {

    public final ID entityId;
    private final ApplicationContext applicationContext;

    private final AggregateRootBehavior<ID> behavior;

    protected AggregateRoot(ApplicationContext applicationContext, ID entityId) {
        this.entityId = entityId;
        this.applicationContext = applicationContext;
        this.behavior = initialBehavior();
    }

    protected abstract AggregateRootBehavior<ID> initialBehavior();


    @SuppressWarnings("unchecked")
    public <A extends Command, B extends Event> Either<CommandFailure, B> handle(A command) {
        CommandHandler<A, B, ID> commandHandler = (CommandHandler<A, B, ID>) behavior.handlers.get(command.getClass());
        return commandHandler.handle(command, entityId);
    }

    public <A extends Command, B extends Event> CommandHandler<A, B, ID> getHandler(Class<? extends CommandHandler<A, B, ID>> commandHandlerClass) {
        // get the beans in spring boot applicationContext
        return applicationContext.getBean(commandHandlerClass);
    }


    public static class AggregateRootBehavior<ID> {

        protected final Map<Class<? extends Command>, CommandHandler<? extends Command, ? extends Event, ID>> handlers;

        public AggregateRootBehavior(Map<Class<? extends Command>, CommandHandler<? extends Command, ? extends Event, ID>> handlers) {
            this.handlers = Collections.unmodifiableMap(handlers);
        }
    }

    public static class AggregateRootBehaviorBuilder<ID> {

        private final Map<Class<? extends Command>, CommandHandler<? extends Command, ? extends Event, ID>> handlers = new HashMap<>();

        public <A extends Command, B extends Event> void setCommandHandler(Class<A> commandClass, CommandHandler<A, B, ID> handler) {
            handlers.put(commandClass, handler);
        }

        public AggregateRootBehavior<ID> build() {
            return new AggregateRootBehavior<>(handlers);
        }
    }
}
