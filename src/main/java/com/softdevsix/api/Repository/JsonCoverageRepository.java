package com.softdevsix.api.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softdevsix.api.Entity.ClassData;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Repository that loads coverage data from a JSON file.
 */
@Repository
public class JsonCoverageRepository implements CoverageRepository {
    private final ObjectMapper objectMapper;

    /**
     * Constructor to initialize the {@link ObjectMapper}.
     */
    public JsonCoverageRepository() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Loads class coverage data from a JSON file.
     *
     * @return A list of {@link ClassData} objects representing the class coverage data.
     */
    @Override
    public List<ClassData> getClassData() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/coverage.json");
            JsonNode rootNode = objectMapper.readTree(inputStream).get("classList");
            return objectMapper.readValue(rootNode.toString(), new TypeReference<List<ClassData>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error loading JSON data", e);
        }
    }
}
