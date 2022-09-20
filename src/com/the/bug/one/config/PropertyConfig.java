package com.the.bug.one.config;

public class PropertyConfig {
    private static PropertyConfig INSTANCE = null;
    private String host;
    private String user;
    private String password;
    private String database;
    private String port;
    private String cronExpression;
    private String mysqlDump;

    public PropertyConfig() {

    }

    public PropertyConfig(String host, String user, String password, String database) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.database = database;
    }

    public PropertyConfig(String host, String user, String password, String database, String port) {
        this(host, user, password, database);
        this.port = port;
    }

    public PropertyConfig(String host, String user, String password, String database, String port, String cronExpression) {
        this(host, user, password, database, port);
        this.cronExpression = cronExpression;
    }

    public PropertyConfig(String host, String user, String password, String database, String port, String cronExpression, String mysqlDump) {
        this(host, user, password, database, port, cronExpression);
        this.mysqlDump = mysqlDump;
    }

    public static PropertyConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PropertyConfig();
        }
        return INSTANCE;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
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

    public String getMysqlDump() {
        return mysqlDump;
    }

    public void setMysqlDump(String mysqlDump) {
        this.mysqlDump = mysqlDump;
    }

    @Override
    public String toString() {
        return "PropertyConfig{" +
                "host='" + host + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", database='" + database + '\'' +
                ", port='" + port + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", mysqlDump='" + mysqlDump + '\'' +
                '}';
    }
}
