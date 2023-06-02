package tn.conge.core.security;

import tn.conge.domain.entitites.User;

public interface AuthFacade {
    User getAuthenticated();
}
