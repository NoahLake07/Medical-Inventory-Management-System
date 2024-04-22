package mims;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IOManager {

    private boolean devMode = false;
    private final File dataDirectory;
    public static final String APP_FOLDER_PATH = System.getenv("APPDATA") + "/" + "MIMS_Data";
    public static final String ENV_FILE_PATH = APP_FOLDER_PATH + "/ENV/";

    private File envFile,UIFile,inventoryFile,DPLFile;

    public IOManager() {
        // Define the directory path for MIMS_Data under the user's Program Files directory
        String programFiles = System.getenv("APPDATA");
        dataDirectory = new File(programFiles, "MIMS_Data");

        // Check if the directory exists and create it if it doesn't
        if (!dataDirectory.exists()) {
            boolean wasCreated = dataDirectory.mkdirs();
            if (wasCreated) {
                System.out.println("Directory created successfully at " + programFiles);
                try {
                    createEnvFile();
                } catch (IOException e) {
                    System.out.println("Failed to create environment file.");
                }
            } else {
                System.out.println("Failed to create directory. Check permissions.");
            }
        } else {
            // If the directory exists, load data from it
            loadFromFile(dataDirectory);
        }
    }

    private void loadFromFile(File directory) {
        // Implementation to load data from the directory
        System.out.println("Loading data from " + directory.getPath());
        // todo Add actual file loading logic here
    }

    private void updateFileReferences(){
        if(Files.exists(Paths.get(ENV_FILE_PATH))){
            envFile = new File(ENV_FILE_PATH);
        }
        // todo add static constants with other file paths
        // todo update references for other files
    }

    private void createEnvFile() throws IOException {
        File env = new File(ENV_FILE_PATH);
        FileWriter writer = new FileWriter(env);

        // by default, the envFile will have devMode set to false (0);
        writer.append("0\n");

        // close the file
        writer.close();

        this.envFile = env;
    }

    public File getUIFile(){
        updateFileReferences();
        return this.UIFile;
    }

    public File getInventoryFile(){
        updateFileReferences();
        return inventoryFile;
    }

    public File getDPLFile(){
        updateFileReferences();
        return DPLFile;
    }

    public File getEnvFile(){
        updateFileReferences();
        return DPLFile;
    }

    // ! FOR TESTING PURPOSES ONLY
    public static void main(String[] args) {
        new IOManager();
    }
}


