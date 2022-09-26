package com.bigc.pointtransbatchservice.datasource.repo;

import com.bigc.pointtransbatchservice.datasource.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepo extends JpaRepository<Member, String> {
}
