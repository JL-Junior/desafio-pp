package org.desafio.presentation.context;

import jakarta.enterprise.context.RequestScoped;
import lombok.Getter;
import lombok.Setter;

@RequestScoped
@Getter
@Setter
public class ApplicationContext {
    boolean isUnauthorized = false;
    boolean isFailingNotification = false;
}
