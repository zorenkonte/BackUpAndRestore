package com.the.bug.one.job;

public class BackUpConfig {
    private static BackUpConfig INSTANCE = null;
    private String host;
    private String user;
    private String password;
    private String database;
    private String port;

    public BackUpConfig() {

    }

    public BackUpConfig(String host, String user, String password, String database) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.database = database;
    }

    public BackUpConfig(String host, String user, String password, String database, String port) {
        this(host, user, password, database);
        this.port = port;
    }

    public static BackUpConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BackUpConfig();
        }
        return INSTANCE;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "BackUpConfig{" +
                "host='" + host + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", database='" + database + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
