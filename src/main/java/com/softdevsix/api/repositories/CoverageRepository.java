package com.softdevsix.api.repositories;

import com.softdevsix.api.entities.ClassData;
import java.util.List;

/**
 * Interface for accessing coverage data.
 */
public interface CoverageRepository {

    /**
     * Retrieves a list of class coverage data.
     *
     * @return A list of {@link ClassData} objects.
     */
    List<ClassData> getClassData();
}
