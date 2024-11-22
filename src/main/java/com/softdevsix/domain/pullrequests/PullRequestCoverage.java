package com.softdevsix.domain.pullrequests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PullRequestCoverage {
    private final UUID id;
}
