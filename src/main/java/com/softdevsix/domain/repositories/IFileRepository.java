package com.softdevsix.domain.repositories;

import com.softdevsix.domain.entities.file.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

public interface IFileRepository extends CrudRepository<File, UUID> {
}
