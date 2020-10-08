package com.github.homework.config;

import com.github.homework.program.model.ProgramSaveDto;
import com.github.homework.program.service.ProgramSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ApplicationRunner applicationRunner() {
        return new ApplicationRunner() {
            @Autowired
            ProgramSaveService programSaveService;

            @Override
            public void run(ApplicationArguments args) throws Exception {
                ProgramSaveDto programSaveDto = ProgramSaveDto.builder().name("12313")
                        .region("1231231").themeName("ddddd")
                        .introduction("ffsdf")
                        .introductionDetail(
                                "프로그램")
                        .build();
                programSaveService.saveProgram(programSaveDto);
            }
        };
    }
}