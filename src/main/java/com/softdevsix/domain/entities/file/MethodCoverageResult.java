<<<<<<<< HEAD:src/main/java/com/softdevsix/domain/entity/file/MethodCoverageResult.java
package com.softdevsix.domain.entity.file;
========
package com.softdevsix.domain.entities.file;
>>>>>>>> 25506e9 (refactor: update entities directory):src/main/java/com/softdevsix/domain/entities/file/MethodCoverageResult.java

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class MethodCoverageResult {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Map<Integer, Boolean> statements;
}
