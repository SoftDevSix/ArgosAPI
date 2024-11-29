package com.softdevsix.domain.entity.file;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.UUID;

public class File {
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID fileId;
}
