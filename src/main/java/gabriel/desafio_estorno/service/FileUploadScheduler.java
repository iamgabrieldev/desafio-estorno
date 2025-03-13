package gabriel.desafio_estorno.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileUploadScheduler {

    private static final String MASTERCARD_URL = "https://api.mastercard.com/upload";

    @Scheduled(cron = "0 0 */6 * * *") // A cada 6 horas
    public void uploadFile() {
        String filePath = "refunds.txt";
        if (Files.exists(Paths.get(filePath))) {
            try {
                String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.postForObject(MASTERCARD_URL, fileContent, String.class);
                System.out.println("File uploaded successfully.");

                // Limpar o arquivo após o envio
                Files.deleteIfExists(Paths.get(filePath));
            } catch (Exception e) {
                System.err.println("Error uploading file: " + e.getMessage());
            }
        }
    }
}