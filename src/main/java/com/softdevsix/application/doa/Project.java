package com.softdevsix.application.doa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Project {
    @Id
    private Long id;
}
