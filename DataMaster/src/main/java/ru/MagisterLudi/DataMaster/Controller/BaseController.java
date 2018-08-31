package ru.MagisterLudi.DataMaster.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.MagisterLudi.DataMaster.Enum.SystemEnum;
import ru.MagisterLudi.DataMaster.Model.BaseDocument;
import ru.MagisterLudi.DataMaster.Service.DocumentService;
import ru.MagisterLudi.DataMaster.Service.SystemService;

@RestController
public class BaseController {

    private DocumentService documentService;
    private SystemService systemService;

    public BaseController(DocumentService documentService, SystemService systemService) {
        this.documentService = documentService;
        this.systemService = systemService;
    }

    @RequestMapping(path = "/getData", method = RequestMethod.PUT)
    public void getData(@RequestBody BaseDocument document) {
        documentService.saveDocument(document);
    }

    @RequestMapping(path = "/who", method = RequestMethod.GET)
    public String getWhoIAm() {
        return systemService.getSelfAddres();
    }

    @RequestMapping(path = "/interview/setState", method = RequestMethod.PUT)
    public void setState(String master) {
        systemService.setState(master);
    }

}
