package com.msr.agenceloc.system.exception;

import com.msr.agenceloc.system.Result;
import com.msr.agenceloc.system.StatusCode;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Result handleObjectNotFoundException (ObjectNotFoundException exception){
        return new Result(false, StatusCode.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Result handleObjectNotFoundException (DataIntegrityViolationException exception){
        return new Result(false, StatusCode.INTERNAL_SERVER_ERREUR, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Result handleValidationException(MethodArgumentNotValidException exception){
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        Map<String, String> map = new HashMap<>(errors.size());
        errors.forEach(error->{
            String key = ((FieldError) error).getField();
            String val = error.getDefaultMessage();
            map.put(key,val);
        });

        return new Result(false,StatusCode.INVALID_ARGUMENTS,"Provided arguments are invalid, see data for details.",map);

    }

    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    Result handleAuthenticationException (Exception exception){
        return new Result(false, StatusCode.UNAUTHORIZED,"usename or password not correct" + exception.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    Result methodNotSupportedException (HttpRequestMethodNotSupportedException exception){
        return new Result(false, StatusCode.FORBIDEN,"La méthode et ce url  n'est pas géree par lapplication" + exception.getMessage());
    }

    /**
     * ceci est pour les utilisateur qui sont désactivé (exp:enable=0)
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(AccountStatusException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    Result handleAccountStatusException(AccountStatusException exception) {

        return new Result(false, StatusCode.UNAUTHORIZED,"user account is abnormal", exception.getMessage());
    }

    @ExceptionHandler(InvalidBearerTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    Result handleInvalidBearerTokenException(InvalidBearerTokenException exception) {
        return new Result(false, StatusCode.UNAUTHORIZED,"The access token provided is expired revoked,malforme, or invalid for other reasons", exception.getMessage());
    }


    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    Result handleAccessDeniedException(AccessDeniedException exception) {
        return new Result(false, StatusCode.FORBIDEN,"No perission", exception.getMessage());
    }

    @ExceptionHandler(ReservationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    Result handleReservationException(ReservationException exception) {
        return new Result(false, StatusCode.INTERNAL_SERVER_ERREUR, exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    Result handleOtherException(AccessDeniedException exception) {
        return new Result(false, StatusCode.INTERNAL_SERVER_ERREUR,"NServer interne erreur", exception.getMessage());
    }







}
