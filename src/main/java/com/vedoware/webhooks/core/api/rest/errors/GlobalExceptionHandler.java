package com.vedoware.webhooks.core.api.rest.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.vedoware.webhooks.core.api.exceptions.EscCoreApiException;
import com.vedoware.webhooks.core.api.rest.exceptions.NotificationProblemDetailsException;
import com.vedoware.webhooks.core.api.rest.operations.ProblemDetails;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = EscCoreApiException.class)
    public ResponseEntity<Object> handleBaseException(EscCoreApiException e, WebRequest request) {
        ProblemDetails problemDetails = new ProblemDetails();
        problemDetails.setDetails(e.getMessage());
        return handleExceptionInternal(e, problemDetails, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { NotificationProblemDetailsException.class })
    protected ResponseEntity<Object> handleConflict(NotificationProblemDetailsException ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getNotificationProblemDetails(), new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception e, WebRequest request) {
        ProblemDetails problemDetails = new ProblemDetails();
        problemDetails.setDetails(e.getMessage());
        return handleExceptionInternal(e, problemDetails, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
