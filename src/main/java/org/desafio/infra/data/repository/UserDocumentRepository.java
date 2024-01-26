package org.desafio.infra.data.repository;

import org.desafio.infra.data.entity.UserDocument;

import java.util.Optional;

public interface UserDocumentRepository {

    Optional<UserDocument> getByNumber(Long documentNumber);
}
