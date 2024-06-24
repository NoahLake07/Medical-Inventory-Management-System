package mims;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import mims.data.Inventory;
import mims.data.app.MIMSEnv;
import mims.data.app.UISettings;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IOManager {

    public static boolean devMode = true;
    private static final String DATA_DIR = System.getenv("APPDATA") + "/MIMS_Data";
    private static final String INVENTORY_FILE = DATA_DIR + "/inventory.json";
    private static final String UISETTINGS_FILE = DATA_DIR + "/uisettings.json";
    private static final String DPLCONFIG_FILE = DATA_DIR + "/dplconfig.json";
    private static final String MIMSENV_FILE = DATA_DIR + "/mimsenv";

    private ObjectMapper objectMapper;

    public IOManager() {
        objectMapper = new ObjectMapper();
        ensureDataDirectoryExists();
    }

    private void ensureDataDirectoryExists() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public void saveInventory(Inventory inventory) {
        saveToFile(INVENTORY_FILE, inventory);
    }

    public Inventory loadInventory() {
        if(Files.exists(Paths.get(INVENTORY_FILE))) {
            return loadFromFile(INVENTORY_FILE, Inventory.class);
        } else {
            if(devMode) System.out.println("> No Inventory file found, creating new one...");
            return new Inventory();
        }
    }

    public void saveUISettings(UISettings settings) {
        saveToFile(UISETTINGS_FILE, settings);
    }

    public UISettings loadUISettings() {
        if(Files.exists(Paths.get(UISETTINGS_FILE))) {
            return loadFromFile(UISETTINGS_FILE, UISettings.class);
        } else {
            if(devMode) System.out.println("> No UISettings file found, creating new one...");
            return new UISettings();
        }
    }

    public void saveDPL(DPL dpl) {
        saveToFile(DPLCONFIG_FILE, dpl);
    }

    public DPL loadDPL() {
        if(Files.exists(Paths.get(DPLCONFIG_FILE))) {
            return loadFromFile(DPLCONFIG_FILE, DPL.class);
        } else {
            if(devMode) System.out.println("> No DPL file found, creating new one...");
            return new DPL();
        }
    }

    public void saveMIMSEnv(MIMSEnv env) {
        saveToFile(MIMSENV_FILE, env);
    }

    public MIMSEnv loadMIMSEnv() {
        if(Files.exists(Paths.get(MIMSENV_FILE))) {
            MIMSEnv mimsenv = loadFromFile(MIMSENV_FILE, MIMSEnv.class);
            this.devMode = mimsenv.isDevMode();
            return mimsenv;
        } else {
            if(devMode) System.out.println("> No MIMSEnv file found, creating new one...");
            return new MIMSEnv();
        }
    }

    public void saveAll(DPL dpl, Inventory inventory, UISettings settings, MIMSEnv env) {
        saveDPL(dpl);
        saveInventory(inventory);
        saveUISettings(settings);
        saveMIMSEnv(env);
    }

    public void verifyInit() {
        if(Files.exists(Paths.get(MIMSENV_FILE))) {
            if(devMode) System.out.println("> MIMSEnv file found.");
        } else {
            if(devMode) System.out.println("> No MIMSEnv file found, creating new one...");
            saveMIMSEnv(new MIMSEnv());
        }

        if(Files.exists(Paths.get(INVENTORY_FILE))) {
            if(devMode) System.out.println("> Inventory file found.");
        } else {
            if(devMode) System.out.println("> No Inventory file found, creating new one...");
            saveInventory(new Inventory());
        }

        if(Files.exists(Paths.get(UISETTINGS_FILE))) {
            if(devMode) System.out.println("> UISettings file found.");
        } else {
            if(devMode) System.out.println("> No UISettings file found, creating new one...");
            saveUISettings(new UISettings());
        }

        if(Files.exists(Paths.get(DPLCONFIG_FILE))) {
            if(devMode) System.out.println("> DPL file found.");
        } else {
            if(devMode) System.out.println("> No DPL file found, creating new one...");
            saveDPL(new DPL());
        }
    }

    private <T> void saveToFile(String filePath, T object) {
        try {
            objectMapper.writeValue(new File(filePath), object);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save data to file: " + filePath, e);
        }
    }

    private <T> T loadFromFile(String filePath, Class<T> clazz) {
        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(filePath));
            String content = new String(encoded, StandardCharsets.UTF_8);
            return objectMapper.readValue(content, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}