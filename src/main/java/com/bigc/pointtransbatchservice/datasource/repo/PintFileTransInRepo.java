package com.bigc.pointtransbatchservice.datasource.repo;

import com.bigc.pointtransbatchservice.datasource.entities.PintFileTransIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PintFileTransInRepo extends JpaRepository<PintFileTransIn, Long> {
    List<PintFileTransIn> findByMsgId(Long msgId);
}
