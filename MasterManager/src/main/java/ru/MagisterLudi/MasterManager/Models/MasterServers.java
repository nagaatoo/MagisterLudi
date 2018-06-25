package ru.MagisterLudi.MasterManager.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.MagisterLudi.MasterManager.Enums.MessageStatus;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MasterServers {

    private Map<String, String> servers;
    private MessageStatus status;

    public MasterServers(){

    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public Map<String, String> getServers() {
        if(servers == null) {
            servers = new HashMap<>();
        }
        return servers;
    }

    public void setServers(Map<String, String> servers) {
        this.servers = servers;
    }


}
