package Team.Propose.model;

import Team.Propose.util.ServerInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.sql.*;

@Slf4j
@Controller
public class PlayerDaoImpl implements PlayerDao{

    private DataSource ds;
    private static PlayerDaoImpl dao = new PlayerDaoImpl();

    private PlayerDaoImpl() {
        try {
            Class.forName(ServerInfo.DRIVER_NAME);
        } catch(ClassNotFoundException e){
            log.info("error={}", e);
        }
    }

    public static PlayerDaoImpl getInstance() {
        return dao;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
    }

    public void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
        if(ps != null) ps.close();
        if(conn != null) conn.close();
        return;
    }

    public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
        if (rs != null) rs.close();
        closeAll(ps, conn);
        return;
    }

    @Override
    public Player getPlayer(String playerName) throws SQLException {

        Player ret = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            log.info("1");
            conn = getConnection();
            log.info("2");
            String query = "SELECT * from player where name=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, playerName);

            rs = ps.executeQuery();
            if(rs.next())
                ret = new Player(rs.getString(2), rs.getString(3));
        } catch(Exception e){
            log.info("error={}", e);
        } finally {
            closeAll(rs, ps, conn);
        }

        return ret;
    }
}
