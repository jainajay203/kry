package se.kry.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import se.kry.entity.Endpoint;
import se.kry.service.EndpointServiceInt;

import java.util.List;

@Component
public class EndpointStatusUpdate {

    @Autowired
    EndpointServiceInt serviceInt;

    @Autowired
    RestTemplate restTemplate;

    @Async
    @Scheduled(fixedRate = 5000)
    public void scheduleFixedRateTask() {

       List<Endpoint> endpoint=serviceInt.getEndpoints();
       endpoint.forEach(e ->{

           try{
               HttpHeaders httpHeaders= restTemplate.headForHeaders(e.getUrl());

               e.setStatus("Ok");

           }catch (Exception ex){
               e.setStatus("Fail");
           }

          serviceInt.update(e);

       });

    }
}
