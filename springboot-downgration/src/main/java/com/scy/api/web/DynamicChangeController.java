package com.scy.api.web;

import com.scy.common.config.EnvConfig;
import com.scy.downgration.utils.ResourceDowngrationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/change")
public class DynamicChangeController {
    @Autowired
    private EnvConfig envConfig;

    @GetMapping(value = "/{env}")
    public String changeEnv(@PathVariable("env") String env) {
        envConfig.setProfile("env");
        return "current env change to " + env + " ok!";
    }

    @GetMapping(value = "/{resourceId}/{maxThreshold}")
    public String changeThreshold(@PathVariable("resourceId") String resourceId, @PathVariable("maxThreshold") Integer maxThreshold) {
        ResourceDowngrationUtil.INSTANCE.changeThreshold(resourceId, maxThreshold);
        return "resourceId: " + resourceId + " change maxThreshold to " + maxThreshold + " ok !";
    }
}
