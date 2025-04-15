package site.ytcomment.popular.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import site.ytcomment.popular.Service.DTO.myPage.UpdateProfileImgServiceDTO;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3UploadService {
    private final S3Client s3Client;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadImage(UpdateProfileImgServiceDTO.In in) throws IOException {
        String key = UUID.randomUUID() + "_" + in.getFile().getOriginalFilename();
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType(in.getFile().getContentType())
                .build();

        s3Client.putObject(request, RequestBody.fromInputStream(in.getFile().getInputStream(), in.getFile().getSize()));

        return "https://" + bucket + ".s3.ap-northeast-2.amazonaws.com/" + key;
    }
}
