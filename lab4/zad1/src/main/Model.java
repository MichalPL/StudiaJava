package main;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Michal on 2015-12-29.
 */
public class Model {
    private Connection con = null;
    private Statement stmt = null;
    private StringToArrayParser stapraser;

    public Model() {
        stapraser = new StringToArrayParser();
    }

    private void getConnectionEtc(boolean autoCommit) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:test.db");
        stmt = con.createStatement();
        if(!autoCommit) con.setAutoCommit(false);
    }

    private void closeConnectionEtc(String sql, boolean executeUpdate) throws SQLException {
        if(stmt == null || con == null) return;
        if(executeUpdate) stmt.executeUpdate(sql);
        stmt.close();
        con.commit();
        con.close();
    }

    public void createDB(){
        try {
            getConnectionEtc(true);
            String sql = "CREATE TABLE questions " +
                    "(id INTEGER PRIMARY KEY     AUTOINCREMENT," +
                    " text           TEXT    NOT NULL, " +
                    " answers        TEXT     NOT NULL, " +
                    " correct        INT     NOT NULL)";
            closeConnectionEtc(sql, false);

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void insertToDB(String q, ArrayList<String> l, int cor) throws SQLException, ClassNotFoundException {
        getConnectionEtc(false);
        String a = stapraser.toStr(l);
        String sql = "INSERT INTO questions (text,answers,correct) " +
                "VALUES ('" + q + "','" + a + "','" + cor +"');";
        closeConnectionEtc(sql, true);
    }

    public Question getQuest(int id) throws SQLException, ClassNotFoundException {
        getConnectionEtc(false);
        ResultSet rs = stmt.executeQuery( "SELECT * FROM questions WHERE id = '"+ id +"';" );
        String text = "";
        String answers = "";
        int correct = 0;
        while ( rs.next() ) {
            text = rs.getString("text");
            answers = rs.getString("answers");
            correct = rs.getInt("correct");
        }
        closeConnectionEtc("", false);
        return new Question(id, text, stapraser.toArr(answers), correct);
    }

    public int getCount() throws SQLException, ClassNotFoundException {
        getConnectionEtc(false);
        ResultSet rs = stmt.executeQuery( "SELECT COUNT(*) FROM questions");
        int count = 0;
        while ( rs.next() ) {
            count = rs.getInt(1);
        }
        closeConnectionEtc("", false);
        return count;
    }


}
