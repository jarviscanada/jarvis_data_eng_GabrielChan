package ca.jrvs.apps.trading.dao;

import org.springframework.dao.DataAccessException;

public class ResourceNotFoundException extends DataAccessException {
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
