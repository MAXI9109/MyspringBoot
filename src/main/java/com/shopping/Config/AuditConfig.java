package com.shopping.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // 감시용 (지켜본다 )
public class AuditConfig {
    @Bean // AuditorAware 구현체를 Bean으로 등록할께요
    public AuditorAwareImpl auditorProvider(){
        return new AuditorAwareImpl() ;



    }
 }
