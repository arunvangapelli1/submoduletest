package com.bbtransact.icp.api.resource.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bbtransact.icp.api.exception.custom.AccountTransactionsNotFoundException;
import com.bbtransact.icp.api.exception.custom.CardHolderNotFoundException;
import com.bbtransact.icp.api.exception.custom.TransactionsNotFoundException;
import com.bbtransact.icp.api.resource.entity.AccountEntity;
import com.bbtransact.icp.api.resource.entity.AccountEntity_;
import com.bbtransact.icp.api.resource.entity.CardEntity;
import com.bbtransact.icp.api.resource.entity.CardHolderEntity;
import com.bbtransact.icp.api.resource.entity.CardHolderEntity_;
import com.bbtransact.icp.api.resource.entity.MerchantEntity;
import com.bbtransact.icp.api.resource.entity.MerchantEntity_;
import com.bbtransact.icp.api.resource.entity.PlanEntity;
import com.bbtransact.icp.api.resource.entity.TransactionEntity;
import com.bbtransact.icp.api.resource.entity.TransactionEntity_;
import com.bbtransact.icp.api.resource.entity.TransactionEntryEntity;
import com.bbtransact.icp.api.resource.entity.TransactionEntryEntity_;
import com.bbtransact.icp.api.resource.repo.ICardHolderRepo;
import com.bbtransact.icp.api.shared.bean.AccountBean;
import com.bbtransact.icp.api.shared.bean.MerchantBean;
import com.bbtransact.icp.api.shared.bean.TransactionBean;
import com.bbtransact.icp.api.shared.bean.TransactionEntryBean;
import com.bbtransact.icp.api.util.DateUtil;;

/**
 * @author grini
 */
@Component
public class TransactionHelper {

    @Autowired
    private ICardHolderRepo cardHolder;
    @Autowired
    EntityManagerFactory    entityManagerFactory;

    /**
     * This method is used to get the transactions for a particular carholderId.
     *
     * @param cardHolderId
     * @param pageNumber
     * @param pageSize
     * @param allRequestParams
     * @return Transaction List
     */
    public List<TransactionBean> getCardHolderTransactions(String cardHolderId, int pageNumber, int pageSize,
            Map<String, String> allRequestParams) {

        CardHolderEntity cardHolderInfo = cardHolder.findOne(cardHolderId);
        if (cardHolderInfo == null) {
            throw new CardHolderNotFoundException(cardHolderId);
        }

        TransactionBean transactionInfo = null;
        List<TransactionBean> transactionList = new ArrayList<TransactionBean>();

        List<TransactionEntryEntity> transactions = createCriteriaQuery(cardHolderId, null, pageNumber, pageSize,
                allRequestParams);

        Iterator<TransactionEntryEntity> it = transactions.iterator();

        while (it.hasNext()) {
            TransactionEntryEntity transactionEntryEntity = it.next();
            TransactionEntity transactionEntity = transactionEntryEntity.getTransactionEntity();
            CardEntity cardEntity = transactionEntity.getCardEntity();
            AccountEntity accountEntity = transactionEntryEntity.getAccountEntity();
            PlanEntity planEntity = accountEntity.getPlanEntity();
            MerchantEntity merchantEntity = transactionEntity.getMerchantEntity();

            transactionInfo = new TransactionBean();
            transactionInfo.setId(transactionEntity.getId());
            transactionInfo.setTransactionDateTime(DateUtil.getUTCDateFormat(transactionEntity.getDate()));
            transactionInfo.setTranactionTypeCode("INWORK");
            transactionInfo.setSettled(transactionEntity.isSettled());
            if (transactionEntity.isSettled()) {
                if (transactionEntity.getSettleddate() == null) {
                    transactionInfo.setSettleDateTime("");

                } else {
                    transactionInfo.setSettleDateTime(DateUtil.getUTCDateFormat(transactionEntity.getSettleddate()));
                }
            } else {
                transactionInfo.setSettleDateTime("pending for settlement");
            }

            MerchantBean merchant = new MerchantBean();

            if (null != merchantEntity) {
                merchant.setId(merchantEntity.getId());
            } else {
                merchant.setId("");

            }
            if (merchantEntity != null) {
                merchant.setName(merchantEntity.getName());
            } else {
                merchant.setName("");
            }

            transactionInfo.setMerchant(merchant);
            String tempCardNumber = null;
            if (null != cardEntity) {

                if (cardEntity.getCardnumber() != null) {
                    tempCardNumber = cardEntity.getCardnumber();
                } else if (cardEntity.getId() != null) {
                    tempCardNumber = cardEntity.getId();
                } else {
                    tempCardNumber = "XXXX";
                }
            } else {
                tempCardNumber = "XXXX";
            }

            if (tempCardNumber.length() <= 3) {
                String tempCardNumber4 = "XXX" + tempCardNumber;
                transactionInfo.setCardNumberLast4(tempCardNumber4.substring(tempCardNumber4.length() - 4));

            } else {
                transactionInfo.setCardNumberLast4(tempCardNumber.substring(tempCardNumber.length() - 4));
            }

            TransactionEntryBean transactionEntry = new TransactionEntryBean();
            transactionEntry.setCreditDebitType(transactionEntryEntity.getDebit());

            transactionEntry.setCurrencyCodeAlpha3("INWORK");
            transactionEntry.setCurrencyCodeNumeric("INWORK");

            transactionEntry.setAmount(transactionEntryEntity.getAmount());
            AccountBean account = new AccountBean();
            account.setId(accountEntity.getId());
            String accountType = null;
            if (accountEntity.getAccounttype().equals("PTS")) {
                accountType = "POINT";
            } else {
                accountType = accountEntity.getAccounttype();
            }
            account.setAccountType(accountType);

            account.setName(planEntity.getName());

            transactionEntry.setAccount(account);
            transactionInfo.setTransactionEntry(transactionEntry);

            transactionList.add(transactionInfo);

        }

        // }
        if (transactionList.isEmpty()) {
            throw new TransactionsNotFoundException(cardHolderId);

        }
        return transactionList;
    }

    /**
     * This method is used to retrieve transaction for an account associated to
     * a particular card id.
     *
     * @param cardHolderId
     * @param accountId
     * @param pageNumber
     * @param pageSize
     * @param allRequestParams
     * @return Transaction List
     */
    public List<TransactionBean> getAccountIdTransactions(String cardHolderId, String accountId, int pageNumber,
            int pageSize, Map<String, String> allRequestParams) {
        // TODO Auto-generated method stub
        CardHolderEntity cardHolderInfo = cardHolder.findOne(cardHolderId);
        if (cardHolderInfo == null) {
            throw new CardHolderNotFoundException(cardHolderId);

        }
        TransactionBean transactionInfo = null;
        List<TransactionBean> transactionList = new ArrayList<TransactionBean>();

        List<TransactionEntryEntity> transactions = createCriteriaQuery(cardHolderId, accountId, pageNumber, pageSize,
                allRequestParams);

        Iterator<TransactionEntryEntity> it = transactions.iterator();

        while (it.hasNext()) {
            TransactionEntryEntity transactionEntryEntity = it.next();
            TransactionEntity transactionEntity = transactionEntryEntity.getTransactionEntity();

            CardEntity cardEntity = transactionEntity.getCardEntity();
            AccountEntity accountEntity = transactionEntryEntity.getAccountEntity();
            PlanEntity planEntity = accountEntity.getPlanEntity();
            MerchantEntity merchantEntity = transactionEntity.getMerchantEntity();

            transactionInfo = new TransactionBean();
            transactionInfo.setId(transactionEntity.getId());
            transactionInfo.setTransactionDateTime(DateUtil.getUTCDateFormat(transactionEntity.getDate()));
            transactionInfo.setTranactionTypeCode("INWORK");
            transactionInfo.setSettled(transactionEntity.isSettled());
            if (transactionEntity.isSettled()) {
                if (transactionEntity.getSettleddate() == null) {

                    transactionInfo.setSettleDateTime("");

                } else {
                    transactionInfo.setSettleDateTime(DateUtil.getUTCDateFormat(transactionEntity.getSettleddate()));
                }
            } else {
                transactionInfo.setSettleDateTime("pending for settlement");
            }

            MerchantBean merchant = new MerchantBean();

            if (null != merchantEntity) {
                merchant.setId(merchantEntity.getId());
            } else {

                merchant.setId("");
            }
            if (merchantEntity != null) {
                merchant.setName(merchantEntity.getName());
            } else {

                merchant.setName("");
            }

            transactionInfo.setMerchant(merchant);
            String tempCardNumber = null;
            if (null != cardEntity) {

                if (cardEntity.getCardnumber() != null) {
                    tempCardNumber = cardEntity.getCardnumber();
                } else if (cardEntity.getId() != null) {
                    tempCardNumber = cardEntity.getId();
                } else {
                    tempCardNumber = "XXXX";
                }
            } else {
                tempCardNumber = "XXXX";
            }

            if (tempCardNumber.length() <= 3) {
                String tempCardNumber4 = "XXX" + tempCardNumber;
                transactionInfo.setCardNumberLast4(tempCardNumber4.substring(tempCardNumber4.length() - 4));

            } else {
                transactionInfo.setCardNumberLast4(tempCardNumber.substring(tempCardNumber.length() - 4));
            }

            TransactionEntryBean transactionEntry = new TransactionEntryBean();
            transactionEntry.setCreditDebitType(transactionEntryEntity.getDebit());

            transactionEntry.setCurrencyCodeAlpha3("INWORK");
            transactionEntry.setCurrencyCodeNumeric("INWORK");

            transactionEntry.setAmount(transactionEntryEntity.getAmount());
            AccountBean account = new AccountBean();
            account.setId(accountEntity.getId());
            String accountType = null;
            if (accountEntity.getAccounttype().equals("PTS")) {
                accountType = "POINT";
            } else {
                accountType = accountEntity.getAccounttype();
            }
            account.setAccountType(accountType);

            account.setName(planEntity.getName());

            transactionEntry.setAccount(account);
            transactionInfo.setTransactionEntry(transactionEntry);

            transactionList.add(transactionInfo);

        }

        if (transactionList.isEmpty()) {
            throw new AccountTransactionsNotFoundException(accountId);

        }
        return transactionList;

    }

    /**
     * This method creates the query for the transaction.
     *
     * @param cardHolderId
     * @param accountId
     * @param pageNumber
     * @param pageSize
     * @param allRequestParams
     * @return Returns the TransactionEntryEntity
     */
    public List<TransactionEntryEntity> createCriteriaQuery(String cardHolderId, String accountId, int pageNumber,
            int pageSize, Map<String, String> allRequestParams) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<TransactionEntryEntity> cq = cb.createQuery(TransactionEntryEntity.class);
        // Root<CardHolderEntity> cardHolder = cq.from(CardHolderEntity.class);
        Root<TransactionEntryEntity> transactionEntry = cq.from(TransactionEntryEntity.class);

        Join<TransactionEntryEntity, TransactionEntity> transaction = transactionEntry
                .join(TransactionEntryEntity_.transactionEntity);

        List<Predicate> predicates = new ArrayList<Predicate>();
        // Default Predicates for transactions
        predicates
                .add(cb.equal(transactionEntry.get(TransactionEntryEntity_.cardHolderEntity).get(CardHolderEntity_.id),
                        cardHolderId));
        predicates.add(cb.equal(transactionEntry.get(TransactionEntryEntity_.valid), 'Y'));
        predicates.add(cb.equal(transaction.get(TransactionEntity_.retcode), "00"));

        // For Transaction API with acountId Path Variable
        if ((accountId != null) && (!accountId.trim().isEmpty())) {
            Join<TransactionEntryEntity, AccountEntity> account = transactionEntry
                    .join(TransactionEntryEntity_.accountEntity);
            predicates.add(cb.equal(account.get(AccountEntity_.id), accountId));
        }
        if (!allRequestParams.isEmpty()) {
            String startDate = allRequestParams.get("minDate");
            String endDate = allRequestParams.get("maxDate");
            String merchantName = allRequestParams.get("merchantName");
            String date = allRequestParams.get("date");

            // Filter for Transactions with specific Date or with Start/End
            // date.
            if ((date != null) && !date.trim().isEmpty()) {
                predicates.add(cb.between(transaction.get(TransactionEntity_.date), DateUtil.getSqlStartDate(date),
                        DateUtil.getSqlEndDate(date)));
            } else if ((startDate != null) && (!startDate.trim().isEmpty()) && (endDate != null)
                    && (!endDate.trim().isEmpty())) {
                predicates.add(cb.between(transaction.get(TransactionEntity_.date), DateUtil.getSqlStartDate(startDate),
                        DateUtil.getSqlEndDate(endDate)));
            } else if (((null == endDate) || (endDate.trim().isEmpty())) && (startDate != null)
                    && (!startDate.trim().isEmpty())) {
                predicates.add(cb.greaterThanOrEqualTo(transaction.get(TransactionEntity_.date),
                        DateUtil.getSqlStartDate(startDate)));
            } else if (((null == startDate) || (startDate.trim().isEmpty())) && (endDate != null)
                    && (!endDate.trim().isEmpty())) {
                predicates.add(cb.lessThanOrEqualTo(transaction.get(TransactionEntity_.date),
                        DateUtil.getSqlEndDate(endDate)));
            }

            // Filter by merchant name
            if (((merchantName != null) && !merchantName.trim().isEmpty())) {
                Join<TransactionEntity, MerchantEntity> merchant = transaction.join(TransactionEntity_.merchantEntity);
                predicates.add(
                        cb.like(cb.lower(merchant.get(MerchantEntity_.name)), "%" + merchantName.toLowerCase() + "%"));
            }
        }

        cq.select(transactionEntry).where(predicates.toArray(new Predicate[] {}));
        cq.orderBy(cb.desc(transaction.get(TransactionEntity_.date)));
        TypedQuery<TransactionEntryEntity> qry = entityManager.createQuery(cq);

        // For Paging of Transactions
        if ((pageNumber > 0) && (pageSize > 0)) {
            qry.setFirstResult((pageNumber - 1) * pageSize);
            qry.setMaxResults(pageSize);
        }
        return qry.getResultList();
    }
}
