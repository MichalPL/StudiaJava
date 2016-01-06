package main;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Michal on 2015-12-29.
 */
public class Model {


    Connection con = null;
    Statement stmt = null;
    StringToArrayParser stapraser;

    public Model() {
        stapraser = new StringToArrayParser();
    }
    public void createDB(){
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:test.db");

            stmt = con.createStatement();

            String sql = "CREATE TABLE questions " +
                    "(id INTEGER PRIMARY KEY     AUTOINCREMENT," +
                    " text           TEXT    NOT NULL, " +
                    " answers        TEXT     NOT NULL, " +
                    " correct        INT     NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void insertToDB(String q, ArrayList<String> l, int cor) throws SQLException, ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:test.db");

        stmt = con.createStatement();
        String a = stapraser.toStr(l);


        con.setAutoCommit(false);

        String sql = "INSERT INTO questions (text,answers,correct) " +
                "VALUES ('" + q + "','" + a + "','" + cor +"');";
        stmt.executeUpdate(sql);

        stmt.close();
        con.commit();
        con.close();
    }

    public Question getQuest(int id) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:test.db");

        stmt = con.createStatement();

        con.setAutoCommit(false);
        ResultSet rs = stmt.executeQuery( "SELECT * FROM questions WHERE id = '"+ id +"';" );
        String text = "";
        String answers = "";
        int correct = 0;
        while ( rs.next() ) {
            text = rs.getString("text");
            answers = rs.getString("answers");
            correct = rs.getInt("correct");
        }
        stmt.close();
        con.commit();
        con.close();
        return new Question(id, text, stapraser.toArr(answers), correct);
    }

    public int getCount() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:test.db");

        stmt = con.createStatement();
        con.setAutoCommit(false);
        ResultSet rs = stmt.executeQuery( "SELECT COUNT(*) FROM questions");
        int count = 0;
        while ( rs.next() ) {
            count = rs.getInt(1);
        }
        stmt.close();
        con.commit();
        con.close();
        return count;
    }


}
