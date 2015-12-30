package com.bbtransact.icp.api.resource.repo;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bbtransact.icp.api.resource.entity.CardHolderEntity;

@Configurable
public interface ICardHolderRepo extends PagingAndSortingRepository<CardHolderEntity, String> {

}
