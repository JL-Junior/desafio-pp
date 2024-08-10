package org.desafio.domain.service;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.common.constraint.Assert;
import jakarta.inject.Inject;
import org.desafio.domain.dto.CreateTransactionRequest;
import org.desafio.domain.enumeration.ErrorEnum;
import org.desafio.domain.exception.ApplicationException;
import org.desafio.domain.exception.NotFoundApplicationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class TransactionServiceTest {

    @Inject TransactionService sut; // System Under Testing

    @Test
    void createTransaction_validateFields_whenReceiverDataIsNull(){
        //Arrange
        final CreateTransactionRequest input = new CreateTransactionRequest(
                10.00,
                1L,
                null,
                null,
                null,
                null,
                null
        );
        //Action
        ApplicationException error = Assertions.assertThrows(
                ApplicationException.class,
                () -> sut.createTransaction(input),
                "Tipo de erro esperado está incorreto");
        //Assert
        Assertions.assertEquals(
                ErrorEnum.NO_RECEIVER_DATA,
                error.getErrorEnum(),
                "A mensagem de erro está incorreta.");
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "1;joao@hotmail.com;",
            "1;;123456",
            ";joao@hotmail.com;123456",
            "1;joao@hotmail.com;123456"
    })
    void createTransaction_validateFields_whenReceiverDataIsTooMuch(
            Long idReceiver,
            String emailReceiver,
            Long documentReceiver){
        //Arrange
        final CreateTransactionRequest input = new CreateTransactionRequest(
                10.00,
                1L,
                idReceiver,
                null,
                emailReceiver,
                null,
                documentReceiver
        );
        //Action
        ApplicationException error = Assertions.assertThrows(
                ApplicationException.class,
                () -> sut.createTransaction(input),
                "Tipo de erro esperado está incorreto");
        //Assert
        Assertions.assertEquals(
                ErrorEnum.TOO_MUCH_RECEIVER_ID_FIELDS,
                error.getErrorEnum(),
                "A mensagem de erro está incorreta.");
    }

    @Test
    void createTransaction_validateFields_whenSenderDataIsNull(){
        //Arrange
        final CreateTransactionRequest input = new CreateTransactionRequest(
                10.00,
                null,
                1L,
                null,
                null,
                null,
                null
        );
        //Action
        ApplicationException error = Assertions.assertThrows(
                ApplicationException.class,
                () -> sut.createTransaction(input),
                "Tipo de erro esperado está incorreto");
        //Assert
        Assertions.assertEquals(
                ErrorEnum.NO_SENDER_DATA,
                error.getErrorEnum(),
                "A mensagem de erro está incorreta.");
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "1;joao@hotmail.com;",
            "1;;123456",
            ";joao@hotmail.com;123456",
            "1;joao@hotmail.com;123456"
    })
    void createTransaction_validateFields_whenSenderDataIsTooMuch(
            Long idSender,
            String emailSender,
            Long documentSender){
        //Arrange
        final CreateTransactionRequest input = new CreateTransactionRequest(
                10.00,
                idSender,
                1L,
                emailSender,
                null,
                documentSender,
                null
        );
        //Action
        ApplicationException error = Assertions.assertThrows(
                ApplicationException.class,
                () -> sut.createTransaction(input),
                "Tipo de erro esperado está incorreto");
        //Assert
        Assertions.assertEquals(
                ErrorEnum.TOO_MUCH_SENDER_ID_FIELDS,
                error.getErrorEnum(),
                "A mensagem de erro está incorreta.");
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "1;1;;;;",
            ";;joao@hotmail.com;joao@hotmail.com;;",
            ";;;;123456;123456",
    }
    )
    void createTransaction_validateFields_whenSameIdData(
            Long idSender,
            Long idReceiver,
            String emailSender,
            String emailReceiver,
            Long documentSender,
            Long documentReceiver){
        //Arrange
        final CreateTransactionRequest input = new CreateTransactionRequest(
                100.00,
                idSender,
                idReceiver,
                emailSender,
                emailReceiver,
                documentSender,
                documentReceiver
        );
        //Action
        ApplicationException error = Assertions.assertThrows(
                ApplicationException.class,
                () -> sut.createTransaction(input),
                "Tipo de erro esperado está incorreto");
        //Assert
        Assertions.assertEquals(
                ErrorEnum.SAME_RECEIVER_AND_SENDER_DATA,
                error.getErrorEnum(),
                "A mensagem de erro está incorreta.");
    }




}