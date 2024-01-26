package org.desafio.infra.data.repository;

import org.desafio.infra.data.entity.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> getById(Long id);
    Optional<User> getByEmail(String email);

    boolean updateBalanceById(Long id, double amount);
}
