package com.spring.techpractica.core.SocialAccount;

import com.spring.techpractica.core.SocialAccount.Entity.SocialAccount;
import com.spring.techpractica.core.SocialAccount.model.SocialAccountId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialAccountRepository extends JpaRepository<SocialAccount, SocialAccountId> {

}
