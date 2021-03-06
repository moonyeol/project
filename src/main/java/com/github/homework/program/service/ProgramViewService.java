package com.github.homework.program.service;

import com.github.homework.program.domain.Program;
import com.github.homework.program.exception.ProgramNotFoundException;
import com.github.homework.program.model.ProgramViewDto;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProgramViewService {

    Optional<ProgramViewDto> getBy(Long id);

    List<ProgramViewDto> getByName(String name);

    Page<ProgramViewDto> pageBy(Pageable pageable);

    List<ProgramViewDto> getRank();
}
