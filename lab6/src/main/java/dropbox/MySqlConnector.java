package dropbox;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Michal on 2016-01-23.
 */
public class MySqlConnector {
    public Connection conn = null;

    public MySqlConnector() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/crawler";
            conn = DriverManager.getConnection(url, "root", "");
            System.out.println("conn built");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getLinks(String table) throws SQLException {
        ResultSet rs = runExecuteQuery("SELECT * FROM " + table);
        ArrayList<String> result = new ArrayList<>();
        while(rs.next()) {
            result.add(rs.getString("link"));
        }
        return result;
    }

    public ResultSet runExecuteQuery(String sql) throws SQLException {
        Statement sta = conn.createStatement();
        return sta.executeQuery(sql);
    }

    public ResultSet selectWhereLink(String table, String url) throws SQLException {
        Statement sta = conn.createStatement();
        return sta.executeQuery( "SELECT * FROM " + table + " where link = '" + url + "'");
    }

    public String generateSqlStringInsertOneValue(String baseAndTable, String column)
    {
        return "INSERT INTO  " + baseAndTable + "("+column+") VALUES " + "(?);";
    }

    public void truncateDB() throws SQLException {
        runExecute("TRUNCATE links;");
    }

    public boolean runExecute(String sql) throws SQLException {
        Statement sta = conn.createStatement();
        return sta.execute(sql);
    }

    @Override
    protected void finalize() throws Throwable {
        if (conn != null || !conn.isClosed()) {
            conn.close();
        }
    }
}
