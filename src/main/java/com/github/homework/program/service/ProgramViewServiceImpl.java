package com.github.homework.program.service;

import com.github.homework.program.domain.Program;
import com.github.homework.program.exception.ProgramNotFoundException;
import com.github.homework.program.model.ProgramViewDto;
import com.github.homework.program.repository.ProgramRepository;
import com.github.homework.theme.domain.Theme;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProgramViewServiceImpl implements ProgramViewService {

    private final ProgramRepository programRepository;

    @Override
    public Optional<ProgramViewDto> getBy(Long id) {
        return programRepository.findById(id).map(p ->
            new ProgramViewDto(
                p.getId(),
                p.getName(),
                p.getIntroduction(),
                p.getIntroductionDetail(),
                p.getRegion(),
                p.getThemes().stream().map(Theme::getName).collect(Collectors.joining(", "))
            )
        );
    }

    @Override
    public Page<ProgramViewDto> pageBy(Pageable pageable) {
        return programRepository.findBy(pageable);
    }

    @Override
    @Transactional
    public void updateViews(Long id) throws ProgramNotFoundException  {
        Program program = this.programRepository.findById(id).orElseThrow(
                ProgramNotFoundException::new);
        program.updateViews();
    }

}
