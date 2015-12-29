package com.bbtransact.icp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("NewCardController")

public class NewCardController {

    @RequestMapping(value = "/user", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String getCards() {

        String response = "in cards";
        System.out.println("in /user");
        return response;
    }
}