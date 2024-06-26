package com.cloud;

import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//Uploads com.game details to AWS bucket
public class CloudUploader {
    private CloudContainer cc;
    private S3Client client;
    private final Region region=Region.EU_NORTH_1;
    private final String bucketName="progettoedids";
    public CloudUploader(CloudContainer cc) {
        //Constructor
        this.cc=cc;
        this.client=S3Client.builder().region(this.region).build();
    }
    //Function to upload the object to the com.cloud
    public boolean upload(){
        //Create putobjectrequest
        PutObjectRequest req=PutObjectRequest.builder().bucket(this.bucketName).key("cc").build();
        //Convert the com.cloud.CloudContainer into byte array
        try(ByteArrayOutputStream bos=new ByteArrayOutputStream();
        ObjectOutputStream oos=new ObjectOutputStream(bos)){
            oos.writeObject(this.cc);
            oos.flush();
            byte[] barr=bos.toByteArray();
            //Send request
            this.client.putObject(req,RequestBody.fromBytes(barr));
        }catch(Exception e){
            //Catch random exceptions and returns false (failed upload)
            e.printStackTrace();
            return false;
        }
        //return true for successful upload
        return true;
    }
    public CloudContainer download(){
        byte[] data;
        try{
            //Create new request
            GetObjectRequest req=GetObjectRequest.builder().key("cc").bucket(this.bucketName).build();
            //Get object as bytes
            ResponseBytes<GetObjectResponse> objBytes=this.client.getObjectAsBytes(req);
            data=objBytes.asByteArray();
            //Cast bytes to com.cloud.CloudContainer object
            ByteArrayInputStream bin=new ByteArrayInputStream(data);
            ObjectInputStream oin=new ObjectInputStream(bin);
            return (CloudContainer)oin.readObject();
        }catch(Exception e) {
            //If an error happens return null (download failed)
            e.printStackTrace();
            return null;
        }
    }
}
