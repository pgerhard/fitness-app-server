package de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1;

import de.university.reutlingen.mobile.computing.fitnessappserver.boundary.PlanManagementService;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.Plan;
import de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model.PlanDto;
import de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1.model.PlanReferenceDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Endpoint for plans.
 */
@RestController
@RequestMapping ( path = "/api/v1/plans" )
public class PlanControllerV1 {

    private final PlanManagementService delegate;

    /**
     * Constructor for required beans.
     * @param delegate that this controller delegates its actions to
     */
    public PlanControllerV1 ( PlanManagementService delegate ) {
        this.delegate = delegate;
    }

    /**
     * Get references to all plans.
     * @return the list of all plans
     */
    @GetMapping
    public Flux<PlanReferenceDto> loadAll () {
        return delegate.findAll ();
    }

    /**
     * Get a single plan.
      * @param identifier of plan to load
     * @return the plan
     */
    @GetMapping (path = "/{identifier}")
    public Mono<PlanDto> loadOneByIdentifier( @PathVariable ("identifier") String identifier) {
        return delegate.findOneByIdentifier ( identifier );
    }
}
