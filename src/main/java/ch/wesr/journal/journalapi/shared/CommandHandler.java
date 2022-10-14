package ch.wesr.journal.journalapi.shared;

public interface CommandHandler<C extends Command, E extends Event, ID> {
    CommandFailure handle(C command, ID entityId);
}
