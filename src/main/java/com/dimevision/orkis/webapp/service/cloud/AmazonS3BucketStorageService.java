package com.dimevision.orkis.webapp.service.cloud;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Dimevision
 * @version 0.1
 */

@Service
public class AmazonS3BucketStorageService {

    private AmazonS3 s3Client;

    @Autowired
    public AmazonS3BucketStorageService(@Qualifier("buildS3Client") AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    public String uploadFile(String keyName, MultipartFile file) {

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            s3Client.putObject(bucketName, keyName, file.getInputStream(), metadata);

            return "\tFile succesfully uploaded: " + keyName;
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
            // TODO: 11/20/2021 Add logging stuff
        }

        return "\tFile not uploaded: " + keyName;
    }
}
