package com.levymoreira.repository;

import com.levymoreira.domain.InstallmentGroup;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the InstallmentGroup entity.
 */
@SuppressWarnings("unused")
public interface InstallmentGroupRepository extends JpaRepository<InstallmentGroup,Long> {

}
