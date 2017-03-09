package com.levymoreira.repository;

import com.levymoreira.domain.AccountType;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the AccountType entity.
 */
@SuppressWarnings("unused")
public interface AccountTypeRepository extends JpaRepository<AccountType,Long> {

}
