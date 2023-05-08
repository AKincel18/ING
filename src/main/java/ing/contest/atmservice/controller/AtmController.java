package ing.contest.atmservice.controller;

import ing.contest.atmservice.model.Atm;
import ing.contest.atmservice.model.ServiceTask;
import ing.contest.atmservice.service.AtmService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/atms/calculateOrder")
@Validated
public class AtmController {

    private final AtmService atmService;

    public AtmController(AtmService atmService) {
        this.atmService = atmService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Atm>> calculateOrder(@RequestBody @Valid List<ServiceTask> serviceTasks) {
        List<Atm> result = atmService.calculateOrder(serviceTasks);
        return ResponseEntity.ok(result);
    }
}
