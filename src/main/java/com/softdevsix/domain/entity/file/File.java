package com.softdevsix.domain.entity.file;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

import java.util.UUID;

@Data
public class File {
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID fileId;
}
