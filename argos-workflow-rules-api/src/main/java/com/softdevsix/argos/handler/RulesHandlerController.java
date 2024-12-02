package com.softdevsix.argos.handler;

import com.softdevsix.argos.domain.Rules;
import com.softdevsix.argos.domain.RulesRequestMap;
import com.softdevsix.argos.service.RulesService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/argos/rules")
public class RulesHandlerController {
  private final RulesService rulesService;

  public RulesHandlerController(RulesService service) {
    this.rulesService = service;
  }

  @PostMapping
  public ResponseEntity<Rules> createRules(@RequestParam Integer project, @RequestBody RulesRequestMap requestData) {
    Rules createdRules = rulesService.handleRules(requestData, project);
    return ResponseEntity.ok(createdRules);
  }
}
