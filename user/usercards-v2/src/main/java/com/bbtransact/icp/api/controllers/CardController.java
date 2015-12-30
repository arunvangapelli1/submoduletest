package com.bbtransact.icp.api.controllers;

import java.io.IOException;
import java.util.regex.Pattern;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bbtransact.icp.api.exception.custom.InvalidDataFormatException;
import com.bbtransact.icp.api.services.ICardService;
import com.bbtransact.icp.api.services.bean.v1.RequestVO;
import com.bbtransact.icp.api.util.DateUtil;

@RestController("CardController")
@RequestMapping("/user/v1")
public class CardController {

    @Autowired
    ICardService iCardService;

    @RequestMapping(value = "/accountholders/{cardHolderId}/cards", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String getCards(@PathVariable("cardHolderId") final String id) throws JsonMappingException, IOException {
        if (!Pattern.matches("\\d+", id)) {
            throw new InvalidDataFormatException();
        }
        final String requestTimeStamp = DateUtil.getUTCDate();
        final ObjectMapper om = new ObjectMapper();
        final String jsonObj = om.writeValueAsString(iCardService.getCardHolderCardsInfo(id, requestTimeStamp));
        return jsonObj;
    }

    @RequestMapping(value = "/accountholders/{cardHolderId}/cards/{cardId}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String getCard(@PathVariable("cardHolderId") final String cardHolderId,
            @PathVariable("cardId") final String cardId) throws JsonMappingException, IOException {
        if (!Pattern.matches("\\d+", cardHolderId) || !Pattern.matches("\\d+", cardId)) {
            throw new InvalidDataFormatException();
        }
        final String requestTimeStamp = DateUtil.getUTCDate();
        final ObjectMapper om = new ObjectMapper();
        final String jsonObj = om
                .writeValueAsString(iCardService.getCardHolderCardInfo(cardHolderId, cardId, requestTimeStamp));
        return jsonObj;
    }

    @RequestMapping(value = "/accountholders/{cardHolderId}/cards/{cardId}", produces = "application/json; charset=utf-8", method = RequestMethod.PUT)
    @ResponseBody
    public String putCard(@PathVariable("cardHolderId") final String cardHolderId,
            @PathVariable("cardId") final String cardId, @RequestBody final RequestVO requestVO)

                    throws JsonMappingException, IOException {
        if (!Pattern.matches("\\d+", cardHolderId) || !Pattern.matches("\\d+", cardId)) {
            throw new InvalidDataFormatException();
        }
        final String requestTimeStamp = DateUtil.getUTCDate();
        iCardService.updateCardInfo(cardId, requestVO.isActive());
        final ObjectMapper om = new ObjectMapper();
        final String jsonObj = om
                .writeValueAsString(iCardService.getCardHolderCardInfo(cardHolderId, cardId, requestTimeStamp));
        return jsonObj;
    }

}