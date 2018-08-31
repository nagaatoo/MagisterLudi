package ru.MagisterLudi.DataMaster.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.MagisterLudi.DataMaster.Model.DocumentDAO;

import java.util.List;

public interface SelfRepository extends MongoRepository<DocumentDAO, String> {
    DocumentDAO findByUUID(String uuid);
    List<DocumentDAO> findByOwnerNameLike(String ownerName);
    List<DocumentDAO> findByfileNameLike(String fileName);
    List<DocumentDAO> findByDescriptionLike(String description);
}
