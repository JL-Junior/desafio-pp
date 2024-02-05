package org.desafio.presentation.filter;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import org.desafio.presentation.constants.Header;
import org.desafio.presentation.context.ApplicationContext;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;

import java.util.Objects;

@RequestScoped
public class RequestFilter {

    @Inject
    ApplicationContext context;

    @ServerRequestFilter(preMatching = true)
    public void getUnauthorizedHeader(ContainerRequestContext requestContext) {
        String header = requestContext.getHeaderString(Header.TRANSACTION_UNAUTHORIZED);

        if(Objects.isNull(header))
            return;

        boolean isUnauthorized = Boolean.parseBoolean(header);
        context.setUnauthorized(isUnauthorized);
    }
}
