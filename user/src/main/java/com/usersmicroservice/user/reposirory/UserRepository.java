package com.usersmicroservice.user.reposirory;

import com.usersmicroservice.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByCompanyId(UUID companyId);

    @Modifying
    @Query("DELETE FROM User u WHERE u.companyId = :companyId")
    void deleteAllByCompanyId(@Param("companyId") UUID companyId);
}
