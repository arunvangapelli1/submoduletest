package com.bbtransact.icp.api.controllers;

import java.io.IOException;
import java.util.regex.Pattern;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bbtransact.icp.api.exception.custom.InvalidDataFormatException;
import com.bbtransact.icp.api.services.ICardHolderIssuerService;
import com.bbtransact.icp.api.util.DateUtil;

@RestController("CardHolderController")
@RequestMapping("/user/v1")
public class CardHolderController {

    @Autowired
    ICardHolderIssuerService iCardHolderIssuerService;

    @RequestMapping(value = "/accountholders/{cardHolderId}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String getCardHolderIssuer(@PathVariable("cardHolderId") final String cardHolderId)
            throws JsonMappingException, IOException {
        if (!Pattern.matches("\\d+", cardHolderId)) {
            throw new InvalidDataFormatException();
        }
        final String requestTimeStamp = DateUtil.getUTCDate();
        final ObjectMapper om = new ObjectMapper();
        final String jsonobj = om
                .writeValueAsString(iCardHolderIssuerService.getCardHolderIssuer(cardHolderId, requestTimeStamp));
        return jsonobj;
    }

    @RequestMapping(value = "/accountholders", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String getCardHolderIssuers(@RequestHeader("userContextId") final String cardHolderId)
            throws JsonMappingException, IOException {
        if (!Pattern.matches("\\d+", cardHolderId)) {
            throw new InvalidDataFormatException();
        }
        final String requestTimeStamp = DateUtil.getUTCDate();
        final ObjectMapper om = new ObjectMapper();
        final String jsonobj = om
                .writeValueAsString(iCardHolderIssuerService.getCardHolderIssuer(cardHolderId, requestTimeStamp));
        return jsonobj;
    }
}