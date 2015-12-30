package com.bbtransact.icp.api.exception;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bbtransact.icp.api.exception.bean.v1.ErrorBean;
import com.bbtransact.icp.api.exception.bean.v1.OperationsErrorBean;
import com.bbtransact.icp.api.exception.custom.AccountIdNotFoundException;
import com.bbtransact.icp.api.exception.custom.AccountTransactionsNotFoundException;
import com.bbtransact.icp.api.exception.custom.AccountsNotFoundException;
import com.bbtransact.icp.api.exception.custom.CardHolderNotFoundException;
import com.bbtransact.icp.api.exception.custom.CardIdNotFoundException;
import com.bbtransact.icp.api.exception.custom.CardNotFoundException;
import com.bbtransact.icp.api.exception.custom.DatePatternMismatchException;
import com.bbtransact.icp.api.exception.custom.InvalidDataFormatException;
import com.bbtransact.icp.api.exception.custom.TransactionsNotFoundException;
import com.bbtransact.icp.api.localization.MessageHandler;
import com.bbtransact.icp.api.util.DateUtil;

@EnableWebMvc
@ControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageHandler messageHandler;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = CardHolderNotFoundException.class)
    public @ResponseBody OperationsErrorBean cardHolderNotFoundException(HttpServletRequest request, Exception ex) {
        String timeStamp = DateUtil.getUTCDate();
        String errormessage = messageHandler.localizeErrorMessage("error.card.number.not.found");
        OperationsErrorBean errorResponse = errorResponse(ex.getMessage() + " " + errormessage,
                "CardHolderNotFoundException.class", request.getRequestURI(), timeStamp, "1008");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = CardNotFoundException.class)
    public @ResponseBody OperationsErrorBean cardNotFoundException(HttpServletRequest request, Exception ex) {
        String timeStamp = DateUtil.getUTCDate();
        String errormessage = messageHandler.localizeErrorMessage("error.card.not.found.for.card.holder.id");
        OperationsErrorBean errorResponse = errorResponse(ex.getMessage() + " " + errormessage,
                "CardNotFoundException.class", request.getRequestURI(), timeStamp, "1008");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = AccountsNotFoundException.class)
    public @ResponseBody OperationsErrorBean AccountsNotFoundException(HttpServletRequest request, Exception ex) {
        String timeStamp = DateUtil.getUTCDate();
        String errormessage = messageHandler.localizeErrorMessage("error.accounts.not.found.for.card.holder.id");
        OperationsErrorBean errorResponse = errorResponse(errormessage, "AccountsNotFoundException.class",
                request.getRequestURI(), timeStamp, "1008");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = TransactionsNotFoundException.class)
    public @ResponseBody OperationsErrorBean TransactionsNotFoundException(HttpServletRequest request, Exception ex) {
        String timeStamp = DateUtil.getUTCDate();
        messageHandler.localizeErrorMessage("error.Transactions.not.found.for.card.holder.id");
        OperationsErrorBean errorResponse = errorResponse(
                "No Transactions found for Accountholder" + " " + ex.getMessage(),
                "TransactionsNotFoundException.class", request.getRequestURI(), timeStamp, "1008");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = AccountTransactionsNotFoundException.class)
    public @ResponseBody OperationsErrorBean AccountTransactionsNotFoundException(HttpServletRequest request,
            Exception ex) {
        String timeStamp = DateUtil.getUTCDate();
        String errormessage = messageHandler.localizeErrorMessage("error.Transactions.not.found.for.account.id");
        OperationsErrorBean errorResponse = errorResponse(ex.getMessage() + " " + errormessage,
                "AccountTransactionsNotFoundException.class", request.getRequestURI(), timeStamp, "1008");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = AccountIdNotFoundException.class)
    public @ResponseBody OperationsErrorBean accountIdNotFoundException(HttpServletRequest request, Exception ex) {
        String timeStamp = DateUtil.getUTCDate();
        String errormessage = messageHandler.localizeErrorMessage("error.account.number.not.found");
        OperationsErrorBean errorResponse = errorResponse(ex.getMessage() + " " + errormessage,
                "AccountIdNotFoundException.class", request.getRequestURI(), timeStamp, "1008");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = CardIdNotFoundException.class)
    public @ResponseBody OperationsErrorBean cardIdNotFoundException(HttpServletRequest request, Exception ex) {
        String timeStamp = DateUtil.getUTCDate();
        String errormessage = messageHandler.localizeErrorMessage("error.card.number.not.found");
        OperationsErrorBean errorResponse = errorResponse(ex.getMessage() + " " + errormessage,
                "CardIdNotFoundException.class", request.getRequestURI(), timeStamp, "1008");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = DatePatternMismatchException.class)
    public @ResponseBody OperationsErrorBean datePatternMismatchException(HttpServletRequest request, Exception ex) {
        String timeStamp = DateUtil.getUTCDate();
        messageHandler.localizeErrorMessage("error.account.number.not.found");
        OperationsErrorBean errorResponse = errorResponse("The date can only be in yyyy-mm-dd format",
                "DatePatternMismatchException.class", request.getRequestURI(), timeStamp, "1008");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = InvalidDataFormatException.class)
    public @ResponseBody OperationsErrorBean invalidDataFormatException(HttpServletRequest request, Exception ex) {
        String timeStamp = DateUtil.getUTCDate();
        messageHandler.localizeErrorMessage("error.card.number.not.found");
        OperationsErrorBean errorResponse = errorResponse(
                "The userContextId header value is in an invalid data format.", "InvalidDataFormatException.class",
                request.getRequestURI(), timeStamp, "2003");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NullPointerException.class)
    public @ResponseBody OperationsErrorBean handleNullPointerException(HttpServletRequest request,
            NullPointerException ex) {
        String timeStamp = DateUtil.getUTCDate();
        messageHandler.localizeErrorMessage("error.npe");
        OperationsErrorBean errorResponse = errorResponse("Custom Null Pointer", ex.toString(), request.getRequestURI(),
                timeStamp, "N/A");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = StringIndexOutOfBoundsException.class)
    public @ResponseBody OperationsErrorBean handleStringIndexOutOfBoundsException(HttpServletRequest request,
            StringIndexOutOfBoundsException ex) {
        String timeStamp = DateUtil.getUTCDate();
        messageHandler.localizeErrorMessage("error.illegalstate");
        OperationsErrorBean errorResponse = errorResponse("OOPS something is really bad", ex.toString(),
                request.getRequestURI(), timeStamp, "N/A");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = IllegalStateException.class)
    public @ResponseBody OperationsErrorBean handleillegalStateException(HttpServletRequest request,
            IllegalStateException ex) {
        String timeStamp = DateUtil.getUTCDate();
        String errormessage = messageHandler.localizeErrorMessage("error.illegalstate");
        OperationsErrorBean errorResponse = errorResponse(errormessage, ex.toString(), request.getRequestURI(),
                timeStamp, "N/A");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = IOException.class)
    public @ResponseBody OperationsErrorBean handleIOException(HttpServletRequest request, IOException ex) {
        String timeStamp = DateUtil.getUTCDate();
        String errormessage = messageHandler.localizeErrorMessage("error.exception");
        OperationsErrorBean errorResponse = errorResponse(errormessage, ex.toString(), request.getRequestURI(),
                timeStamp, "N/A");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = SQLException.class)
    public @ResponseBody OperationsErrorBean handleSQLException(HttpServletRequest request, SQLException ex) {
        String timeStamp = DateUtil.getUTCDate();
        messageHandler.localizeErrorMessage("error.exception");
        OperationsErrorBean errorResponse = errorResponse("SQL Exception Just occured", ex.toString(),
                request.getRequestURI(), timeStamp, "N/A");
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = Exception.class)
    public @ResponseBody OperationsErrorBean handleglobalException(HttpServletRequest request, Exception ex) {
        String timeStamp = DateUtil.getUTCDate();
        String errormessage = messageHandler.localizeErrorMessage("error.exception");
        OperationsErrorBean errorResponse = errorResponse(errormessage, ex.toString(), request.getRequestURI(),
                timeStamp, "N/A");
        return errorResponse;
    }

    public OperationsErrorBean errorResponse(String errorMessage, String exceptionType, String errorUrl,
            String timeStamp, String errorCode) {
        OperationsErrorBean operation = new OperationsErrorBean();
        ErrorBean error = new ErrorBean();
        List<ErrorBean> errors = new ArrayList<ErrorBean>();
        operation.setResult("ERROR");
        error.setCode("N/A");
        error.setMessage(errorMessage);
        error.setField(errorMessage);
        errors.add(error);
        operation.setErrors(errors);
        operation.setRequestTimeStampUtc(timeStamp);
        System.out.println("Date util>>> " + DateUtil.getUTCDate());
        operation.setResponseTimeStampUtc(DateUtil.getUTCDate());
        return operation;
    }
}