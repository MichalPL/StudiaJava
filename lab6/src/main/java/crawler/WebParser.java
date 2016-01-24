package crawler;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Michal on 2016-01-23.
 */
public class WebParser {
    private MySqlConnector mySqlConnector;
    private AtomicInteger counter;
    private AtomicInteger stats = new AtomicInteger(0);
    private ConfigManager configManager;
    private ArrayList<String> links;

    public WebParser(MySqlConnector mySqlConnector, ConfigManager configManager) throws SQLException {
        this.mySqlConnector = mySqlConnector;
        this.configManager = configManager;
        counter = new AtomicInteger(Integer.parseInt(configManager.getProperty(AppKeys.LINK_COUNT)) - 1); //*2*mySqlConnector.getLinks("base_links").size()
        links = new ArrayList<>();
    }

    public void parse(String word, String url) throws SQLException, IOException {
        ResultSet rs = mySqlConnector.selectWhereLink("links", url);
        if (!rs.next()) {
//            System.out.println(url + " dodano link, zostalo " + counter); //tylko dla programisty potrzebne
            links.add(url);
            counter.decrementAndGet();
            stats.incrementAndGet();

            String sql = mySqlConnector.generateSqlStringInsertOneValue("`crawler`.`links`", "link");
            PreparedStatement stmt = (PreparedStatement) mySqlConnector.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, url);
            stmt.execute();
            try {
                Document doc = Jsoup.connect(url).get();
                if (doc.text().contains(word)) {
                    Elements questions = doc.select("a[href]");
                    for (Element link : questions) {
                        if(counter.get() >= 0) {
                            parse(word, link.attr("abs:href"));
                        }
                    }
                }
            } catch (Exception ignored) {
                //link to zdjecie lub cos co nie jest strona itp NIEWAZNE
            }
        }
    }

    public ArrayList<String> getLinks() {
        return links;
    }

    public void clearArray() {
        links.clear();
    }

    public int getStats() {
        return stats.getAndSet(0);
    }
}
