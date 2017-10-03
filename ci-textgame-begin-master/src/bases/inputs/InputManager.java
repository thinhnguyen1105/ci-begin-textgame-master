package bases.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huynq on 7/28/17.
 */
public class InputManager implements KeyListener {
    public static final InputManager instance = new InputManager();
    public String command;
    private ArrayList<String> oldCommands;
    private int oldCommandCurrentIndex;

    private List<CommandListener> commandListeners;

    public void addCommandListener(CommandListener commandListener) {
        commandListeners.add(commandListener);
    }

    public InputManager() {
        command = "";
        commandListeners = new ArrayList<>();
        oldCommands = new ArrayList<>();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char typedCharacter = e.getKeyChar();
        if (isValidInput(typedCharacter)) {
            changeCommand(command + typedCharacter);
        }

        if (typedCharacter == '\b' && command.length() > 0) {
            int endPosition = command.length() - 1;
            if (endPosition < 0) endPosition = 0;
            changeCommand(command.substring(0, endPosition));
        }
    }

    private void changeCommand(String newCommand) {
        command = newCommand;
        commandChanged = true;
    }

    private void finishCommand() {
        commandFinished = true;
        oldCommands.add(command);
        if (oldCommands.size() > 10) {
            oldCommands.remove(0);
        }
        oldCommandCurrentIndex = oldCommands.size();
    }

    private boolean isValidInput(char c) {
        List<Character> allowedSpecialCharacters =
                java.util.Arrays.asList('#', ';', '_', '-');

        return Character.isDigit(c)
                || Character.isSpaceChar(c)
                || Character.isLetter(c)
                || allowedSpecialCharacters.contains(c);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (command.length() > 0) {
                finishCommand();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (oldCommandCurrentIndex > 0) {
                oldCommandCurrentIndex--;
                changeCommand(oldCommands.get(oldCommandCurrentIndex));
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (oldCommandCurrentIndex < oldCommands.size() - 1) {
                oldCommandCurrentIndex++;
                changeCommand(oldCommands.get(oldCommandCurrentIndex));
            } else {
                oldCommandCurrentIndex = oldCommands.size();
                changeCommand("");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private boolean commandChanged;
    private boolean commandFinished;

    public void run() {
        if (commandFinished) {
            for (CommandListener cmdListener : commandListeners) {
                cmdListener.onCommandFinished(command);
            }
            command = "";
            commandFinished = false;
        } else if (commandChanged) {
            for (CommandListener cmdListener : commandListeners) {
                cmdListener.commandChanged(command);
            }
            commandChanged = false;
        }
    }
}
