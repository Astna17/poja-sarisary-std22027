package hei.school.sarisary.endpoint.rest.controller.health;


import hei.school.sarisary.service.event.BlackAndWhiteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/black-and-white")
public class BlackAndWhiteController {
    private final BlackAndWhiteService blackAndWhiteService;

    public BlackAndWhiteController(BlackAndWhiteService blackAndWhiteService) {
        this.blackAndWhiteService = blackAndWhiteService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> uploadImage(@PathVariable String id, @RequestParam("file") MultipartFile file) {
        try {
            blackAndWhiteService.processImage(id, file);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, String>> getImageUrls(@PathVariable String id) {
        Map<String, String> urls = blackAndWhiteService.getImageUrls(id);
        return ResponseEntity.ok(urls);
    }
}
