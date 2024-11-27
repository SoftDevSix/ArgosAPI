<<<<<<<< HEAD:src/main/java/com/softdevsix/domain/entity/file/FileCoverageResult.java
package com.softdevsix.domain.entity.file;
========
package com.softdevsix.domain.entities.file;
>>>>>>>> 25506e9 (refactor: update entities directory):src/main/java/com/softdevsix/domain/entities/file/FileCoverageResult.java

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class FileCoverageResult {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private List<MethodCoverageResult> allStatements;
}
