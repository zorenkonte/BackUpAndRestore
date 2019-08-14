package the.bug.one.main;

import the.bug.one.backup.BackUp;
import the.bug.one.restore.Restore;

public class Main {
    public static void main(String[] args) {
        BackUp.performBackUp("localhost", "user", "password", "database"); //do backup
        Restore.performRestore("localhost", "user", "password"); //do restore
    }
}
