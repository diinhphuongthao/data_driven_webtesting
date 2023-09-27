package demoqa.page;

import demoqa.repo.UploadFileRepo;
import guru99.page.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import untils.keywords.WebUI;


import java.io.*;
import java.net.URL;

public class UploadFile extends BasePage {

    public UploadFile(WebUI webUI) throws IOException {
        super(webUI);
    }

    @Step("Upload file from Local computer")
    public boolean uploadFileFromLocal() {
        WebElement fileInput = webUI.findElement(UploadFileRepo.BTN_UPLOAD_FILE);
        webUI.clickElement(UploadFileRepo.BTN_UPLOAD_FILE);
        webUI.delayInSecond(2);
        String filePath = "D:\\VTI\\Assignment_Automation_Framework\\img\\one.jpg";
        if (filePath != null) {
            fileInput.sendKeys(filePath);
            webUI.delayInSecond(3);
            webUI.takeScreenShotAndHighLightElement(UploadFileRepo.BTN_UPLOAD_FILE);
            return true;
        }
        return false;
    }

//    @Step("Drag and Drop Upload file ")
//    public boolean DragAndDropUploadFile() {
//        String filePath = "D:\\VTI\\Assignment_Automation_Framework\\img\\sampleFile.jpeg";
//        if (webUI.uploadFile(filePath)) {
//            return true;
//        }
//        return false;
//    }

    @Step(" Upload file From url")
    public boolean uploadFileFromUrl() throws IOException {
        WebElement uploadButton = webUI.findElement(UploadFileRepo.BTN_UPLOAD_FILE);
        String fileUrl = "https://res.cloudinary.com/dd2vnuoyi/image/upload/v1670998237/bithree_removebg-preview_umcoxw.png";
        if (fileUrl != null) {
            InputStream inputStream = getFileFromUrl(fileUrl);
            uploadFile(uploadButton, inputStream);
            webUI.delayInSecond(3);
            webUI.takeScreenShotAndHighLightElement(UploadFileRepo.BTN_UPLOAD_FILE);
            return true;
        }
        return false;
    }

    private static InputStream getFileFromUrl(String fileUrl) throws IOException {
        URL url = new URL(fileUrl);
        return url.openStream();
    }

    private void uploadFile(WebElement uploadButton, InputStream inputStream) throws IOException {
        uploadButton.sendKeys(convertInputStreamToFilepath(inputStream));
    }

    private String convertInputStreamToFilepath(InputStream inputStream) throws IOException {
        File tempFile = File.createTempFile("uploaded_file", ".png");
        try (OutputStream outputStream = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        return tempFile.getAbsolutePath();
    }



}