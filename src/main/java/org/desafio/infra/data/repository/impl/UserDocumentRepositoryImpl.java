package org.desafio.infra.data.repository.impl;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.desafio.infra.data.entity.UserDocument;
import org.desafio.infra.data.repository.UserDocumentRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class UserDocumentRepositoryImpl implements UserDocumentRepository, PanacheRepository<UserDocument> {
    @Override
    public Optional<UserDocument> getByNumber(Long documentNumber) {
        Map<String, Object> params = new HashMap<>();
        params.put("documentNumber", documentNumber);

        return find("documentNumber = :documentNumber", params)
                .singleResultOptional();
    }
}
