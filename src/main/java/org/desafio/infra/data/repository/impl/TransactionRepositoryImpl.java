package org.desafio.infra.data.repository.impl;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.desafio.infra.data.entity.Transaction;
import org.desafio.infra.data.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@ApplicationScoped
public class TransactionRepositoryImpl implements TransactionRepository, PanacheRepositoryBase<Transaction, UUID> {
    @Override
    public UUID saveTransaction(Long idSender, Long idReceiver, Double amount) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setSenderId(idSender);
        transaction.setReceiverId(idReceiver);
        transaction.setTimestamp(LocalDateTime.now());
        persist(transaction);
        return transaction.getId();
    }
}
