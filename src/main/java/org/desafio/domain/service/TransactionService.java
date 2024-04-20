package org.desafio.domain.service;

import io.quarkus.logging.Log;
import io.quarkus.runtime.util.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.desafio.domain.dto.CreateTransactionRequest;
import org.desafio.domain.enumeration.ErrorEnum;
import org.desafio.domain.exception.ApplicationException;
import org.desafio.domain.exception.NotFoundApplicationException;
import org.desafio.domain.usecase.CreateTransactionUseCase;
import org.desafio.infra.data.entity.User;
import org.desafio.infra.data.entity.UserDocument;
import org.desafio.infra.data.repository.TransactionRepository;
import org.desafio.infra.data.repository.UserDocumentRepository;
import org.desafio.infra.data.repository.UserRepository;
import org.desafio.infra.rest.client.ApplicationRestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@ApplicationScoped
public class TransactionService implements CreateTransactionUseCase {

    final UserDocumentRepository userDocumentRepository;

    final UserRepository userRepository;

    final TransactionRepository transactionRepository;

    final ApplicationRestClient restClient;

    @Inject
    public TransactionService(
            UserDocumentRepository userDocumentRepository,
            UserRepository userRepository,
            TransactionRepository transactionRepository,
            ApplicationRestClient restClient) {
        this.userDocumentRepository = userDocumentRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.restClient = restClient;
    }

    @Override
    @Transactional
    public UUID createTransaction(CreateTransactionRequest request) {
        log("initializing create transaction process...");
        validateFields(request);
        User sender = validateSenderExistence(request);
        User receiver = validateReceiverExistence(request);
        validateSamePerson(sender, receiver);
        validateCanPay(sender);
        validateCanReceive(receiver);
        validateBalance(sender, request.amount());

        UUID id = doTransaction(sender, receiver, request.amount());

        notifyReceiver(request);

        return id;
    }

    private void validateSamePerson(User sender, User receiver) {
        if(sender.getId().equals(receiver.getId()))
            throw new ApplicationException(ErrorEnum.SAME_RECEIVER_AND_SENDER_DATA);
    }


    private void validateCanReceive(User user) {
        if(! user.getUserType().getReceive())
            throw new ApplicationException(ErrorEnum.RECEIVER_TYPE_CANT_RECEIVE);
    }

    private void validateCanPay(User user) {
        if(! user.getUserType().getSend())
            throw new ApplicationException(ErrorEnum.SENDER_TYPE_CANT_PAY);
    }

    private void notifyReceiver(CreateTransactionRequest request) {
        // TODO call notify service
    }

    private UUID doTransaction(User sender, User receiver, Double amount) {
        updateSenderBalance(sender, amount);
        updateReceiverBalance(receiver, amount);
        UUID id = saveTransaction(sender, receiver, amount);
        callAuthorizeService(id);
        return id;
    }

    @SneakyThrows
    private void callAuthorizeService(UUID id) {
        log("Calling authorization service...");
        restClient.authorizeTransaction(id);
    }

    private void updateReceiverBalance(User receiver, Double amount) {
        log("Updating receiver's balance...");
        userRepository.updateBalanceById(receiver.getId(), receiver.getBalance() + amount);
    }

    private void updateSenderBalance(User sender, Double amount) {
        log("Updating sender's balance...");
        userRepository.updateBalanceById(sender.getId(), sender.getBalance() - amount);
    }

    private void validateBalance(User user, Double amount) {
        if(user.getBalance() < amount)
            throw new  ApplicationException(ErrorEnum.INSUFFICIENT_FUNDS);
    }


    private User validateReceiverExistence(CreateTransactionRequest request) {
        Optional<User> user = getUser(
                request.id_receiver(),
                request.document_receiver(),
                request.email_receiver());

        return user.orElseThrow(() -> new NotFoundApplicationException(ErrorEnum.RECEIVER_NOT_FOUND));
    }

    private User validateSenderExistence(CreateTransactionRequest request) {
        Optional<User> user = getUser(
                request.id_sender(),
                request.document_sender(),
                request.email_sender());

        return user.orElseThrow(() -> new NotFoundApplicationException(ErrorEnum.SENDER_NOT_FOUND));
    }

    private Optional<User> getUser(Long id, Long document, String email) {
        if(Objects.nonNull(id))
            return userRepository.getById(id);

        if(!StringUtil.isNullOrEmpty(email)){
            return  userRepository.getByEmail(email);
        }

        return userDocumentRepository
                .getByNumber(document)
                .map(UserDocument::getUser);
    }

    private UUID saveTransaction(User sender, User receiver, Double amount) {
        log("Saving transaction...");
        return transactionRepository.saveTransaction(sender.getId(), receiver.getId(), amount);
    }

    private void validateFields(CreateTransactionRequest request) {
        Log.info("Validating fields...");
        validateReceiverData(request);
        validateSenderData(request);
        validateSameData(request);
    }

    private void validateSameData(CreateTransactionRequest request) {
        log("Validating if has same input...");
        Object[][] validationData = new Object[][]{
                {request.id_receiver(), request.id_sender()},
                {request.email_receiver(), request.email_sender()},
                {request.document_receiver(), request.document_sender()}
        };
        for (Object[] validationDatum : validationData) {
            Object receiverData = validationDatum[0];
            Object senderData = validationDatum[1];
            if (Objects.isNull(receiverData) || Objects.isNull(senderData))
                continue;

            if (receiverData.equals(senderData))
                throw new  ApplicationException(ErrorEnum.SAME_RECEIVER_AND_SENDER_DATA);
        }
    }


    private void validateSenderData(CreateTransactionRequest request) {
        log("Validating sender data...");
        final List<Object> senderData =  new ArrayList<>();
        senderData.add(request.document_sender());
        senderData.add(request.email_sender());
        senderData.add(request.id_sender());

        boolean isAllNull = senderData
                .stream()
                .allMatch(Objects::isNull);

        log("Validating if has no sender identifier fields...");

        if(isAllNull)
            throw new  ApplicationException(ErrorEnum.NO_SENDER_DATA);

        boolean hasTooMuchData = senderData
                .stream()
                .filter(Objects::nonNull)
                .count() > 1;

        log("Validating if has too much sender identifier fields...");
        if(hasTooMuchData)
            throw new  ApplicationException(ErrorEnum.TOO_MUCH_SENDER_ID_FIELDS);
    }

    private void validateReceiverData(CreateTransactionRequest request){
        log("Validating receiver data");
        final List<Object> receiverData =  new ArrayList<>();
        receiverData.add(request.document_receiver());
        receiverData.add(request.email_receiver());
        receiverData.add(request.id_receiver());

        boolean isAllNull = receiverData
                .stream()
                .allMatch(Objects::isNull);

        log("Validating if has no receiver identifier fields...");

        if(isAllNull)
            throw new  ApplicationException(ErrorEnum.NO_RECEIVER_DATA);

        boolean hasTooMuchData = receiverData
                .stream()
                .filter(Objects::nonNull)
                .count() > 1;

        log("Validating if has too much receiver identifier fields...");
        if(hasTooMuchData)
            throw new  ApplicationException(ErrorEnum.TOO_MUCH_RECEIVER_ID_FIELDS);
    }

    private static void log(String message) {
        Log.info(message);
    }
}
