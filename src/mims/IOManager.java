package mims;

import java.io.File;

public class IOManager {
    private final File dataDirectory;

    public IOManager() {
        // Define the directory path for MIMS_Data under the user's Program Files directory
        String programFiles = System.getenv("ProgramFiles");
        dataDirectory = new File(programFiles, "MIMS_Data");

        // Check if the directory exists and create it if it doesn't
        if (!dataDirectory.exists()) {
            boolean wasCreated = dataDirectory.mkdirs();
            if (wasCreated) {
                System.out.println("Directory created successfully.");
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

    // ! FOR TESTING PURPOSES ONLY
    public static void main(String[] args) {
        new IOManager();
    }
}


