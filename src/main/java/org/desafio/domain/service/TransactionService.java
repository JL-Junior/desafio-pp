package org.desafio.domain.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.desafio.domain.dto.CreateTransactionRequest;
import org.desafio.domain.enumeration.ErrorEnum;
import org.desafio.domain.exception.ApplicationException;
import org.desafio.domain.usecase.CreateTransactionUseCase;

import java.util.*;

@ApplicationScoped
public class TransactionService implements CreateTransactionUseCase {

    @Override
    @Transactional
    public UUID createTransaction(CreateTransactionRequest request) {
        validateFields(request);
        validateExistences(request);
        validateCanPay(request);
        validateCanReceive(request);
        validateBalance(request);


        UUID id = doTransaction(request);

        notifyReceiver(request);

        return id;
    }

    private void validateCanReceive(CreateTransactionRequest request) {
        //TODO validate user can receive

    }

    private void validateCanPay(CreateTransactionRequest request) {
        //TODO validate user can pay
    }

    private void notifyReceiver(CreateTransactionRequest request) {
        // TODO call notify service
    }

    private UUID doTransaction(CreateTransactionRequest request) {
        updateSenderBalance(request);
        updateReceiverBalance(request);
        UUID id = saveTransaction(request);
        callAuthorizeService();
        return id;
    }

    private void callAuthorizeService() {
        // TODO call authorize mock service
    }

    private void updateReceiverBalance(CreateTransactionRequest request) {
        // TODO implementar atualizar o saldo da pessoa que recebeu

    }

    private void updateSenderBalance(CreateTransactionRequest request) {
        // TODO implementar atualizar o saldo da pessoa que enviou
    }

    private void validateBalance(CreateTransactionRequest request) {
        // TODO valida se a pessoa tem saldo na conta
    }

    private void validateExistences(CreateTransactionRequest request) {
        validateSenderExistence(request);
        validateReceiverExistence(request);
    }

    private void validateReceiverExistence(CreateTransactionRequest request) {
        // TODO implementar se a pessoa que recebe existe
    }

    private void validateSenderExistence(CreateTransactionRequest request) {
        // TODO implementar se pessoa que envia existe
    }

    private UUID saveTransaction(CreateTransactionRequest request) {
        // TODO salvar dados da transacao no banco de dados
        return null;

    }

    private void validateFields(CreateTransactionRequest request) {
        validateReceiverData(request);
        validateSenderData(request);
        validateSameData(request);
        // TODO validar campos de pessoa que recebe
    }

    private void validateSameData(CreateTransactionRequest request) {
        Object[][] validationData = new Object[][]{
                {request.id_receiver(), request.id_sender()},
                {request.email_receiver(), request.email_sender()},
                {request.document_receiver(), request.document_sender()}
        };
        for(int row = 0; row < validationData.length; row++){
            Object receiverData = validationData[row][0];
            Object senderData = validationData[row][1];
            if(Objects.isNull(receiverData) || Objects.isNull(senderData))
                continue;

            if(receiverData.equals(senderData))
                throw ApplicationException.of(ErrorEnum.SAME_RECEIVER_AND_SENDER_DATA);
        }

        /*
        continue = vai pro proximo item  do loop
        break = corta o for e sai do looping
        return = corta a execução do método

         */
    }


    private void validateSenderData(CreateTransactionRequest request) {
        final List<Object> senderData =  new ArrayList<>();
        senderData.add(request.document_sender());
        senderData.add(request.email_sender());
        senderData.add(request.id_sender());

        boolean isAllNull = senderData
                .stream()
                .allMatch(Objects::isNull);

        if(isAllNull)
            throw ApplicationException.of(ErrorEnum.NO_SENDER_DATA);

        boolean hasTooMuchData = senderData
                .stream()
                .filter(Objects::nonNull)
                .count() > 1;

        if(hasTooMuchData)
            throw ApplicationException.of(ErrorEnum.TOO_MUCH_SENDER_ID_FIELDS);
    }

    private void validateReceiverData(CreateTransactionRequest request){
        final List<Object> receiverData =  new ArrayList<>();
        receiverData.add(request.document_receiver());
        receiverData.add(request.email_receiver());
        receiverData.add(request.id_receiver());

        boolean isAllNull = receiverData
                .stream()
                .allMatch(Objects::isNull);

        if(isAllNull)
            throw ApplicationException.of(ErrorEnum.NO_RECEIVER_DATA);

        boolean hasTooMuchData = receiverData
                .stream()
                .filter(Objects::nonNull)
                .count() > 1;

        if(hasTooMuchData)
            throw ApplicationException.of(ErrorEnum.TOO_MUCH_RECEIVER_ID_FIELDS);
    }
}
