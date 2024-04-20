package org.desafio.infra.rest.client;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.desafio.domain.enumeration.ErrorEnum;
import org.desafio.domain.exception.ApiCallException;
import org.desafio.infra.rest.dto.AuthorizationResponse;
import org.desafio.infra.rest.dto.NotifyUserResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.ClientWebApplicationException;

import java.util.UUID;

@ApplicationScoped
public class ApplicationRestClientImpl implements ApplicationRestClient {

    private final AuthorizationRestClient authClient;

    private final NotificationRestClient notificationClient;

    @Inject
    public ApplicationRestClientImpl(@RestClient AuthorizationRestClient authClient,@RestClient NotificationRestClient notificationClient) {
        this.authClient = authClient;
        this.notificationClient = notificationClient;
    }

    @Override
    public AuthorizationResponse authorizeTransaction(UUID transactionId) throws ApiCallException {
        Log.info("Calling authorization service api...");

        try{
            return authClient.authorizeTransaction().getEntity();
        }catch (ClientWebApplicationException exception){
            Log.error("Error on call authorizations service!", exception);
            throw new ApiCallException(ErrorEnum.ERROR_ON_AUTHORIZATION_SERVICE_CALL);
        }
    }

    @Override
    public NotifyUserResponse notifyUser(Long userId) throws ApiCallException {
        try{
            return notificationClient.notifyUser().getEntity();
        }catch (ClientWebApplicationException exception){
            Log.error("Error on call notification service!", exception);
            throw new ApiCallException(ErrorEnum.ERROR_ON_NOTIFICATION_SERVICE_CALL);
        }
    }
}
