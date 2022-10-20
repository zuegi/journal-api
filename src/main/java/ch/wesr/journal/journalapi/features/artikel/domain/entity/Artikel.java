package ch.wesr.journal.journalapi.features.artikel.domain.entity;

import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikel;
import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikelHandler;
import ch.wesr.journal.journalapi.features.artikel.domain.command.impl.SaveArtikelHandlerImpl;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import ch.wesr.journal.journalapi.shared.AggregateRoot;
import org.springframework.context.ApplicationContext;

public class Artikel extends AggregateRoot<Artikel, ArtikelId> {

    ApplicationContext applicationContext;

    public Artikel(ApplicationContext applicationContext, ArtikelId entityId) {
        super(applicationContext, entityId);
        this.applicationContext = applicationContext;
    }

    @Override
    protected AggregateRootBehavior<ArtikelId> initialBehavior() {
        AggregateRootBehaviorBuilder<ArtikelId> behaviorBuilder = new AggregateRootBehaviorBuilder<>();
        behaviorBuilder.setCommandHandler(SaveArtikel.class, new SaveArtikelHandlerImpl(applicationContext));
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
