package com.example.merchant.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindException;

import com.example.merchant.model.UserEntity;

/**
 * This class configures batch job
 *
 */
@Configuration
@EnableBatchProcessing
public class BatchJobConfig {

	@Autowired
	private JobBuilderFactory jobBuilder;

	@Autowired
	private StepBuilderFactory stepBuilder;

	@Value("classpath:/csv/users.csv")
	private Resource csvResource;

	@Autowired
	private DataSource dataSource;

	@Bean
	public Job readCSVFile() {
		return jobBuilder.get("readCSVFile").incrementer(new RunIdIncrementer()).start(step()).build();
	}

	@Bean
	public Step step() {
		return stepBuilder.get("step").<UserEntity, UserEntity>chunk(5).reader(reader()).processor(processor())
				.writer(writer()).build();
	}

	@Bean
	public ItemProcessor<UserEntity, UserEntity> processor() {
		return new UserProcessor();
	}

	// reading from csv file
	@Bean
	public FlatFileItemReader<UserEntity> reader() {
		FlatFileItemReader<UserEntity> itemReader = new FlatFileItemReader<>();
		itemReader.setLineMapper(lineMapper());
		itemReader.setLinesToSkip(1);
		itemReader.setResource(csvResource);
		return itemReader;
	}

	// convert csv rows to beans
	@Bean
	public LineMapper<UserEntity> lineMapper() {
		DefaultLineMapper<UserEntity> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] { "username", "password", "name", "description", "email", "status",
				"total_transaction_sum", "role" });
		lineTokenizer.setIncludedFields(new int[] { 0, 1, 2, 3, 4, 5, 6, 7 });
		BeanWrapperFieldSetMapper<UserEntity> fieldSetMapper = new BeanWrapperFieldSetMapper<UserEntity>();
		fieldSetMapper.setTargetType(UserEntity.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		fieldSetMapper = new BeanWrapperFieldSetMapper<UserEntity>() {
			{
				this.setTargetType(UserEntity.class);
			}

			@Override
			public UserEntity mapFieldSet(FieldSet fs) throws BindException {
				UserEntity tmp = super.mapFieldSet(fs);
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				tmp.setPassword(passwordEncoder.encode(tmp.getPassword()));
				return tmp;
			}

		};
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}

	// writting into mysql database
	@Bean
	public JdbcBatchItemWriter<UserEntity> writer() {
		JdbcBatchItemWriter<UserEntity> itemWriter = new JdbcBatchItemWriter<>();
		itemWriter.setDataSource(dataSource);
		itemWriter.setSql(
				"INSERT INTO merchant.users( username, password ,name, description, email, status, total_transaction_sum, `role`) VALUES (:username,:password,:name,:description,:email,:status,:total_transaction_sum,:role)");
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<UserEntity>());
		return itemWriter;
	}

}