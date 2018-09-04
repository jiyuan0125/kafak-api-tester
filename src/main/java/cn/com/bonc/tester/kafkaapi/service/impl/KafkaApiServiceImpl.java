package cn.com.bonc.tester.kafkaapi.service.impl;

import cn.com.bonc.tester.kafkaapi.service.KafkaApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class KafkaApiServiceImpl implements KafkaApiService {

    @Autowired
    private Environment env;

    @Override
    public String defineStructures(Map<String, String> commandArgs) {
//        RestTemplate restTemplate = new RestTemplate();
//        String basePath = getBasePath();
//        String path = String.format("%s/%s", basePath, "defineStructures");
//        ResponseEntity<Map> result = restTemplate.getForEntity(path, Map.class, commandArgs);
//        System.out.println(result);
        return "defineStructures";
    }

    @Override
    public String deleteStructures(Map<String, String> commandArgs) {
        String basePath = getBasePath();
        return String.format("%s/%s", basePath, "deleteStructures");
    }

    @Override
    public String getStructuresInfo(Map<String, String> commandArgs) {
        RestTemplate restTemplate = new RestTemplate();
        String basePath = getBasePath();
        String path = String.format("%s/%s", basePath, "getStructuresInfo");
        HttpEntity<Map> request = new HttpEntity<>(commandArgs);
        ResponseEntity<Map> result = restTemplate.postForEntity(path, request, Map.class);
        return result.toString();
    }

    @Override
    public String mergeStructures(Map<String, String> commandArgs) {
        String basePath = getBasePath();
        return String.format("%s/%s", basePath, "mergeStructures");
    }

    private String getBasePath() {
        return env.getProperty("kafkaApi.basePath");
    }
}
