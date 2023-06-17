package shubham.csvapi.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Helper {

    public static BufferedReader getBufferedReaderFromMultiPartFile(MultipartFile file) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(file.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return br;
    }

    public static boolean validateFileExtension(MultipartFile file) {
        if (file.getOriginalFilename().contains(".csv") || file.getOriginalFilename().contains(".CSV"))
            return true;
        return false;
    }

}
