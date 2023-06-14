package tn.conge.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.conge.domain.Entities.ResponsableRH;
import tn.conge.domain.Repositories.ReponsableRHRepository;
import tn.conge.domain.services.ResponsableRHService;

import java.util.List;

@Service
public class ResponsableRHServiceImpl implements ResponsableRHService {

    @Autowired
    ReponsableRHRepository responsableRHRepository;


    @Override
    public ResponsableRH createResponsableRH(ResponsableRH responsableRH) {
        return responsableRHRepository.save(responsableRH);
    }

    @Override
    public ResponsableRH updateResponsableRH(ResponsableRH responsableRH) {
        return responsableRHRepository.saveAndFlush(responsableRH);
    }

    @Override
    public void deleteResponsableRH(Long id) {
     responsableRHRepository.deleteById(id);
    }

	@Override
	public ResponsableRH getResponsableRHById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResponsableRH> getAllResponsableRH() {
		// TODO Auto-generated method stub
		return null;
	}

    
}
