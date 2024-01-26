package org.desafio.infra.data.repository.impl;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.desafio.infra.data.entity.User;
import org.desafio.infra.data.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository, PanacheRepository<User> {
    @Override
    public Optional<User> getById(Long id) {
        return findByIdOptional(id);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);

        return find("email = :email", params)
                .singleResultOptional();
    }

    @Override
    public boolean updateBalanceById(Long id, double amount) {
        return update("balance = ?1 where id = ?2",  amount, id) > 0;
    }
}
