package com.bookstore;

import com.bookstore.entity.Book;
import com.bookstore.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            bookstoreService.insertBook();                                   
            bookstoreService.updateBook();                                   
        };
    }
}

/*
 * How To Calculate Entity Persistent Property Via Hibernate @Generated

Description: This application is an example of calculating an entity persistent property at INSERT and/or UPDATE time via Hibernate, @Generated.

Key points:

Calculate at INSERT time:

annotate the corresponding persistent field with @Generated(value = GenerationTime.INSERT)
annotate the corresponding persistent field with @Column(insertable = false)
Calculate at INSERT and UPDATE time:

annotate the corresponding persistent field with @Generated(value = GenerationTime.ALWAYS)
annotate the corresponding persistent field with @Column(insertable = false, updatable = false)
Further, apply:

Method 1:

if the database schema is generated via JPA annotations (not recommended) then use columnDefinition element of @Column to specify as an SQL query expression the formula for calculating the persistent property
Method 2:

if the database schema is not generated via JPA annotations (recommended way) then add the formula as part of schema in CREATE TABLE
Note: In production, you should not rely on columnDefinition. You should disable hibernate.ddl-auto (by omitting it) or set it to validate, and add the SQL query expression in CREATE TABLE (in this application, check the discount column in CREATE TABLE, file schema-sql.sql). Nevertheless, not even schema-sql.sql is ok in production. The best way is to rely on Flyway or Liquibase.
 */
