package com.bbtransact.icp.api.controllers;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bbtransact.icp.api.exception.custom.InvalidDataFormatException;
import com.bbtransact.icp.api.services.ITransactionService;
import com.bbtransact.icp.api.util.DateUtil;

@RestController("TransactionController")
@RequestMapping("/user/v1")
public class TransactionController {

    @Autowired
    ITransactionService iTransactionService;

    @RequestMapping(value = "/accountholders/{cardHolderId}/transactions", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String gettransactions(@PathVariable("cardHolderId") final String cardHolderId,
            @RequestParam(value = "page", required = false, defaultValue = "0") final int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "0") final int pageSize,
            @RequestParam final Map<String, String> allRequestParams) throws JsonMappingException, IOException {
        if (!Pattern.matches("\\d+", cardHolderId)) {
            throw new InvalidDataFormatException();
        }
        final String requestTimeStamp = DateUtil.getUTCDate();
        final ObjectMapper om = new ObjectMapper();
        final String jsonobj = om.writeValueAsString(iTransactionService.getCardHolderTransactionsInfo(cardHolderId,
                requestTimeStamp, pageNumber, pageSize, allRequestParams));
        return jsonobj;
    }

    @RequestMapping(value = "/accountholders/{cardHolderId}/accounts/{accountId}/transactions", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String getAccountTransactions(@PathVariable("cardHolderId") final String cardHolderId,
            @PathVariable("accountId") final String accountId,
            @RequestParam(value = "page", required = false, defaultValue = "0") final int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "0") final int pageSize,
            @RequestParam final Map<String, String> allRequestParams) throws JsonMappingException, IOException {

        if (!Pattern.matches("\\d+", cardHolderId) || !Pattern.matches("\\d+", accountId)) {
            throw new InvalidDataFormatException();
        }
        final String requestTimeStamp = DateUtil.getUTCDate();
        final ObjectMapper om = new ObjectMapper();
        final String jsonobj = om.writeValueAsString(iTransactionService.getAccountIdTransactionsInfo(cardHolderId,
                accountId, requestTimeStamp, pageNumber, pageSize, allRequestParams));
        return jsonobj;

    }

}