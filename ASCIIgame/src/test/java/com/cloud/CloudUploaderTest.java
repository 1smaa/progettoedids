package com.cloud;

import org.junit.jupiter.api.Test;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.DeleteBucketRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.regions.Region;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class CloudUploaderTest {

    private final String bucketName = "progettoedids";
    private final S3Client s3Client = S3Client.builder().region(Region.EU_NORTH_1).build();

    @Test
    public void testUpload() throws IOException, ClassNotFoundException {

        // Create a CloudContainer object
        CloudContainer cc = new CloudContainer();
        CloudUploader uploader = new CloudUploader(cc);

        // Test upload
        boolean uploadResult = uploader.upload();
        assertTrue(uploadResult, "Upload should succeed");

        // Verify the object was uploaded correctly
        GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket(bucketName).key("cc").build();
        ResponseBytes<GetObjectResponse> responseBytes = s3Client.getObjectAsBytes(getObjectRequest);

        ByteArrayInputStream bin = new ByteArrayInputStream(responseBytes.asByteArray());
        ObjectInputStream oin = new ObjectInputStream(bin);
        CloudContainer downloadedCc = (CloudContainer) oin.readObject();

        assertNotNull(downloadedCc, "Downloaded CloudContainer should not be null");
    }

    @Test
    public void testDownload() throws IOException, ClassNotFoundException {
        // Prepare and upload a CloudContainer object
        CloudContainer cc = new CloudContainer();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(cc);
        oos.flush();
        byte[] barr = bos.toByteArray();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key("cc").build();
        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(barr));

        // Test download
        CloudUploader uploader = new CloudUploader(cc);
        CloudContainer downloadedCc = uploader.download();
        assertNotNull(downloadedCc, "Downloaded CloudContainer should not be null");
    }

    @Test
    public void testUploadFailure() {
        CloudContainer cc = new CloudContainer();
        CloudUploader uploader = new CloudUploader(cc);

        // Modify uploader to force an error (e.g., by using an invalid bucket name)
        uploader = new CloudUploader(cc) {
            @Override
            public boolean upload() {
                PutObjectRequest req = PutObjectRequest.builder().bucket("invalid-bucket-name").key("cc").build();
                try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                     ObjectOutputStream oos = new ObjectOutputStream(bos)) {
                    oos.writeObject(cc);
                    oos.flush();
                    byte[] barr = bos.toByteArray();
                    s3Client.putObject(req, RequestBody.fromBytes(barr));
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        };

        boolean uploadResult = uploader.upload();
        assertFalse(uploadResult, "Upload should fail due to invalid bucket name");
    }

    @Test
    public void testDownloadFailure() {
        CloudContainer cc = new CloudContainer();
        CloudUploader uploader = new CloudUploader(cc);

        // Modify uploader to force an error (e.g., by using an invalid bucket name)
        uploader = new CloudUploader(cc) {
            @Override
            public CloudContainer download() {
                try {
                    GetObjectRequest req = GetObjectRequest.builder().key("cc").bucket("invalid-bucket-name").build();
                    ResponseBytes<GetObjectResponse> objBytes = s3Client.getObjectAsBytes(req);
                    byte[] data = objBytes.asByteArray();
                    ByteArrayInputStream bin = new ByteArrayInputStream(data);
                    ObjectInputStream oin = new ObjectInputStream(bin);
                    return (CloudContainer) oin.readObject();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };

        CloudContainer downloadedCc = uploader.download();
        assertNull(downloadedCc, "Download should fail due to invalid bucket name");
    }
}
