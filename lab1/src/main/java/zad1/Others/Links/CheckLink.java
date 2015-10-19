package zad1.Others.Links;

import zad1.Parsers.Parser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

/**
 * Created by Michal on 2015-10-09.
 */
public class CheckLink {
    String link, domain;
    public CheckLink(String l, String d) throws MalformedURLException {
        link = l;
        domain = d;
        try {
            URL url = new URL(domain);
            domain = url.getHost();
        } catch (Exception ignored) {}
    }

    public boolean CheckDomainLink() {
        return link.contains(fix(domain)) || link.trim().charAt(0) == '/' || (link.trim().charAt(0) == '.' && link.trim().charAt(1) == '/');
    }

    public String fix(String dom) {
        if(dom.startsWith("www."))
            dom = dom.replace("www.", "");
        return dom;
    }

}
