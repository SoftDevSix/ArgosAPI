package com.softdevsix.api.entities;

import lombok.Data;
import java.util.List;

/**
 * Represents method-level coverage data.
 */
@Data
public class MethodData {
    /**
     * Name of the method.
     */
    private String name;

    /**
     * Description of the method.
     */
    private String desc;

    /**
     * List of all statements in the method.
     */
    private List<Integer> allStatements;

    /**
     * List of statements covered by the test in the method.
     */
    private List<Integer> coveragedStatements;
}
