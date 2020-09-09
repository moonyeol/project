package com.github.homework.program.service;

import com.github.homework.program.domain.Program;
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
    @Transactional
    public Optional<ProgramViewDto> getBy(Long id) {
        Optional<Program> byId = programRepository.findById(id);
        return byId.map(p ->
            new ProgramViewDto(
                p.getId(),
                p.getName(),
                p.getIntroduction(),
                p.getIntroductionDetail(),
                p.getRegion()
            )
        );
    }

    @Override
    public Page<ProgramViewDto> pageBy(Pageable pageable) {
        return programRepository.findBy(pageable);
    }
}
