package com.levymoreira.repository;

import com.levymoreira.domain.AccountName;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the AccountName entity.
 */
@SuppressWarnings("unused")
public interface AccountNameRepository extends JpaRepository<AccountName,Long> {

    @Query("select accountName from AccountName accountName where accountName.user.login = ?#{principal.username}")
    List<AccountName> findByUserIsCurrentUser();

}
