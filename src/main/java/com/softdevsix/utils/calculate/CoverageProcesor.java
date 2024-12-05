package com.softdevsix.utils.calculate;

import com.softdevsix.domain.entities.file.File;
import com.softdevsix.domain.entities.file.MethodCoverageResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CoverageProcesor {

    public static float calculateFileCoverage(File file) {
        float totalMethodCoverage = 0f;
        int methodCount = 0;

        for (MethodCoverageResult methodCoverage : file.getFileCoverageResult().getMethodCoverageResults()) {
            int totalStatements = methodCoverage.getMethodStatements().size();
            int coveredStatements = (int) methodCoverage.getMethodStatements().values().stream().filter(Boolean::booleanValue).count();

            if (totalStatements > 0) {
                totalMethodCoverage += (coveredStatements * 100f) / totalStatements;
                methodCount++;
            }
        }

        return methodCount > 0 ? totalMethodCoverage / methodCount : 0f;
    }

    public static float calculateFileMethodCoverage(File file) {
        int totalStatements = 0;
        int coveredStatements = 0;

        for (MethodCoverageResult methodCoverage : file.getFileCoverageResult().getMethodCoverageResults()) {
            for (Map.Entry<Integer, Boolean> entry : methodCoverage.getMethodStatements().entrySet()) {
                totalStatements++;
                if (entry.getValue() != null && entry.getValue()) {
                    coveredStatements++;
                }
            }
        }

        return (totalStatements > 0) ? (coveredStatements * 100f) / totalStatements : 0f;
    }

    public static List<Integer> getUncoveredLines(File file) {
        List<Integer> uncoveredLines = new ArrayList<>();
        for (MethodCoverageResult methodCoverage : file.getFileCoverageResult().getMethodCoverageResults()) {
            for (Map.Entry<Integer, Boolean> entry : methodCoverage.getMethodStatements().entrySet()) {
                if (entry.getValue() != null && !entry.getValue().booleanValue()) {
                    uncoveredLines.add(entry.getKey());
                }
            }
        }
        return uncoveredLines;
    }
}
