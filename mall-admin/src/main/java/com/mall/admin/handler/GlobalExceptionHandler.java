package com.mall.admin.handler;

import com.mall.common.dto.ErrorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ErrorInfo validationExceptionHandler(
            HttpServletRequest httpServletRequest,
            ConstraintViolationException exception
    ){
        StringBuilder stringBuilder = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        for (ConstraintViolation constraintViolation : constraintViolations){
            stringBuilder.append(constraintViolation.getMessage());
            stringBuilder.append(" ");
        }

        return new ErrorInfo<>(
                ErrorInfo.PARAMS_ERROR,
                stringBuilder.toString(),
                null,
                httpServletRequest.getRequestURL().toString()
        );
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ErrorInfo bindExceptionHandler(
            HttpServletRequest request,
            BindException exception
    ){
        StringBuilder builder = new StringBuilder();
        List<ObjectError> allErrors = exception.getAllErrors();
        for (ObjectError objectError : allErrors){
            builder.append(objectError.getDefaultMessage());
            builder.append(" ");
        }

        return new ErrorInfo<>(
                ErrorInfo.PARAMS_ERROR,
                builder.toString(),
                null,
                request.getRequestURL().toString()
        );
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ErrorInfo missRequestParamExceptionHandler(
            HttpServletRequest request,
            MissingServletRequestParameterException exception
    ){
        StringBuilder builder = new StringBuilder();
        builder.append(exception.getLocalizedMessage());
        builder.append(exception.getMessage());

        return new ErrorInfo<>(
                ErrorInfo.PARAMS_ERROR,
                builder.toString(),
                null,
                request.getRequestURL().toString()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorInfo commonExceptionHandler(
            HttpServletRequest request,
            Exception exception
    ){
        exception.printStackTrace();
        return new ErrorInfo<>(
                ErrorInfo.ERROR,
                exception.getMessage(),
                null,
                request.getRequestURL().toString()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorInfo methodArgsNotValidExceptionHandler(
            HttpServletRequest request,
            MethodArgumentNotValidException exception
    ){
        StringBuilder stringBuilder = new StringBuilder();
        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
        for (ObjectError error : allErrors){
            stringBuilder.append(error.getDefaultMessage());
            stringBuilder.append(" ");
        }

        return new ErrorInfo<>(
                ErrorInfo.PARAMS_ERROR,
                stringBuilder.toString(),
                null,
                request.getRequestURL().toString()
        );
    }
}
