package advisor;

import advisor.command.Command;
import advisor.command.HelpCommand;
import advisor.manager.CommandManager;
import advisor.service.ServerService;

import java.io.FileNotFoundException;
import java.io.InputStream;
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
        this.appConfig = loadConfig();
    }


    public static void main(String[] args) {

        Main application = new Main();
        application.start();
    }

    public void start() {
        Command helpCommand = new HelpCommand();
        helpCommand.handle();

        commandManager.executeLine();
    }

    private Properties loadConfig() {
        Properties appConfig = new Properties();
        try {

            Properties prop = new Properties();
            String propFileName = "config.properties";

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            System.out.println(prop.getProperty("service.spotify.url"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return appConfig;
    }

}
