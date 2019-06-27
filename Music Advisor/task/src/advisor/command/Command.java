package advisor.command;

public abstract class Command {

    protected boolean exitCommand = false;

    protected boolean loginRequired = true;

    public abstract void handle();

    public boolean isLoginRequired() {
        return loginRequired;
    }

    public boolean isExitCommand() {
        return exitCommand;
    }
}
