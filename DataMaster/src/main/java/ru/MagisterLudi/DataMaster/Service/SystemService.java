package ru.MagisterLudi.DataMaster.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.MagisterLudi.DataMaster.Enum.SystemEnum;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class SystemService {

    @Value("${ru.MagisterLudi.system.addres}")
    private String setfAddres;

    private Map<String, String> clasterDB = new HashMap<>();

    private String state;
    private RestTemplate restToMasterManager = new RestTemplate();

    public String getSelfAddres() {
        return setfAddres;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if(state.equals(SystemEnum.MASTER.toString()) || state.equals(SystemEnum.SLAVE.toString())) {
            this.state = state;
        }
    }

    public boolean checkingMasterAlive() {
        String addres = "";

        for(Map.Entry<String, String> entry : clasterDB.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if(value.equals(SystemEnum.MASTER)) {
                addres = key;
            }
        }

        boolean isAlive = true;
        try {
            restToMasterManager.getRequestFactory().createRequest(URI.create(addres), HttpMethod.GET).execute();
        } catch (IOException e) {
            // Если эксепшн - значит мастер мертв
            isAlive = false;
        }
        return isAlive;
    }

    public void addClasster(String addres, String status) {
        clasterDB.put(addres, status);
    }

}
