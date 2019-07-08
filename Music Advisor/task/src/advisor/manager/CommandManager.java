package advisor.manager;

import advisor.command.Command;
import advisor.command.CommandFactory;
import advisor.exception.InvalidInputException;
import advisor.service.SpotifyService;

import java.util.Scanner;

public class CommandManager {

    CommandFactory commandFactory;
    Scanner scanner;
    boolean getNextLine = true;

    boolean userLogged = false;

    public CommandManager(Scanner scanner) {
        this.scanner = scanner;
        this.commandFactory = new CommandFactory();
    }

    public void executeLine()
    {
        String nextLine = getNextLine();
        if (!nextLine.isEmpty()) {

            try {
                startCommand(nextLine);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (getNextLine) {
            executeLine();
        }
    }

    private void startCommand(String line) throws InvalidInputException {
        Command command = commandFactory.getCommand(line);
        if (!command.isLoginRequired() || SpotifyService.isUserLogged()) {
            command.handle();
        }
        else {
            System.out.println("Please, provide access for application.");
        }

        if (command.isExitCommand()) {
            getNextLine = false;
        }
    }

    private String getNextLine() {
        return scanner.nextLine();
    }

}
