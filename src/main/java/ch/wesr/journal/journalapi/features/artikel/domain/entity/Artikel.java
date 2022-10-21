package ch.wesr.journal.journalapi.features.artikel.domain.entity;

import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikel;
import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikelHandler;
import ch.wesr.journal.journalapi.features.artikel.domain.query.GetArtikelByIDQuery;
import ch.wesr.journal.journalapi.features.artikel.domain.query.GetArtikelByIdQueryHandler;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import ch.wesr.journal.journalapi.shared.AggregateRoot;
import ch.wesr.journal.journalapi.shared.AggregateRootBehavior;
import ch.wesr.journal.journalapi.shared.AggregateRootBehaviorBuilder;
import org.springframework.context.ApplicationContext;

public class Artikel extends AggregateRoot<Artikel, ArtikelId> {

    public Artikel(ApplicationContext applicationContext, ArtikelId entityId) {
        super(applicationContext, entityId);
    }

    @Override
    protected AggregateRootBehavior<ArtikelId> initialBehavior() {
        AggregateRootBehaviorBuilder<ArtikelId> behaviorBuilder = new AggregateRootBehaviorBuilder<>();
        behaviorBuilder.setCommandHandler(SaveArtikel.class, getCommandHandler(SaveArtikelHandler.class));
        behaviorBuilder.setQueryHandler(GetArtikelByIDQuery.class, getQueryHandler(GetArtikelByIdQueryHandler.class));
        return behaviorBuilder.build();
    }

    @Override
    public boolean sameIdentityAs(Artikel other) {
        return other != null && entityId.sameValueAs(other.entityId);
    }

    @Override
    public ArtikelId id() {
        return entityId;
    }

}
