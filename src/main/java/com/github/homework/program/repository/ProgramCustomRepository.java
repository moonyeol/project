package com.github.homework.program.repository;

import com.github.homework.program.domain.Program;
import com.github.homework.program.model.ProgramViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProgramCustomRepository {

    Page<ProgramViewDto> findBy(Pageable pageable);


    //@Query(value = "SELECT p FROM Program p JOIN FETCH p.themes t ORDER BY p.views DESC LIMIT 10",nativeQuery = true)
    public List<ProgramViewDto> getRank();

    //@Query(value = "SELECT p FROM Program p WHERE P.name = name JOIN FETCH p.themes t ORDER BY p.views DESC LIMIT 10",nativeQuery = true)
    public List<ProgramViewDto> findByName(String name);
}