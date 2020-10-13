package com.github.homework.program.service;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

import com.github.homework.program.domain.Program;
import com.github.homework.program.model.ProgramViewDto;
import com.github.homework.program.repository.ProgramRepository;
import com.github.homework.theme.domain.Theme;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
class ProgramViewServiceImplTest {

    @Mock
    private ProgramRepository programRepository;

    @InjectMocks
    private ProgramViewServiceImpl programViewService;

    @Test
    @DisplayName("프로그램이 한개 일때")
    void getByTest() {
        //given
        Program program = Program.builder()
                .name("name")
                .introduction("introduction")
                .introductionDetail("introductionDetail")
                .region("region")
                .themes(Set.of(new Theme("theme"), new Theme("theme2")))
                .build();

        given(this.programRepository.findById(1L)).willReturn(Optional.of(program));
        //when
        Optional<ProgramViewDto> optionalProgramViewDto = this.programViewService.getBy(1L);
        //then
        then(optionalProgramViewDto).hasValueSatisfying(programViewDto -> {
                    then(programViewDto.getName()).isEqualTo("name");
                    then(programViewDto.getIntroduction()).isEqualTo("introduction");
                    then(programViewDto.getIntroductionDetail()).isEqualTo("introductionDetail");
                    then(programViewDto.getRegion()).isEqualTo("region");
                    then(programViewDto.getThemeName()).isEqualTo("theme2, theme");
                }
        );

    }

    @Test
    @DisplayName("프로그램이 여러개 일때")
    void pageByTest() {
        //given
        ProgramViewDto programViewDto = new ProgramViewDto(1L, "name", "introduction", "introductionDetail", "region",
                "theme",1);
        given(this.programRepository.findBy(PageRequest.of(0, 100)))
                .willReturn(
                        new PageImpl<>(List.of(programViewDto, programViewDto))
                );

        //when
        Page<ProgramViewDto> programViewDtos = this.programViewService.pageBy(PageRequest.of(0, 100));
        //then
        then(programViewDtos.getContent()).hasSize(2);
        then(programViewDtos.getContent()).allSatisfy(p -> {
                    then(p.getId()).isGreaterThan(0L);
                    then(p.getName()).isEqualTo("name");
                    then(p.getIntroduction()).isEqualTo("introduction");
                    then(p.getIntroductionDetail()).isEqualTo("introductionDetail");
                    then(p.getRegion()).isEqualTo("region");
                    then(p.getThemeName()).isEqualTo("theme");
                }
        );
    }

    @Test
    @DisplayName("프로그램 이름으로 조회 일때")
    void getByNameTest() {
        //given
        Program program = Program.builder()
                .name("name")
                .introduction("introduction")
                .introductionDetail("introductionDetail")
                .region("region")
                .themes(Set.of(new Theme("theme"), new Theme("theme2")))
                .build();

        ProgramViewDto programViewDto = new ProgramViewDto(1L, "name", "introduction", "introductionDetail", "region",
                "theme",0);

        given(this.programRepository.findByName(program.getName())).willReturn( List.of(programViewDto));
        //when
        List<ProgramViewDto> listProgramViewDto = this.programViewService.getByName(program.getName());
        //then
        then(listProgramViewDto).hasSize(1);
        then(listProgramViewDto).allSatisfy(p -> {
                    then(p.getId()).isGreaterThan(0L);
                    then(p.getName()).isEqualTo("name");
                    then(p.getIntroduction()).isEqualTo("introduction");
                    then(p.getIntroductionDetail()).isEqualTo("introductionDetail");
                    then(p.getRegion()).isEqualTo("region");
                    then(p.getThemeName()).isEqualTo("theme");
                }
        );

    }

    @Test
    @DisplayName("조회수 순위 조회 일때")
    void getRankTest() {
        //given
        Program program = Program.builder()
                .name("name")
                .introduction("introduction")
                .introductionDetail("introductionDetail")
                .region("region")
                .themes(Set.of(new Theme("theme"), new Theme("theme2")))
                .build();

        ProgramViewDto programViewDto = new ProgramViewDto(1L, "name", "introduction", "introductionDetail", "region",
                "theme",0);

        given(this.programRepository.getRank()).willReturn( List.of(programViewDto));
        //when
        List<ProgramViewDto> listProgramViewDto = this.programViewService.getRank();
        //then
        then(listProgramViewDto).hasSize(1);
        then(listProgramViewDto).allSatisfy(p -> {
                    then(p.getId()).isGreaterThan(0L);
                    then(p.getName()).isEqualTo("name");
                    then(p.getIntroduction()).isEqualTo("introduction");
                    then(p.getIntroductionDetail()).isEqualTo("introductionDetail");
                    then(p.getRegion()).isEqualTo("region");
                    then(p.getThemeName()).isEqualTo("theme");
                }
        );

    }

}