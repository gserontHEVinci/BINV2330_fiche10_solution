package blacklist;

import domaine.Query;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Set;

public class BlacklistServiceImpl implements BlacklistService {

    private static Set<String> blacklistedDomains;

    static {
        try (FileInputStream in = new FileInputStream("blacklist.properties")) {
            Properties props = new Properties();
            props.load(in);
            blacklistedDomains = Set.of(props.getProperty("blacklistedDomains").split(";"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean check(Query query) {
        try {
            URL url = new URL(query.getUrl());
            String hostname = url.getHost();
            return blacklistedDomains.stream().noneMatch(d -> hostname.equals(d));
        } catch (MalformedURLException e) {
            return false;
        }
    }


}
