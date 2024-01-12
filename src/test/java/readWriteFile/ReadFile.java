package readWriteFile;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class ReadFile {



    @Test
    public void readRequestFile() throws IOException {
        String filepath="src/test/resources/testData/request.json";
        File file= new File(filepath);
        String fileData =FileUtils.readFileToString(file , Charset.defaultCharset());
        System.out.println(fileData);
    }
}
