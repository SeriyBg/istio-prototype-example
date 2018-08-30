package com.istio.mesh.example.lunarphase;

import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LunarPhaseController {

    private static final Logger LOG = Logger.getLogger(LunarPhaseController.class.getName());

    private static final String USER_AGENT = "user-agent";
    private static final String[] TRACE_HEADERS = new String[] {"x-request-id", "x-b3-traceid", "x-b3-spanid", "x-b3-parentspanid", "x-b3-sampled", "x-b3-flags", "x-ot-span-context"};

    private final IconService iconService;
    private final TimeService timeService;

    @Autowired
    public LunarPhaseController(IconService iconService, TimeService timeService) {
        this.iconService = iconService;
        this.timeService = timeService;
    }

    @GetMapping("/phase/today/{city}")
    public LunarPhase lunarPhase(@PathVariable("city") String city, HttpServletRequest request) {
        LunarPhase lunarPhase = new LunarPhase(city);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(USER_AGENT, request.getHeader(USER_AGENT));
        for (String traceHeader : TRACE_HEADERS) {
            String header = request.getHeader(traceHeader);
            LOG.info("Header - " + traceHeader + ": " + header);
            if (StringUtils.isEmpty(header)) {
                continue;
            }
            httpHeaders.add(traceHeader, header);
        }
        lunarPhase.setIcon(iconService.icon(lunarPhase.getPhase(), httpHeaders));
        lunarPhase.setCurrentTime(timeService.jsonTime(httpHeaders));
        return lunarPhase;
    }
}
