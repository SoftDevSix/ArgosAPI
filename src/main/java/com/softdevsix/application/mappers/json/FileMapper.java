package com.softdevsix.application.mappers.json;

import com.softdevsix.application.dto.FileCoverageDto;
import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.entities.file.FileCoverageResult;
import com.softdevsix.domain.entities.file.MethodCoverageResult;
import com.softdevsix.domain.entities.report.ReportClass;
import com.softdevsix.domain.entities.report.ReportLine;
import com.softdevsix.domain.entities.report.ReportMethod;
import com.softdevsix.domain.entities.report.ReportPackage;
import com.softdevsix.utils.calculate.CoverageProcesor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileMapper {

    public static FileCoverageDto FileToFileDto(File file){

        FileCoverageDto fileDto = new FileCoverageDto(
                file.getFileName(),
                file.getPath(),
                file.getCodeLines(),
                CoverageProcesor.calculateFileMethodCoverage(file),
                CoverageProcesor.calculateFileCoverage(file),
                CoverageProcesor.getUncoveredLines(file)
        );
        return fileDto;
    }


    public MethodCoverageResult handleReportMethod(ReportMethod reportMethod) {
        MethodCoverageResult methodResult = MethodCoverageResult.builder().build();
        Map<Integer, Boolean> resultStatements = methodResult.getMethodStatements();
        for (ReportLine statement : reportMethod.getStatements()) {
            resultStatements.put(statement.getStatementNumber(), statement.isCovered());
        }

        return methodResult;
    }

    public FileCoverageResult handleReportClass(ReportClass reportClass) {
        FileCoverageResult result = FileCoverageResult.builder().build();
        List<MethodCoverageResult> statements = result.getMethodCoverageResults();
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
                    .fileCoverageResult(handleReportClass(reportClass)).build();
            mappedFileList.add(file);
        }
        return mappedFileList;
    }
}
