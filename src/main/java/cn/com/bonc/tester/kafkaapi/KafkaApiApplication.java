package cn.com.bonc.tester.kafkaapi;

import cn.com.bonc.tester.kafkaapi.service.KafkaApiService;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class KafkaApiApplication {

	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext appContext = SpringApplication.run(KafkaApiApplication.class, args);

        KafkaApiService service = appContext.getBean(KafkaApiService.class);

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String commandLine;
		while ((commandLine = reader.readLine()) != null) {
            if (StringUtils.equals(commandLine, "exit")) {
                System.exit(0);
            }

            if (StringUtils.isBlank(commandLine)) {
                System.out.println("Supported commands: defineStructures, deleteStructures, getStructuresInfo, mergeStructures");
                continue;
            }

            String output;
            String[] commandLineArray = StringUtils.split(commandLine, " ");
            String command = commandLineArray[0];
            Map<String, String> commandArgs = parseCommandArgs(commandLineArray);

            switch(command) {
                case "defineStructures":
                    output = service.defineStructures(commandArgs);
                    System.out.println(output);
                    break;
                case "deleteStructures":
                    output = service.deleteStructures(commandArgs);
                    System.out.println(output);
                    break;
                case "getStructuresInfo":
                    output = service.getStructuresInfo(commandArgs);
                    System.out.println(output);
                    break;
                case "mergeStructures":
                    output = service.mergeStructures(commandArgs);
                    System.out.println(output);
                    break;
                default:
                    System.out.println("Supported commands: defineStructures, deleteStructures, getStructuresInfo, mergeStructures");
            }
        }
	}

    private static Map<String, String> parseCommandArgs(String[] commandLineArray) {
	    List<String> tmpArgsList = Arrays.asList(commandLineArray);

	    List<Map<String, String>> tmpResult = tmpArgsList.stream().skip(1).map((String arg) -> {
	        String[] args = StringUtils.split(arg, "=");
	        Map<String, String> paramMap = new HashMap<>();
	        paramMap.put(args[0], args[1]);
	        return paramMap;
        }).collect(Collectors.toList());

        Map<String, String> result = new HashMap<>();
        for (Map<String, String> paramMap : tmpResult) {
            result.putAll(paramMap);
        }

	    return result;
    }
}
