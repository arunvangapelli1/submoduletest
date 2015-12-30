package com.bbtransact.icp.api.resource.repo;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bbtransact.icp.api.resource.entity.CardEntity;;

@Configurable
public interface ICardRepo extends PagingAndSortingRepository<CardEntity, String> {

}
