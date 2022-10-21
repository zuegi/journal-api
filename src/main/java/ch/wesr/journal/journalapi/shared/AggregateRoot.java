package ch.wesr.journal.journalapi.shared;

import io.vavr.control.Either;
import org.springframework.context.ApplicationContext;

import java.io.Serializable;

public abstract class AggregateRoot<E, ID extends Serializable> implements Entity<E, ID> {

    public final ID entityId;
    private final ApplicationContext applicationContext;

    private final AggregateRootBehavior<ID> behavior;

    protected AggregateRoot(ApplicationContext applicationContext, ID entityId) {
        this.entityId = entityId;
        this.applicationContext = applicationContext;
        this.behavior = initialBehavior();
    }

    protected abstract AggregateRootBehavior<ID> initialBehavior();

    /*
        Command Section
     */
    @SuppressWarnings("unchecked")
    public <A extends Command, B extends Event> Either<CommandFailure, B> handleCommand(A command) {
        CommandHandler<A, B, ID> commandHandler = (CommandHandler<A, B, ID>) behavior.commandHandlers.get(command.getClass());
        return commandHandler.handle(command, entityId);
    }

    public <A extends Command, B extends Event> CommandHandler<A, B, ID> getCommandHandler(Class<? extends CommandHandler<A, B, ID>> commandHandlerClass) {
        // get the beans in spring boot applicationContext
        return applicationContext.getBean(commandHandlerClass);
    }


    /*
        Query Section
     */
    @SuppressWarnings("unchecked")
    public <A extends Query, B extends Event> Either<QueryFailure, B> handleQuery(A query) {
        QueryHandler<A, B, ID> queryHandler = (QueryHandler<A, B, ID>) behavior.queryHandlers.get(query.getClass());
        return queryHandler.handle(query, entityId);
    }

    public <A extends Query, B extends Event> QueryHandler<A, B, ID> getQueryHandler(Class<? extends QueryHandler<A, B, ID>> queryHandlerClass) {
        // get the beans in spring boot applicationContext
        return applicationContext.getBean(queryHandlerClass);
    }


}
