package com.bigc.pointtransbatchservice.processor;

import com.bigc.pointtransbatchservice.datasource.entities.PinMsgIn;
import com.bigc.pointtransbatchservice.datasource.entities.PintFileTransIn;
import com.bigc.pointtransbatchservice.datasource.repo.PintFileTransInRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@StepScope
@Slf4j
public class PointTransProcessor implements ItemProcessor<PinMsgIn, Long> {

    @Autowired
    private PintFileTransInRepo pintFileTransInRepo;
    @Override
    public Long process(PinMsgIn pinMsgIn) throws Exception {
        List<PintFileTransIn> resultList = this.pintFileTransInRepo.findByMsgId(pinMsgIn.getMsgId());
        for (PintFileTransIn fileTransIn : resultList){
            log.info("## File name: {} Status: {} ",fileTransIn.getFileName(),fileTransIn.getStatus());
        }
        return pinMsgIn.getMsgId();
    }
}