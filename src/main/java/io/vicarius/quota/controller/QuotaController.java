package io.vicarius.quota.controller;

import io.vicarius.quota.service.QuotaService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(
    value = "quotas"
)
public class QuotaController {

    private final QuotaService quotaService;

    @GetMapping
    public Map<String, Integer> findQuotas() {
        return quotaService.findQuotas();
    }

}
