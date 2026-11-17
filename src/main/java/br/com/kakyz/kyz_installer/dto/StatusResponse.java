package br.com.kakyz.kyz_installer.dto;

import br.com.kakyz.kyz_installer.enums.DatabaseStatus;
import br.com.kakyz.kyz_installer.enums.Status;

public class StatusResponse {
    private String application;
    private Status status;
    private DatabaseStatus database;
    private String version;

    public StatusResponse (
        String application,
        Status status,
        DatabaseStatus database,
        String version
    ) {
        this.application = application;
        this.status = status;
        this.database = database;
        this.version = version;
    }


    public String getApplication() {return application;}
    public Status getStatus() {return status;}
    public DatabaseStatus getDatabase() {return database;}
    public String getVersion() {return version;}
}