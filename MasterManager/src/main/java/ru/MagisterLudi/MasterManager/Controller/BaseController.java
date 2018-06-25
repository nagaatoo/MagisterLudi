package ru.MagisterLudi.MasterManager.Controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.MagisterLudi.MasterManager.Enums.MessageStatus;
import ru.MagisterLudi.MasterManager.Models.MasterServers;
import ru.MagisterLudi.MasterManager.Service.ManagerController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BaseController {

    final static private Logger logger = Logger.getLogger(BaseController.class);

    private ManagerController managerController;

    @Autowired
    private BaseController(ManagerController managerController){
        this.managerController = managerController;
    }

    @RequestMapping(path = "/accessServers", method = RequestMethod.GET)
    public MasterServers getServerAddres(MasterServers document, HttpServletRequest request){
        MasterServers servers = new MasterServers();
        servers.getServers().put("Moscow.ru", "ya.ru");
        servers.getServers().put("dream.com", "8.8.8.8");
        servers.setStatus(MessageStatus.OK);
        logger.info("Send ServerAddres to " + request.getRemoteAddr() + ":" + request.getRemotePort());
        return servers;
    }

    // временное, будет не тут
    @RequestMapping(path = "/saveFile", method = RequestMethod.GET)
    public void getFileTest() {

    }
}
