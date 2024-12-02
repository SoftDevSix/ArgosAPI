package com.softdevsix.presentation.controllers;

import com.softdevsix.application.services.Rules.IRulesService;
import com.softdevsix.domain.entities.project.ProjectParams;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/rules/")
public class RulesController {
    private final IRulesService RULES_SERVICE;

    public RulesController(IRulesService rulesService) {
        this.RULES_SERVICE = rulesService;
    }

    @PostMapping("/{projectId}")
    public ResponseEntity<String> setRules(@RequestBody ProjectParams params, @PathVariable UUID projectId) {
        RULES_SERVICE.saveRules(params, projectId);
        return new ResponseEntity<>("Project params added successfully.", HttpStatus.OK);
    }
}
