package lusaris;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class handles the initialization of our application, creating the
 * necessary files, and delegates the commands to our other modules.
 */

public class Aion {

    /* The folder where the active tasks are stored. */
    public static final File activeFolder = new File("./.aion/active/");

    /* The folder where the archived tasks are stored. */
    public static final File archiveFolder = new File("./.aion/archive/");

    /* The file that stores the settings for our app. */
    public static final File configFile = new File("./.aion/config.json");

    /* The file that stores the hash of the active tasks. */
    public static final File activeTasksFile = new File("./.aion/active.json");

    /* The HashMap storing the name of the active Tasks taken from the activeTasksFile */
    public static HashMap<String, ArrayList<String>> activeTasks = new HashMap<>();

    /* ============================= SETTINGS ============================= */

    /* A setting to decide on how verbose the startup system should be. */
    public static boolean basicsVerbose;

    /* A setting to decide on how verbose the creation system should be. */
    public static boolean serializationVerbose;

    public Aion() {
        check();
    }

    /**
     * Actions to be taken given the command.
     * @param command The command and other arguments given by the user.
     */
    public void action(String... command) {
        switch (command[0]) {
            case "create":
                if (AionUtils.checkNInputs(2, command)) {
                    AionCommands.create(command[1]);
                }
                break;
            case "take":
                if (AionUtils.checkNInputs(2, command)) {
                    AionCommands.take(command[1]);
                }
                break;
            case "drop":
                if (AionUtils.checkNInputs(2, command)) {
                    AionCommands.drop(command[1]);
                }
                break;
            case "archive":
                if (AionUtils.checkNInputs(2, command)) {
                    AionCommands.archive(command[1]);
                }
                break;
            case "bank":
                if (AionUtils.checkNInputs(1, command)) {
                    AionCommands.bank();
                }
                break;
            default:
                System.out.println("Not a valid command.");
        }

    }

    /**
     * Runs all the basic check functions when we start a new program.
     */
    private void check() {
        checkRequiredFiles();
    }

    /**
     * This method ensures all the folders and files are ready
     * for our application. This method should be called
     * each time a new Aion instance is created.
     */
    private void checkRequiredFiles() {
        String returnMessage = "";
        if (activeFolder.mkdirs()) {
            returnMessage += "./aion/active folder created \n";
            AionCommands.create("New task");
        }
        if (archiveFolder.mkdirs()) {
            returnMessage += "./aion/archive folder created \n";
        }
        try {
            if (configFile.createNewFile()) {
                returnMessage += "./aion/config.json file created \n";
                defaultConfig();
            }
            if (activeTasksFile.createNewFile()) {
                returnMessage += "./aion/active.json file created \n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (returnMessage.length() == 0) {
            returnMessage += "No folders or files were created. \n";
        }
        readConfig();
        readActive();
        if (basicsVerbose) {
            System.out.println(returnMessage + "All folders and files are initialized.");
        }
    }

    /**
     * This method is called by checkFolders to set up a default config.json
     * file if one is not already created. This method can also be used
     * to reset the config file to the original settings.
     */
    private void defaultConfig() {
        HashMap<String, Object> hashSettings = new HashMap<>();
        hashSettings.put("basicsVerbose", true);
        hashSettings.put("serializationVerbose", true);
        AionUtils.writeHashMaptoJSON(configFile, hashSettings);
    }

    /**
     * This method is called by checkFolders to read the contents of the
     * config.json file. This method sets up all the settings according to
     * the config.json file.
     */
    private void readConfig() {
        JSONParser parser = new JSONParser();

        try (FileReader readConfig = new FileReader(configFile.getPath())) {
            JSONObject settings = (JSONObject) parser.parse(readConfig);
            basicsVerbose = (boolean) settings.get("basicsVerbose");
            serializationVerbose = (boolean) settings.get("serializationVerbose");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method that reads the active.json file and extracts the current
     * tasks that are active.
     */
    private void readActive() {
        JSONParser parser = new JSONParser();
        try (FileReader readActive = new FileReader(activeTasksFile.getPath())) {
            JSONObject actives = (JSONObject) parser.parse(readActive);
            for (Object activeTask : actives.keySet()) {
                ArrayList<String> activeTaskHash =
                        AionUtils.JSONArr_to_ArrayList(
                                (JSONArray) actives.get(activeTask));
                activeTasks.put(activeTask.toString(), activeTaskHash);
            }
            if (basicsVerbose) {
                System.out.println(activeTasks);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
