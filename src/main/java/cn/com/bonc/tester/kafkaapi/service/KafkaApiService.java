package cn.com.bonc.tester.kafkaapi.service;

import java.util.Map;

public interface KafkaApiService {
    String defineStructures(Map<String, String> commandArgs);

    String deleteStructures(Map<String, String> commandArgs);

    String getStructuresInfo(Map<String, String> paramMap);

    String mergeStructures(Map<String, String> commandArgs);
}
