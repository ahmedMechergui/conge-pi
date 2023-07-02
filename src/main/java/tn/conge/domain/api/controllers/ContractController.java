package tn.conge.domain.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.conge.domain.api.dtos.ContractDto;
import tn.conge.domain.api.mappers.ContractMapper;
import tn.conge.domain.entitites.Contract;
import tn.conge.domain.exceptions.exceptions.EntityNotFoundException;
import tn.conge.domain.services.ContractService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
public class ContractController {
    private final ContractService contractService;
    private final ContractMapper contractMapper;


    @PostMapping
    public ResponseEntity<ContractDto> addContract(@RequestBody ContractDto contractDto) {
        Contract contract = contractMapper.toEntity(contractDto);
        Contract savedContract = contractService.addContract(contract);
        ContractDto savedContractDto = contractMapper.toDto(savedContract);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContractDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractDto> getContractById(@PathVariable Long id) {
        Contract contract = contractService.getContractById(id);
        ContractDto contractDto = contractMapper.toDto(contract);
        return ResponseEntity.ok(contractDto);
    }

    @GetMapping
    public ResponseEntity<List<ContractDto>> getAllContracts() {
        List<Contract> contracts = contractService.getAllContracts();
        List<ContractDto> contractDtos = contractMapper.toDto(contracts);
        return ResponseEntity.ok(contractDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ContractDto> deleteContractById(@PathVariable Long id) {
        Contract contract = contractService.deleteContractById(id);
        ContractDto contractDto = contractMapper.toDto(contract);
        return ResponseEntity.ok(contractDto);
    }

    @PatchMapping
    public ResponseEntity<ContractDto> update(@RequestBody ContractDto contractDto) {
        if (Objects.isNull(contractDto.getId())) throw new EntityNotFoundException(Contract.class);
        Contract contract = this.contractService.getContractById(contractDto.getId());
        this.contractMapper.partialUpdate(contractDto, contract);
        contract = this.contractService.addContract(contract);
        return ResponseEntity.ok(this.contractMapper.toDto(contract));
    }
}

