package com.softdevsix.application.mappers.json;

import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.entities.file.FileCoverageResult;
import com.softdevsix.domain.entities.file.MethodCoverageResult;
import com.softdevsix.domain.entities.report.Report;
import com.softdevsix.domain.entities.report.ReportClass;
import com.softdevsix.domain.entities.report.ReportMethod;
import com.softdevsix.domain.entities.report.ReportPackage;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

import static com.softdevsix.infrastructure.utils.Utils.makeReport;
import static org.junit.jupiter.api.Assertions.*;

class FileMapperTest {

    @Test
    void checkReportMethodMap() {
        Report report = makeReport();
        ReportMethod reportMethod = report.getReportPackages().get(0).getReportClass().get(0).getMethods().get(0);
        FileMapper methodMapper = new FileMapper();
        MethodCoverageResult result = methodMapper.handleReportMethod(reportMethod);
        Map<Integer, Boolean> resultStatements = result.getStatements();
        assertEquals(1, resultStatements.size());
        int statementNumber = 3;
        assertTrue(resultStatements.get(statementNumber));
    }

    @Test
    void checkMapperHappyPath() {
        ReportClass report = makeReport().getReportPackages().get(0).getReportClass().get(0);
        FileMapper mapper = new FileMapper();
        FileCoverageResult result = mapper.handleReportClass(report);
        assertEquals(1, result.getAllStatements().size());
    }

    @Test
    void checkReportPackageMapHappyPath(){
        ReportPackage report = makeReport().getReportPackages().get(0);
        FileMapper mapper = new FileMapper();
        List<File> result = mapper.handleReportPackage(report);
        assertEquals(1, result.size());
        assertEquals("Car.java", result.get(0).getFileName());
    }
}