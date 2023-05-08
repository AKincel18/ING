package ing.contest.onlinegame.controller;

import ing.contest.onlinegame.model.Clan;
import ing.contest.onlinegame.model.Players;
import ing.contest.onlinegame.service.OnlineGameService;
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
@RequestMapping("/onlinegame/calculate")
@Validated
public class OnlineGameController {

    private final OnlineGameService onlineGameService;

    public OnlineGameController(OnlineGameService onlineGameService) {
        this.onlineGameService = onlineGameService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<List<Clan>>> calculate(@RequestBody @Valid Players players) {
        List<List<Clan>> result = onlineGameService.calculate(players);
        return ResponseEntity.ok(result);
    }
}
