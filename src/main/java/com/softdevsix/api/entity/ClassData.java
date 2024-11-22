package com.softdevsix.api.entity;

import lombok.Data;
import java.util.List;

/**
 * Represents coverage data for a specific class.
 */
@Data
public class ClassData {
    /**
     * Name of the class.
     */
    private String className;

    /**
     * Path to the class file.
     */
    private String pathClass;

    /**
     * List of methods with their coverage data.
     */
    private List<MethodData> methods;
}
