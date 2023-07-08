package tn.conge.domain.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.conge.domain.api.dtos.ReplacementDto;
import tn.conge.domain.api.dtos.ReplacementRequestDto;
import tn.conge.domain.api.mappers.ReplacementMapper;
import tn.conge.domain.api.mappers.ReplacementRequestMapper;
import tn.conge.domain.api.vm.ReplacementVM;
import tn.conge.domain.entitites.Replacement;
import tn.conge.domain.entitites.ReplacementRequest;
import tn.conge.domain.services.ReplacementRequestService;

import java.util.List;

@RestController
@RequestMapping("replacements/requests")
@RequiredArgsConstructor
public class ReplacementController {

    private final ReplacementRequestService replacementRequestService;
    private final ReplacementRequestMapper replacementRequestMapper;
    private final ReplacementMapper replacementMapper;

    @PostMapping("")
    public ResponseEntity<ReplacementRequestDto> submitReplacementRequest(@RequestBody ReplacementRequestDto replacementRequestDto) {
        ReplacementRequest replacementRequest = this.replacementRequestMapper.toEntity(replacementRequestDto);
        ReplacementRequest save = this.replacementRequestService.save(replacementRequest);
        ReplacementRequestDto savedReplacementRequestDto = this.replacementRequestMapper.toDto(save);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReplacementRequestDto);
    }

    @GetMapping("")
    public ResponseEntity<List<ReplacementRequestDto>> getAllReplacementRequests() {
        List<ReplacementRequest> requests = this.replacementRequestService.getAll();
        List<ReplacementRequestDto> requestDtos = this.replacementRequestMapper.toDto(requests);
        return ResponseEntity.ok(requestDtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReplacementRequestDto> getReplacementRequestById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(this.replacementRequestMapper.toDto(this.replacementRequestService.getById(id)));
    }

    @PatchMapping("{id}")
    public ResponseEntity<ReplacementRequestDto> patchReplacementRequest(@RequestBody ReplacementRequestDto dto,@PathVariable(name = "id") Long id) {
        ReplacementRequest entity = this.replacementRequestService.getById(id);
        ReplacementRequest replacementRequest = this.replacementRequestMapper.partialUpdate(dto, entity);
        return ResponseEntity.ok(this.replacementRequestMapper.toDto(this.replacementRequestService.save(replacementRequest)));
    }


    @PostMapping("accept")
    public ResponseEntity<ReplacementDto> acceptReplacement(@RequestBody ReplacementVM replacementVM) {
        Replacement replacement = this.replacementRequestService.acceptReplacementRequest(replacementVM.getReplacementRequestId(), replacementVM.getEmployeeId());
        return ResponseEntity.ok(this.replacementMapper.toDto(replacement));
    }

    @PostMapping("refuse")
    public ResponseEntity<Void> refuseReplacement(@RequestBody ReplacementVM replacementVM) {
        this.replacementRequestService.refuseReplacementRequest(replacementVM.getReplacementRequestId(), replacementVM.getEmployeeId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        this.replacementRequestService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
