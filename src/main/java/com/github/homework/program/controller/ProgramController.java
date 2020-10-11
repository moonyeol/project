package com.github.homework.program.controller;

import com.github.homework.program.exception.ProgramNotFoundException;
import com.github.homework.program.model.ProgramSaveDto;
import com.github.homework.program.model.ProgramViewDto;
import com.github.homework.program.model.SimpleResponse;
import com.github.homework.program.service.ProgramSaveService;
import com.github.homework.program.service.ProgramViewService;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/programs")
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramViewService programViewService;
    private final ProgramSaveService programSaveService;

    @GetMapping
    public ResponseEntity<Page<ProgramViewDto>> pageBy(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
                    Pageable pageable) {
        return ResponseEntity.ok(this.programViewService.pageBy(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramViewDto> getBy(@PathVariable Long id) throws ProgramNotFoundException {
        Optional<ProgramViewDto> programViewDto = this.programViewService.getBy(id);
        return programViewDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<SimpleResponse> saveProgram(@RequestBody @Valid ProgramSaveDto
                                                              programSaveDto) {
        this.programSaveService.saveProgram(programSaveDto);
        return ResponseEntity.ok(new SimpleResponse(true, "저장 성공"));
    }

    @PutMapping
    public ResponseEntity<SimpleResponse> updateProgram(@RequestBody @Valid ProgramSaveDto
                                                                programSaveDto) {
        try {
            this.programSaveService.updateProgram(programSaveDto);
        } catch (ProgramNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new SimpleResponse(true, "수정 성공"));
    }

    @GetMapping("/rank")
    public ResponseEntity<List<ProgramViewDto>> pageByViews(){
        return ResponseEntity.ok(this.programViewService.getRank());
    }
}
