package com.bigc.pointtransbatchservice.batch;

import com.bigc.pointtransbatchservice.common.utils.BatchDateUtil;
import com.bigc.pointtransbatchservice.datasource.entities.PinMsgIn;
import com.bigc.pointtransbatchservice.processor.PointTransProcessor;
import com.bigc.pointtransbatchservice.writer.PointTransWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.Date;

@Slf4j
@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private BatchDateUtil batchDateUtil;

    @Bean
    @StepScope
    public ItemStreamReader<PinMsgIn> itemReader(@Value("#{jobParameters['type']}") String type) throws Exception {
        JpaPagingItemReader<PinMsgIn> reader = new JpaPagingItemReader<>();
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setQueryString(this.createQueryString());
        reader.setPageSize(100);
        reader.afterPropertiesSet();
        return reader;
    }

    @Bean
    public ItemProcessor<PinMsgIn, Long> itemProcessor() {
        return new PointTransProcessor();
    }

    @Bean
    public ItemWriter<Long> itemWriter() {
        return new PointTransWriter();
    }

    @Bean
    public Step processLines(ItemReader<PinMsgIn> reader,
                             ItemProcessor<PinMsgIn, Long> processor,
                             ItemWriter<Long> writer) {
        return this.stepBuilderFactory.get("insertPointTransJob").<PinMsgIn, Long>chunk(100)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    @Qualifier("insertPointTransJob")
    public Job insertPointTransJob() throws Exception {
        return this.jobBuilderFactory.get("insertPointTransJob")
                .start(processLines(itemReader(null), itemProcessor(), itemWriter()))
                .build();
    }

    private String createQueryString() throws Exception {
        String createDate = batchDateUtil.getDateNowStr();
        return String.format("SELECT pt FROM PinMsgIn pt\n" +
                "WHERE pt.status ='SW'\n" +
                "AND pt.msgType ='PT'\n" +
                "AND TRUNC(pt.createDate) = TO_DATE('"+createDate+"','YYYY-MM-DD')");
    }
}
