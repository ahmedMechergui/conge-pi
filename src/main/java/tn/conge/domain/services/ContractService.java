package tn.conge.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.conge.domain.entitites.Contract;
import tn.conge.domain.exceptions.exceptions.EntityNotFoundException;
import tn.conge.domain.repositories.ContractRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;

    public Contract addContract(Contract contract) {
        contract.setWrittenBy(contract.getEmployee());
        return this.contractRepository.save(contract);
    }

    public Contract getContractById(Long id) {
        return this.contractRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Contract.class));
    }

    public List<Contract> getAllContracts() {
        return this.contractRepository.findAll();
    }

    public Contract deleteContractById(Long id) {
        Contract contract = this.getContractById(id);
        this.contractRepository.deleteById(id);
        return contract;
    }
}
