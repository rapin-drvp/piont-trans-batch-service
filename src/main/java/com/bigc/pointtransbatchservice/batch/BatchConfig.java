package com.bigc.pointtransbatchservice.batch;

import com.bigc.pointtransbatchservice.datasource.entities.Member;
import com.bigc.pointtransbatchservice.processor.MemberProcessor;
import com.bigc.pointtransbatchservice.writer.MemberWriter;
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

    @Bean
    @StepScope
    public ItemStreamReader<Member> itemReader(@Value("#{jobParameters['type']}") String type) throws Exception {
        log.info("Job type: {}", type);
        JpaPagingItemReader<Member> reader = new JpaPagingItemReader<>();
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setQueryString("SELECT m FROM Member m");
        reader.setPageSize(100);
        reader.afterPropertiesSet();
        return reader;
    }

    @Bean
    public ItemProcessor<Member, String> itemProcessor() {
        return new MemberProcessor();
    }

    @Bean
    public ItemWriter<String> itemWriter() {
        return new MemberWriter();
    }

    @Bean
    public Step processLines(ItemReader<Member> reader, ItemProcessor<Member, String> processor, ItemWriter<String> writer) {
        return this.stepBuilderFactory.get("processCardExp").<Member, String>chunk(100)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    @Qualifier("cardExpJob")
    public Job cardExpJob() throws Exception {
        return this.jobBuilderFactory.get("cardExpJob")
                .start(processLines(itemReader(null), itemProcessor(), itemWriter()))
                .build();
    }

    private String createQueryString() {
        return String.format("SELECT m FROM Member");
    }
}

/*
package com.bigc.pointtransbatchservice.batch;

import com.bigc.pointtransbatchservice.Tasklet.CheckMemberTasklet;
import com.bigc.pointtransbatchservice.listener.JobResultListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class BatchConfig {
    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private JobResultListener jobResultListener;
    private CheckMemberTasklet checkMemberTasklet;

    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                       JobResultListener jobResultListener, CheckMemberTasklet checkMemberTasklet
                       )
    {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.jobResultListener = jobResultListener;
        this.checkMemberTasklet = checkMemberTasklet;
    }

    @Bean
    public Job job() {
        Flow checkMember = new FlowBuilder<SimpleFlow>("CHECK_MEMBER")
                .start(this.stepBuilderFactory.get("CheckMemberStep")
                        .tasklet(checkMemberTasklet)
                        .build())
                .build();
        */
/**Flow readCsv = new FlowBuilder<SimpleFlow>("READ_CSV")
                .start(this.stepBuilderFactory.get("ReadCsvStep")
                        .tasklet(readCsvTasklet)
                        .build())
                .build();
        Flow insertMember = new FlowBuilder<SimpleFlow>("INSERT_MEMBER")
                .start(this.stepBuilderFactory.get("InsertMemberStep")
                        .tasklet(insertMemberTasklet)
                        .build())
                .build();*//*


        Flow insert_member_flow = new FlowBuilder<SimpleFlow>("INSERT_MEMBER_FLOW")
                .start(checkMember)
                //.next(readCsv)
                //.next(insertMember)
                .build();

        return this.jobBuilderFactory.get("INSERT_MEMBER_JOB")
                .incrementer(new RunIdIncrementer())
                .start(insert_member_flow)
                .build()
                .listener(jobResultListener)
                .build();
    }
}*/
