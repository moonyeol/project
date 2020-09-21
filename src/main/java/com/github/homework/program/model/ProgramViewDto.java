package com.github.homework.program.model;

import com.github.homework.theme.domain.Theme;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class ProgramViewDto {

    private Long id;
    private String name;
    private String introduction;
    private String introductionDetail;
    private String region;
    private LinkedList<String> themeNames; // 추가 코드

    public ProgramViewDto(Long id, String name, String introduction, String introductionDetail, String region, Set<Theme> themes) {
        this.id = id;
        this.name = name;
        this.introduction = introduction;
        this.introductionDetail = introductionDetail;
        this.region = region;
        //추가 코드
        themeNames = new LinkedList<String>();
        themes.forEach(theme -> themeNames.add(theme.getName()));

    }
}
