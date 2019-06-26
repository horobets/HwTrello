package com.trello.ui.core.credentialsstorage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class CredentialsStorage {
    //static final String credentialsStorageFile = "c:\\credentials\\kscredentials.txt";
    private final String credentialsStorageFile;

    public CredentialsStorage(String credentialsStorageFile) {
        this.credentialsStorageFile = credentialsStorageFile;
    }

    public boolean saveCredentials(Credentials credentials) {
        ReadWriteTextFileLines readWriteTextFileLines = new ReadWriteTextFileLines(credentialsStorageFile, true);
        try {
            readWriteTextFileLines.writeToFile(String.format("[%s] l:%s p:%s", LocalDateTime.now().toString(), credentials.getUsername(), credentials.getPassword()));
        } catch (IOException ex) {
            System.err.printf("Error saving credentials to the storage file: %n%s", ex.getMessage());
            return false;
        }
        return true;
    }

    public Credentials getLastCredentials() {
        ReadWriteTextFileLines readWriteTextFileLines = new ReadWriteTextFileLines(credentialsStorageFile);
        try {
            List<String> lines = readWriteTextFileLines.readTextFileLines();
            String lastLine = lines.get(lines.size() - 1);
            //parse string with username and password

            String username = lastLine.substring(lastLine.indexOf("l:") + 2, lastLine.indexOf("p:") - 1);
            String password = lastLine.substring(lastLine.indexOf("p:") + 2);

            return new Credentials(username, password);
        } catch (IOException ex) {
            System.err.printf("Error reading credentials from the storage file: %n%s", ex.getMessage());
            return null;
        }
    }
}
