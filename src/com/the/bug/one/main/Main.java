package com.the.bug.one.main;

import com.the.bug.one.backup.BackUp;
import com.the.bug.one.restore.Restore;

public class Main {
    public static void main(String[] args) {
        BackUp.performBackUp("localhost", "user", "password", "database"); //do backup
        Restore.performRestore("localhost", "user", "password"); //do restore
    }
}
