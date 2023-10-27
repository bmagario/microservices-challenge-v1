package com.microservices.challenge.sumcalculatorservice.feignclients;

import com.microservices.challenge.sumcalculatorservice.model.Percentage;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "percentage-service")
@RequestMapping("/percentage")
public interface PercentageFeignClient {

    @PostMapping()
    Percentage save(@RequestBody Percentage percentage);

    @GetMapping("/bysumCalculator/{sumCalculatorId}")
    List<Percentage> getPercentages(@PathVariable("sumCalculatorId") int sumCalculatorId);
}
