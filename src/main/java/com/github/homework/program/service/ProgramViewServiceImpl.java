package com.github.homework.program.service;

import com.github.homework.program.domain.Program;
import com.github.homework.program.exception.ProgramNotFoundException;
import com.github.homework.program.model.ProgramViewDto;
import com.github.homework.program.repository.ProgramRepository;
import com.github.homework.theme.domain.Theme;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProgramViewServiceImpl implements ProgramViewService {

    private final ProgramRepository programRepository;

    @Override
    public Optional<ProgramViewDto> getBy(Long id) {
        Optional<Program> program = programRepository.findById(id);
        program.get().updateViews();
        return program.map(p ->
            new ProgramViewDto(
                p.getId(),
                p.getName(),
                p.getIntroduction(),
                p.getIntroductionDetail(),
                p.getRegion(),
                p.getThemes().stream().map(Theme::getName).collect(Collectors.joining(", ")),
                p.getViews()
            )
        );
    }

    @Override
    public List<ProgramViewDto> getByName(String name) {
        return programRepository.findByName(name);

    }

    @Override
    public Page<ProgramViewDto> pageBy(Pageable pageable) {
        return programRepository.findBy(pageable);
    }

    @Override
    public List<ProgramViewDto> getRank(){
        return programRepository.getRank();

    }

}
