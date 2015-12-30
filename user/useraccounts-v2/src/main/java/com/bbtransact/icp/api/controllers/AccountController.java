package com.bbtransact.icp.api.controllers;

import java.io.IOException;
import java.util.regex.Pattern;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bbtransact.icp.api.exception.custom.InvalidDataFormatException;
import com.bbtransact.icp.api.services.IAccountService;
import com.bbtransact.icp.api.util.DateUtil;

@RestController("AccountController")
@RequestMapping("/user/v1")
public class AccountController {

    @Autowired
    IAccountService iAccountService;

    @RequestMapping(value = "/accountholders/{cardHolderId}/accounts", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String getaccounts(@PathVariable("cardHolderId") final String cardHolderId)
            throws JsonMappingException, IOException {
        if (!Pattern.matches("\\d+", cardHolderId)) {
            throw new InvalidDataFormatException();
        }
        final String requestTimeStamp = DateUtil.getUTCDate();
        final ObjectMapper om = new ObjectMapper();
        final String jsonobj = om
                .writeValueAsString(iAccountService.getCardHolderAccountsInfo(cardHolderId, requestTimeStamp));
        return jsonobj;
    }

    @RequestMapping(value = "/accountholders/{cardHolderId}/accounts/{accountId}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String getaccounts(@PathVariable("cardHolderId") final String cardHolderId,
            @PathVariable("accountId") final String accountId) throws JsonMappingException, IOException {
        if (!Pattern.matches("\\d+", cardHolderId) || !Pattern.matches("\\d+", accountId)) {
            throw new InvalidDataFormatException();
        }
        final String requestTimeStamp = DateUtil.getUTCDate();
        final ObjectMapper om = new ObjectMapper();
        final String jsonobj = om.writeValueAsString(
                iAccountService.getCardHolderAccountInfo(cardHolderId, accountId, requestTimeStamp));
        return jsonobj;

    }

}