package se.kry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kry.entity.Endpoint;
import se.kry.exception.InvalidRequest;
import se.kry.repository.EndPointRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class EndpointServiceImp implements EndpointServiceInt{

    @Autowired
    EndPointRepository repository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int add(Endpoint endpoint) {

        endpoint.setStatus("-");
      Endpoint tempEnd=  repository.getByName(endpoint.getName());
        if(tempEnd !=null){
            throw  new InvalidRequest("Name is already exist.");
        }
        repository.save(endpoint);
        return endpoint.getId();
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(Endpoint endpoint) {

        Endpoint tempEnd=  repository.getByName(endpoint.getName());
        if(tempEnd ==null){
            throw  new InvalidRequest("Name Not exist.");
        }
        tempEnd.setUpdatedAt(new Timestamp(Instant.now().toEpochMilli()));
    tempEnd.setStatus(endpoint.getStatus());
    tempEnd.setName(endpoint.getName());
    tempEnd.setUrl(endpoint.getUrl());
        repository.save(tempEnd);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(String name) {
        repository.deleteByName(name);
    }

    @Override
    public Endpoint get(String name) {
        return repository.getByName(name);
    }

    @Override
    public List<Endpoint> getEndpoints() {
        return repository.findAll();
    }
}
