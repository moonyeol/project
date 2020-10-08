package com.github.homework.program.repository;


import static com.github.homework.program.domain.QProgram.program;
import static com.github.homework.theme.domain.QTheme.theme;

import com.github.homework.program.domain.Program;
import com.github.homework.program.model.ProgramViewDto;
import com.github.homework.theme.domain.Theme;
import com.querydsl.jpa.JPQLQuery;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.repository.support.PageableExecutionUtils;

public class ProgramCustomRepositoryImpl extends QuerydslRepositorySupport implements ProgramCustomRepository {

    public ProgramCustomRepositoryImpl() {
        super(Program.class);
    }

    @Override
    public Page<ProgramViewDto> findBy(Pageable pageable) {
        JPQLQuery<Program> query = Objects.requireNonNull(getQuerydsl())
                .applyPagination(pageable, from(program)
                        .join(program.themes, theme).fetchJoin()
                        .distinct()
                );

        List<ProgramViewDto> collect = query.fetch().stream()
                .map(p -> new ProgramViewDto(
                        p.getId(),
                        p.getName(),
                        p.getIntroduction(),
                        p.getIntroductionDetail(),
                        p.getRegion(),
                        p.getThemes().stream().map(Theme::getName).collect(Collectors.joining(", "))))
                .collect(Collectors.toList());
        return PageableExecutionUtils.getPage(collect, pageable, query::fetchCount);
    }
}