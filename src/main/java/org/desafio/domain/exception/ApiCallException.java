package org.desafio.domain.exception;

import jakarta.json.JsonObject;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiCallException extends Exception {}