package Team.Propose.model;

import java.sql.SQLException;

public interface PlayerDao {

    Player getPlayer(String playerName) throws SQLException;
}
