package readWriteFile;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;


public class WriteToFile {


    @Test
    public void writeDataToFile() throws IOException {
        String filepath = "src/test/resources/testData/response.json";

        File file = new File(filepath);
        String data = "This is my API test result. write it to file";
        FileUtils.writeStringToFile(file, data, Charset.defaultCharset());
    }
}
