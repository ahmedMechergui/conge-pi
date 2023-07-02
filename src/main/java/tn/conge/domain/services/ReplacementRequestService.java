package tn.conge.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import tn.conge.core.security.AuthFacade;
import tn.conge.domain.entitites.Employee;
import tn.conge.domain.entitites.Replacement;
import tn.conge.domain.entitites.ReplacementRequest;
import tn.conge.domain.enums.ReplacementRequestStatus;
import tn.conge.domain.enums.ReplacementStatus;
import tn.conge.domain.exceptions.exceptions.EntityNotFoundException;
import tn.conge.domain.repositories.EmployeeRepository;
import tn.conge.domain.repositories.ReplacementRequestRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReplacementRequestService {

    private final ReplacementRequestRepository replacementRequestRepository;
    private final EmployeeRepository employeeRepository;

    private final AuthFacade authFacade;

    public ReplacementRequest save(ReplacementRequest replacementRequest) {
        return this.replacementRequestRepository.save(replacementRequest);
    }

    public List<ReplacementRequest> getAll() {
        return this.replacementRequestRepository.findAll();
    }

    public ReplacementRequest getById(Long id){
        return this.replacementRequestRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ReplacementRequest.class));
    }

    public Replacement acceptReplacementRequest(Long replacementRequestId,@Nullable Long employeeId){
        ReplacementRequest replacementRequest = this.replacementRequestRepository.findById(replacementRequestId).orElseThrow(() -> new EntityNotFoundException(ReplacementRequest.class));
        Employee employee;
        if (Objects.nonNull(employeeId)){
            employee = this.employeeRepository.findById(employeeId).orElseThrow(() -> new EntityNotFoundException(Employee.class));
        }else {
            employee = (Employee) authFacade.getAuthenticated();
        }

        Replacement replacement = new Replacement();
        replacement.setReplacedBy(employee);
        replacement.setStatus(ReplacementStatus.ACCEPTED);
        replacement.setReplacementRequest(replacementRequest);

        replacementRequest.setStatus(ReplacementRequestStatus.ACCEPTED);
        replacementRequest.setReplacement(replacement);
        return this.replacementRequestRepository.save(replacementRequest).getReplacement();
    }

    public void refuseReplacementRequest(Long replacementRequestId,@Nullable Long employeeId){
        ReplacementRequest replacementRequest = this.replacementRequestRepository.findById(replacementRequestId).orElseThrow(() -> new EntityNotFoundException(ReplacementRequest.class));
        Employee employee;
        if (Objects.nonNull(employeeId)){
            employee = this.employeeRepository.findById(employeeId).orElseThrow(() -> new EntityNotFoundException(Employee.class));
        }else {
            employee = (Employee) authFacade.getAuthenticated();
        }

        replacementRequest.getPotentialCandidates().remove(employee);
        this.replacementRequestRepository.save(replacementRequest);
    }
}
