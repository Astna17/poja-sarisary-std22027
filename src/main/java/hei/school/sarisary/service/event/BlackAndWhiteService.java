package hei.school.sarisary.service.event;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class BlackAndWhiteService {
    private final Map<String, byte[]> images = new HashMap<>();

    public void processImage(String id, MultipartFile file) throws IOException {
        byte[] imageBytes = file.getBytes();
        images.put(id, imageBytes);
    }

    public Map<String, String> getImageUrls(String id) {
        Map<String, String> urls = new HashMap<>();
        byte[] imageBytes = images.get(id);
        if (imageBytes != null) {
            String originalUrl = "https://original.url/" + id;
            String transformedUrl = "https://transformed.url/" + id;
            urls.put("original_url", originalUrl);
            urls.put("transformed_url", transformedUrl);
        }
        return urls;
    }
}
