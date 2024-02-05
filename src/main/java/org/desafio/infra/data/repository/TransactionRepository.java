package org.desafio.infra.data.repository;

import java.util.UUID;

public interface TransactionRepository {
    UUID saveTransaction(Long idSender, Long idReceiver, Double amount);
}
