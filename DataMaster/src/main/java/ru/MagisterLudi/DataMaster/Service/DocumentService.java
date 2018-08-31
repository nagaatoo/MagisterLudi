package ru.MagisterLudi.DataMaster.Service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.MagisterLudi.DataMaster.Model.BaseDocument;
import ru.MagisterLudi.DataMaster.Model.DocumentDAO;
import ru.MagisterLudi.DataMaster.Repository.SelfRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentService {

    private SelfRepository repository;

    public DocumentService(SelfRepository selfRepository) {
        this.repository = selfRepository;
    }

    public void saveDocument(BaseDocument document) {
        if(StringUtils.isAllBlank(document.getUUID(), document.getFileName(), document.getOwnerName()) || StringUtils
                .isEmpty(document.getFileName())) {
            throw new RuntimeException("UUID not found");
        }
        repository.save(pojoToDAO(document));
    }

    public BaseDocument getDocument(String uuid) {
        DocumentDAO document = repository.findByUUID(uuid);
        return DAOTOPojo(document);
    }

    public List<BaseDocument> getDocumentsByFileName(String fileName) {
        if(StringUtils.isBlank(fileName)) {
            throw new RuntimeException("FileName not found");
        }
        List<DocumentDAO> documents = repository.findByfileNameLike(fileName);
        List<BaseDocument> pojo = new ArrayList<>();
        documents.forEach(document -> pojo.add(DAOTOPojo(document)));
        return pojo;
    }

    public List<BaseDocument> getDocumentsByOwnerName(String owner) {
        if(StringUtils.isBlank(owner)) {
            throw new RuntimeException("Owner not found");
        }
        List<DocumentDAO> documents = repository.findByOwnerNameLike(owner);
        List<BaseDocument> pojo = new ArrayList<>();
        documents.forEach(document -> pojo.add(DAOTOPojo(document)));
        return pojo;
    }

    public List<BaseDocument> getDocumentsByDesctioption(String desc) {
        if(StringUtils.isBlank(desc)) {
            throw new RuntimeException("Descrioption not found");
        }
        List<DocumentDAO> documents = repository.findByDescriptionLike(desc);
        List<BaseDocument> pojo = new ArrayList<>();
        documents.forEach(document -> pojo.add(DAOTOPojo(document)));
        return pojo;
    }

    public DocumentDAO pojoToDAO(BaseDocument document) {
        return new DocumentDAO(document.getUUID(), document.getDescription(), document.getFileName(), document
                .getOwnerName(), document.getFile());
    }

    public BaseDocument DAOTOPojo(DocumentDAO dao) {
        return new BaseDocument(dao.getUUID(), dao.getDescription(), dao.getFileName(), dao.getOwnerName(), dao.getFile());
    }
}
