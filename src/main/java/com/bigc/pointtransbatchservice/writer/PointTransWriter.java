package com.bigc.pointtransbatchservice.writer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
@StepScope
public class PointTransWriter implements ItemWriter<Long> {

    @Override
    public void write(List<? extends Long> list) throws Exception {
        log.info("MemberWriter {}",list);
    }
}
