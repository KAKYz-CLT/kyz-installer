package br.com.kakyz.kyz_installer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.kakyz.kyz_installer.service.StatusService;
import br.com.kakyz.kyz_installer.dto.StatusResponse;

@RestController
public class StatusController {
    
    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/status")
    public StatusResponse verificarStatus() {
       return statusService.obterStatus();
    }
}
