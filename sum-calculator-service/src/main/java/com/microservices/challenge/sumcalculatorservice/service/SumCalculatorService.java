package com.microservices.challenge.sumcalculatorservice.service;

import com.microservices.challenge.sumcalculatorservice.entity.CallHistory;
import com.microservices.challenge.sumcalculatorservice.repository.CallHistoryRepository;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SumCalculatorService {

    private final CallHistoryRepository callHistoryRepository;

    private final RestTemplate restTemplate;

    public SumCalculatorService(CallHistoryRepository callHistoryRepository,
                                RestTemplate restTemplate) {
        this.callHistoryRepository = callHistoryRepository;
        this.restTemplate = restTemplate;
    }

    public Page<CallHistory> getCallHistory(Pageable pageable) {
        return callHistoryRepository.findAllByOrderByTimestampDesc(pageable);
    }

    public Double performCalculation(Double num1, Double num2) {
        Double percentage = restTemplate.getForObject(
                "http://percentage-service/percentage",
                Double.class);
        Double result = num1 + num2 + (num1 + num2) * percentage / 100;

        saveCallHistory("performCalculation", num1 + ", " + num2, String.valueOf(result));

        return result;
    }

    private void saveCallHistory(String endpoint, String requestData, String responseData) {
        CallHistory callHistory = new CallHistory();
        callHistory.setTimestamp(LocalDateTime.now());
        callHistory.setEndpoint(endpoint);
        callHistory.setRequestData(requestData);
        callHistory.setResponseData(responseData);
        callHistoryRepository.save(callHistory);
    }
}
