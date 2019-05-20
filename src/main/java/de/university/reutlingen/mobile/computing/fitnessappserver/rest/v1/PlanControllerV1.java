package de.university.reutlingen.mobile.computing.fitnessappserver.rest.v1;

import de.university.reutlingen.mobile.computing.fitnessappserver.boundary.PlanManagementService;
import de.university.reutlingen.mobile.computing.fitnessappserver.model.Plan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping ( path = "/fitness-app/api/v1/plans" )
public class PlanControllerV1 {

    private final PlanManagementService delegate;

    public PlanControllerV1 ( PlanManagementService delegate ) {
        this.delegate = delegate;
    }


    @GetMapping
    public Flux<Plan> loadAll () {
        return delegate.findAll ();
    }
}
