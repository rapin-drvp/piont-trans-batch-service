package com.bigc.pointtransbatchservice.datasource.repo;

import com.bigc.pointtransbatchservice.datasource.entities.PinMsgIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PinMsgInRepo extends JpaRepository<PinMsgIn, Long> {
}
