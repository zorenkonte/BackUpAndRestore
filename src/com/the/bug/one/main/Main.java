package com.the.bug.one.main;

import com.the.bug.one.backup.BackUp;

import static com.the.bug.one.util.Utility.createDefaultDirectory;

public class Main {
    public static void main(String[] args) {
        String host = args[0];
        String user = args[1];
        String password = args[2];
        String database = args[3];

        createDefaultDirectory(); //create default folder if not exist on startup

        BackUp.performBackUp(host, user, password, database); //do backup
    }
}
