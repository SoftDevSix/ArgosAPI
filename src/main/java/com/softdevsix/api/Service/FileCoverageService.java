package com.softdevsix.api.Service;

import com.softdevsix.api.DTO.FileCoverageDTO;
import com.softdevsix.api.Entity.ClassData;
import com.softdevsix.api.Entity.MethodData;
import com.softdevsix.api.Repository.CoverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service to calculate and retrieve file coverage details.
 */
@Service
public class FileCoverageService implements CoverageService {
    private final CoverageRepository coverageRepository;

    /**
     * Constructor to inject the coverage repository implementation.
     *
     * @param coverageRepository Repository that provides access to coverage data.
     */
    @Autowired
    public FileCoverageService(CoverageRepository coverageRepository) {
        this.coverageRepository = coverageRepository;
    }

    /**
     * Calculates and returns the coverage details of a specific file.
     *
     * @param fileId The ID of the file for which the coverage is to be calculated.
     * @return A {@link FileCoverageDTO} containing the calculated coverage details.
     */
    @Override
    public FileCoverageDTO getFileCoverage(String fileId) {
        List<ClassData> classDataList = coverageRepository.getClassData();

        if (classDataList == null || classDataList.isEmpty()) {
            throw new RuntimeException("Error loading JSON data");
        }

        ClassData classData = classDataList.get(0);

        int totalStatements = 0;
        int coveredStatements = 0;

        for (MethodData method : classData.getMethods()) {
            totalStatements += method.getAllStatements() != null ? method.getAllStatements().size() : 0;
            coveredStatements += method.getCoveragedStatements() != null ? method.getCoveragedStatements().size() : 0;
        }

        double coveragePercentage = totalStatements == 0 ? 0 : (double) coveredStatements / totalStatements * 100;
        String coveragePercentageFormatted = String.format("%.2f%%", coveragePercentage);

        return new FileCoverageDTO(
                classData.getClassName(),
                coveragePercentageFormatted,
                classData.getPathClass(),
                totalStatements,
                coveragePercentageFormatted
        );
    }
}
