package com.example.se2_group4_project.jsonComunication;

public class IpAdresse {
    private String ipAdresse;
    private String connectionName;

    public IpAdresse(String ipAdresse, String connectionName){
        this.ipAdresse = ipAdresse;
        this.connectionName = connectionName;
    }

    public String getIpAdresse(){
        return this.ipAdresse;
    }

    public String getConnectionName(){
        return this.connectionName;
    }
}
