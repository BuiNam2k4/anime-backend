package vn.kurisu.anime_service.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final Cloudinary cloudinary;

    public String uploadImage(MultipartFile file) throws IOException {
        // 1. Kiểm tra file có rỗng không
        if (file.isEmpty()) {
            throw new RuntimeException("File rỗng!");
        }

        // 2. Upload lên Cloudinary
        // "public_id" là tên file muốn lưu (nếu không set thì nó random)
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                "resource_type", "auto"
        ));

        // 3. Lấy URL ảnh trả về
        return uploadResult.get("secure_url").toString();
    }
}
