package com.dayeon.insdagram.service;

public class S3Service {
//    ****************************************************************************************************************
//    https://github.com/kang-jisu/jstagram/blob/master/src/main/java/com/project/jstagram/post/service/S3Service.java
//    ****************************************************************************************************************

//    private final AmazonS3Client amazonS3Client;
//
////    버킷 연결
//    @Value("${cloud.aws.s3.bucket}")
//    private String bucket;
//
//
//    public S3Service(AmazonS3Client amazonS3Client) {
//        this.amazonS3Client = amazonS3Client;
//    }
//
//
//    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
//        File uploadFile = convert(multipartFile)
//                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));
//
//        return upload(uploadFile, dirName);
//    }
//
//    private String upload(File uploadFile, String dirName) {
//        String fileName = dirName + "/" + uploadFile.getName();
//        String uploadImageUrl = putS3(uploadFile, fileName);
//        removeNewFile(uploadFile);
//
//        return uploadImageUrl;
//    }
//
//    private String putS3(File uploadFile, String fileName) {
//        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
//        return amazonS3Client.getUrl(bucket, fileName).toString();
//    }
//
//    public void delete(String fileName){
//        amazonS3Client.deleteObject(bucket,bucket+"/"+fileName);
//    }
//
//    private void removeNewFile(File targetFile) {
//        if (targetFile.delete()) {
//            System.out.println("파일이 삭제되었습니다.");
//        } else {
//            System.out.println("파일이 삭제되지 못했습니다.");
//        }
//    }
//
//
//
//    private Optional<File> convert(MultipartFile file) throws IOException {
//
//
//        String fileName = file.getOriginalFilename();
//        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
//        SimpleDateFormat format1 = new SimpleDateFormat("yyMMddhhmmss");
//        String now = format1.format(new Date());
//        String storedFileName = "image"+now+fileExtension.toUpperCase();
//        File convertFile = new File(storedFileName);
//
//        if(convertFile.createNewFile()) {
//            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
//                fos.write(file.getBytes());
//            }
//            return Optional.of(convertFile);
//        }
//
//        return Optional.empty();
//    }
//

}
