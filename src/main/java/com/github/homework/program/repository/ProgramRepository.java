package com.github.homework.program.repository;

import com.github.homework.program.domain.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long>, ProgramCustomRepository {

}