package se.kry.service;

import se.kry.entity.Endpoint;

import java.util.List;

public interface EndpointServiceInt {

    public int add(Endpoint endpoint);
  //  public void save(List<Endpoint> list);
    public void update(Endpoint endpoint);
    public void delete(String name);
    public Endpoint get(String name);
    public List<Endpoint> getEndpoints();

}
