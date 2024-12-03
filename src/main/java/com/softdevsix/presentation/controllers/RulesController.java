package com.softdevsix.presentation.controllers;

import com.softdevsix.application.services.Rules.IRulesService;
import com.softdevsix.domain.entities.project.ProjectParams;
import com.softdevsix.domain.exceptions.BadRequestException;
import com.softdevsix.domain.exceptions.ProjectNotFoundException;
import jakarta.validation.Valid;
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
    public ResponseEntity<String> setRules(@Valid @RequestBody ProjectParams params, @PathVariable UUID projectId) {
        try {
            RULES_SERVICE.saveRules(params, projectId);
            return new ResponseEntity<>("Project params added successfully.", HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ProjectNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred. Please try again later." , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
