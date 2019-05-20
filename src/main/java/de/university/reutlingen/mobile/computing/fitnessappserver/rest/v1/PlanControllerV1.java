package de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1;

import de.university.reutlingen.mobile.computing.fitnessappserver.boundary.PlanManagementService;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.Exercise;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.Plan;
import de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model.PlanReferenceDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping ( path = "/fitness-app/api/v1/plans" )
public class PlanControllerV1 {

    private final PlanManagementService delegate;

    public PlanControllerV1 ( PlanManagementService delegate ) {
        this.delegate = delegate;
    }

    @GetMapping
    public Flux<PlanReferenceDto> loadAll () {
        return delegate.findAll ();
    }

    @GetMapping (path = "/{identifier}")
    public Mono<Plan> loadOneByIdentifier( @PathVariable ("identifier") String identifier) {
        return delegate.findOneByIdentifier ( identifier );
    }
}
