package com.bbtransact.icp.api.services.bean.v1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bbtransact.icp.api.shared.bean.CardBean;
import com.bbtransact.icp.api.shared.bean.OperationsBean;

@Component
public class AllCardsVO {
    private List<CardBean> cards = new ArrayList<CardBean>();
    private OperationsBean operations;

    public List<CardBean> getCards() {
        return cards;
    }

    public void setCards(List<CardBean> cards) {
        this.cards = cards;
    }

    public OperationsBean getOperations() {
        return operations;
    }

    public void setOperations(OperationsBean operations) {
        this.operations = operations;
    }

}
