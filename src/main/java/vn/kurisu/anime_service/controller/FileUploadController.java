package vn.kurisu.anime_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.kurisu.anime_service.dto.response.ApiResponse;
import vn.kurisu.anime_service.service.FileUploadService;

import java.io.IOException;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class FileUploadController {
    private final FileUploadService fileUploadService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            return ApiResponse.<String>builder()
                    .message("Upload thành công")
                    .result(fileUploadService.uploadImage(file))
                    .build();
        } catch (IOException e) {
            throw new RuntimeException("Lỗi upload file: " + e.getMessage());
        }
    }
}
