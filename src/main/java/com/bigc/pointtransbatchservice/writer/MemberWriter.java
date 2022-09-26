package com.bigc.pointtransbatchservice.writer;

import com.bigc.pointtransbatchservice.datasource.entities.Member;
import com.bigc.pointtransbatchservice.datasource.repo.MemberRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Slf4j
@StepScope
public class MemberWriter implements ItemWriter<String> {
    @Autowired
    private MemberRepo memberRepo;
    @Override
    public void write(List<? extends String> list) throws Exception {
        log.info("MemberWriter {}",list);
        Member member = new Member();
        member.setRowId(UUID.randomUUID().toString());
        member.setMemberFirstName("Lisa");
        member.setMemberLastName("Manoban");
        member.setAge("26");
        member.setSex("Male");
        this.memberRepo.save(member);
    }
}
