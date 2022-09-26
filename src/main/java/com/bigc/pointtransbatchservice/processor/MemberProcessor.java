package com.bigc.pointtransbatchservice.processor;

import com.bigc.pointtransbatchservice.datasource.entities.Member;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;

@StepScope
public class MemberProcessor implements ItemProcessor<Member, String> {

    @Override
    public String process(Member member) throws Exception {
        return member.getMemberFirstName();
    }
}
