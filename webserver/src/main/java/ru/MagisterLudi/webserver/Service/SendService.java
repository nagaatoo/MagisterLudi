package ru.MagisterLudi.webserver.Service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import ru.MagisterLudi.webserver.Enum.MessageStatus;
import ru.MagisterLudi.webserver.Model.AbstractDataModel;
import ru.MagisterLudi.webserver.Model.BaseDocument;
import ru.MagisterLudi.webserver.Model.MasterServers;

import java.util.HashMap;
import java.util.Map;

@Service
@EnableScheduling
public class SendService<T extends AbstractDataModel> {

    final static private Logger logger = Logger.getLogger(SendService.class);

    @Value("${addres.mastermanager}")
    private String masterManager;

    // TODO переделать на настоящий masterGate
    @Value("${addres.mastergate}")
    private String masterGate;

    private Map<String, String> actualMasterAddres = new HashMap<>();
    private RestTemplate restToMasterManager = new RestTemplate();

    public void saveDocument(T model) {
        if(model instanceof BaseDocument) {
            restToMasterManager.postForObject(masterGate, model, BaseDocument.class);
        }
    }

    @Scheduled(fixedDelay = 50000)
    private void masterSheduler(){
        try {
            MasterServers servers = restToMasterManager.getForObject(masterManager, MasterServers.class);
            actualMasterAddres.clear();
            servers.getServers().forEach((key, value)->{
                actualMasterAddres.put(key, value);
            });
            if(servers.getStatus() == MessageStatus.OK) {
                logger.info("Make response to Master Manager was successfull");
            } else {
                logger.info("Make response to Master Manager was failed");
            }
        } catch(ResourceAccessException e) {
            logger.error("Master Manager is down now :(");
        }

    }
}
