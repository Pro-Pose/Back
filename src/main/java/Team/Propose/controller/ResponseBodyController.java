package Team.Propose.controller;

import Team.Propose.model.Player;
import Team.Propose.model.PlayerDaoImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@Slf4j
@RestController
public class ResponseBodyController {

    @GetMapping("/players")
    public ResponseEntity<Player> responsePlayerInfo(@RequestParam String playerName) throws SQLException {

        Player player = PlayerDaoImpl.getInstance().getPlayer(playerName);

        return new ResponseEntity<>(player, HttpStatus.OK);
    }

}
