package main;


import blacklist.BlacklistService;
import blacklist.BlacklistServiceImpl;
import domaine.QueryFactory;
import domaine.QueryFactoryImpl;
import ioc.Injector;
import server.ProxyServer;

public class Main {

    public static void main(String[] args) throws Exception {
        ProxyServer proxyServer = new ProxyServer();
        Injector.inject(proxyServer);
        proxyServer.startServer();
    }

}