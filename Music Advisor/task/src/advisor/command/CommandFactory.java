package advisor.command;

import advisor.exception.InvalidInputException;

public class CommandFactory {

    private final String unknownMessage = "Unknown command";
    private final String missingParameterMessage = "Missing Parameter";

    private final String playlistsCommand = "playlists";
    private final String newCommand = "new";
    private final String featuredCommand = "featured";
    private final String categoriesCommand = "categories";
    private final String helpCommand = "help";
    private final String stopCommand = "exit";
    private final String authCommand = "auth";

    public Command getCommand(String commandLine) throws InvalidInputException {

        String[] parts = commandLine.split("\\s", 2);
        String textCommand = parts[0];

        String parameters = null;
        if (parts.length > 1 ) {
            parameters = parts[1];
        }

        Command command;
        switch (textCommand) {
            case helpCommand:
                command = new HelpCommand();
                break;
            case stopCommand:
                command = new ExitCommand();
                break;
            case featuredCommand:
                command = new FeaturedCommand();
                break;
            case newCommand:
                command = new NewCommand();
                break;
            case categoriesCommand:
                command = new CategoriesCommand();
                break;
            case authCommand:
                command = new AuthCommand();
                break;
            case playlistsCommand:
                if (parameters == null) {
                    throw new InvalidInputException(missingParameterMessage);
                }
                command = new PlaylistsCommand(parameters);
                break;
            default:
                throw new InvalidInputException(unknownMessage + " " + textCommand);
        }


        return command;
    }
}
