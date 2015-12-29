package com.bbtransact.icp.api.resource.repo;

import org.springframework.data.repository.CrudRepository;

import com.bbtransact.icp.api.resource.entity.AccountEntity;

public interface IAllAccountsRepo extends CrudRepository<AccountEntity, String> {

}
