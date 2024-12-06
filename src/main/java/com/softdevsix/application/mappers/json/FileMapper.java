package com.softdevsix.application.mappers.json;

import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.entities.file.FileCoverageResult;
import com.softdevsix.domain.entities.file.MethodCoverageResult;
import com.softdevsix.domain.entities.report.ReportClass;
import com.softdevsix.domain.entities.report.ReportLine;
import com.softdevsix.domain.entities.report.ReportMethod;
import com.softdevsix.domain.entities.report.ReportPackage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileMapper {
    public MethodCoverageResult handleReportMethod(ReportMethod reportMethod) {
        MethodCoverageResult methodResult = MethodCoverageResult.builder().build();
        Map<Integer, Boolean> resultStatements = methodResult.getStatements();
        for (ReportLine statement : reportMethod.getStatements()) {
            resultStatements.put(statement.getStatementNumber(), statement.isCovered());
        }

        return methodResult;
    }

    public FileCoverageResult handleReportClass(ReportClass reportClass) {
        FileCoverageResult result = FileCoverageResult.builder().build();
        List<MethodCoverageResult> statements = result.getAllStatements();
        for (ReportMethod method : reportClass.getMethods()) {
            statements.add(handleReportMethod(method));
        }

        return result;
    }

    public List<File> handleReportPackage(ReportPackage reportPackage) {
        List<ReportClass> reportClassList = reportPackage.getReportClass();
        List<File> mappedFileList = new ArrayList<>(reportClassList.size());

        File file;
        for (ReportClass reportClass : reportClassList) {
            file = File.builder()
                    .fileName(reportClass.getSourceFileName())
                    .path(reportClass.getName())
                    .coverageResult(handleReportClass(reportClass)).build();
            mappedFileList.add(file);
        }
        return mappedFileList;
    }
}
