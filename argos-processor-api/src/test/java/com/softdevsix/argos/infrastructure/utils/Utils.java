package com.softdevsix.argos.infrastructure.utils;

import com.softdevsix.argos.domain.entities.report.Report;
import com.softdevsix.argos.application.services.report.IReportReader;
import com.softdevsix.argos.application.services.report.JsonReportReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.fail;

public class Utils {
    private Utils() {
    }

    public static String readFile(String path) {
        try (InputStream fileInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path)) {
            StringBuilder file = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                file.append(line).append("\n");
            }
            return file.toString();

        } catch (IOException e) {
            fail(e.getMessage(), e);
            return "";
        }
    }

    public static Report makeReport() {
        String jsonFile = readFile("coverage-report.json");
        IReportReader reportReader = new JsonReportReader();
        return reportReader.read(jsonFile);
    }
}
