package server;

import blacklist.BlacklistService;
import domaine.Query;
import domaine.Query.QueryMethod;
import domaine.QueryFactory;

import java.net.http.HttpClient;
import java.util.Scanner;

public class ProxyServer {

    private QueryFactory queryFactory;
    private BlacklistService blacklistService;

    public ProxyServer(QueryFactory queryFactory, BlacklistService blacklistService) {
        this.queryFactory = queryFactory;
        this.blacklistService = blacklistService;
    }

    private final HttpClient client = HttpClient.newHttpClient();

    public void startServer() {


        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String url = scanner.nextLine();
                Query query = this.queryFactory.getQuery();
                query.setMethod(QueryMethod.GET);
                query.setUrl(url);
                if (blacklistService.check(query)) {
                    QueryHandler queryHandler = new QueryHandler(query);
                    queryHandler.sendQueryAndPrintResponse();
                } else {
                    System.err.println("Query rejected : domain blacklised !");
                }

            }
        }
    }

}
