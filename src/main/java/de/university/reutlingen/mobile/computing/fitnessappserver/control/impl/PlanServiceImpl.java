package de.university.reutlingen.mobile.computing.fitnessappserver.control.impl;

import de.university.reutlingen.mobile.computing.fitnessappserver.control.PlanService;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.Plan;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.PlanRepository;
import de.university.reutlingen.mobile.computing.fitnessappserver.repository.parameter.PlanSearchParameter;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Example;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class PlanServiceImpl extends AbstractDocumentServiceImpl<Plan, ObjectId, PlanRepository> implements PlanService {

    private final PlanRepository planRepository;

    public PlanServiceImpl ( PlanRepository planRepository ) {
        this.planRepository = planRepository;
    }

    @Override
    public Flux<Plan> findAll () {
        return planRepository.findAll ();
    }

    @Override
    public Mono<Plan> findOneBySearchParameter ( PlanSearchParameter planSearchParameter ) {
        return planRepository.findOne ( Example.of ( buildProbe ( planSearchParameter ) ) ).doOnError ( Throwable::printStackTrace );
    }

    @Override
    public PlanRepository getRepository () {
        return this.planRepository;
    }

    private Plan buildProbe ( PlanSearchParameter planSearchParameter ) {
        Plan probe = new Plan ();

        if ( planSearchParameter.name != null ) {
            probe.setName ( planSearchParameter.name );
        }

        if ( planSearchParameter.identifier != null ) {
            probe.setIdentifier ( UUID.fromString ( planSearchParameter.identifier ) );
        }
        return probe;
    }

}
