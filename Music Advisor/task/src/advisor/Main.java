package advisor;

import advisor.manager.CommandManager;
import advisor.service.ServerService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    CommandManager commandManager;
    Properties appConfig;
    ServerService serverService;

    public Main() {
        Scanner scanner = new Scanner(System.in);
        this.commandManager = new CommandManager(scanner);
        serverService = new ServerService();
        //this.appConfig = loadConfig();
    }

    public static void main(String[] args) {
        Main application = new Main();
        application.start();
    }

    public void start() {
        commandManager.executeLine();
    }

    private Properties loadConfig() {
        Properties appConfig = new Properties();
        try {
            // load the properties file using load() and an input stream
            //FileInputStream in = new FileInputStream("resources/config.properties");
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource("/Music Advisor/task/src/advisor/config.properties");

            System.out.println(resource);
            System.out.println("----------------------------");

            InputStream in = getClass()
                    .getClassLoader().getResourceAsStream("/config.properties");
            appConfig.load(in);
            in.close();

            appConfig.list(System.out);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return appConfig;
    }

}
