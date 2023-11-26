package schoolproject.capstone.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import schoolproject.capstone.dto.request.PredictRequestDto;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@RestController
public class PredictController {
    @PostMapping("/predict")
    public ResponseEntity<String> predict(@RequestBody PredictRequestDto predictRequestDto) throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();

        String flaskURL = "http://192.168.0.247:5000/predict";

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(predictRequestDto);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(flaskURL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        System.out.println("response = " + response.body());

        return ResponseEntity.ok(response.body().toString());
    }

//    @PostMapping("/predict/test")
//    public void testPredict(@RequestBody PredictRequestDto predictRequestDto) {
//        System.out.println("predictRequestDto.getPre_month = " + predictRequestDto.getPre_month());
//        for (int i = 0; i < predictRequestDto.getUser_input().size(); i++) {
//            System.out.println("year = " + predictRequestDto.getUser_input().get(i).getYear());
//            System.out.println("use = " + predictRequestDto.getUser_input().get(i).getUse());
//        }
//    }
}
