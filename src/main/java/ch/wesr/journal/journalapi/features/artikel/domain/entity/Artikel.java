package ch.wesr.journal.journalapi.features.artikel.domain.entity;

import ch.wesr.journal.journalapi.features.artikel.domain.command.PerformArtikel;
import ch.wesr.journal.journalapi.features.artikel.domain.command.SaveArtikelHandler;
import ch.wesr.journal.journalapi.features.artikel.domain.vo.ArtikelId;
import ch.wesr.journal.journalapi.shared.AggregateRoot;
import ch.wesr.journal.journalapi.shared.Command;
import org.springframework.context.ApplicationContext;

public class Artikel extends AggregateRoot<Artikel, ArtikelId> {

    public Artikel(ApplicationContext applicationContext, ArtikelId entityId) {
        super(applicationContext, entityId);
    }

    @Override
    protected AggregateRootBehavior initialBehavior() {
        AggregateRootBehaviorBuilder behaviorBuilder = new AggregateRootBehaviorBuilder();
        behaviorBuilder.setCommandHandler(PerformArtikel.class, getHandler(SaveArtikelHandler.class));
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
