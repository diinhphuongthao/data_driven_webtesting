package demoqa.test;

import demoqa.basePages.BaseUploadFile;
import demoqa.page.UploadFile;
import org.testng.annotations.Test;


import java.io.IOException;
import java.lang.reflect.Method;

import static org.testng.Assert.assertTrue;

public class UploadFileTest extends BaseUploadFile {

    private UploadFile objUploadFile;

    public UploadFileTest() throws IOException {
        super();
        objUploadFile = new UploadFile(this.webUI);
    }

    @Test(description = "TC001: Upload file from local computer", groups = {
            "functiontests"})
       public void TC001_upload_file_from_local(Method method) {
        logger.info("Test case: " + method.getName());
        assertTrue(objUploadFile.uploadFileFromLocal(), "Upload file from local computer");
    }

    @Test(description = "TC002: Upload file by Drag and Drop", groups = {
            "functiontests"})
       public void TC002_drag_and_drop_upload_file(Method method) {
        logger.info("Test case: " + method.getName());
//        assertTrue(objUploadFile.DragAndDropUploadFile(), "Upload file by Drag and Drop");
    }

    @Test(description = "TC003: Upload file from cloudnary", groups = {
            "functiontests"})
    public void TC003_upload_file_from_clound(Method method) throws IOException {
        logger.info("Test case: " + method.getName());
        assertTrue(objUploadFile.uploadFileFromUrl(), "Upload file by Drag and Drop");
    }



}
