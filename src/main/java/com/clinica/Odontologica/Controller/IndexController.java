package com.clinica.Odontologica.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Index")
@PreAuthorize("denyAll()")
public class IndexController {

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public String helloGet() {
        return "Hello Word - GET";
    }

    @GetMapping("/post")
    @PreAuthorize("hasAuthority('CREATE')")
    public String helloPost() {
        return "Hello Word - POST";
    }

    @GetMapping("/copy")
    @PreAuthorize("hasAuthority('COPY')")
    public String helloCopy() {
        return "Hello Word -Copy";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('DELETE')")
    public String helloDelete() {
        return "Hello Word - DELETE";
    }

}