package com.example.talken.common.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Uploader {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.cloud_front.file_url_format}")
    public String CLOUD_FRONT_DOMAIN_NAME;

    private final AmazonS3Client amazonS3Client;

    public List<String> uploadFiles(List<MultipartFile> files, String dirName) {
        List<String> imageUrlList = new ArrayList<>();

        for (MultipartFile file : files) {
            String originalFileName = file.getOriginalFilename();
            String uploadFileName = createFileName(originalFileName, dirName);

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            try (InputStream inputStream = file.getInputStream()) {

                amazonS3Client.putObject(
                        new PutObjectRequest(bucket, uploadFileName, inputStream, objectMetadata)
                                .withCannedAcl(CannedAccessControlList.PublicReadWrite));

                String uploadFileUrl = "https://" + CLOUD_FRONT_DOMAIN_NAME + "/" + uploadFileName;
                imageUrlList.add(uploadFileUrl);

            } catch (IOException e) {
                throw new IllegalArgumentException(
                        String.format("IMAGE_UPLOAD_ERROR")
                );
            }
        }
        return imageUrlList;
    }

    public void delete(List<String> fileNames, String dirName) {
        fileNames.forEach(fileName -> amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, dirName + "/" + fileName)));
    }

    private String createFileName(String originalFileName, String dirName) {
        return dirName + "/" + UUID.randomUUID().toString().concat(getFileExtension(originalFileName));
    }

    private String getFileExtension(String fileName) {
        if(fileName.length() == 0) {
            throw new IllegalArgumentException(
                    String.format("WRONG_INPUT_IMAGE")
            );
        }

        ArrayList<String> fileValidate = new ArrayList<>();
        fileValidate.add(".jpg");
        fileValidate.add(".jpeg");
        fileValidate.add(".png");
        fileValidate.add(".JPG");
        fileValidate.add(".JPEG");
        fileValidate.add(".PNG");

        String idxFileName = fileName.substring(fileName.lastIndexOf("."));
        if(!fileValidate.contains(idxFileName)) {
            throw new IllegalArgumentException(
                    String.format("WRONG_IMAGE_FORMAT")
            );
        }

        return fileName.substring(fileName.lastIndexOf("."));
    }
}
