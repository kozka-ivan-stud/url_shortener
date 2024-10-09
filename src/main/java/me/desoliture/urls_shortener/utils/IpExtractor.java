package me.desoliture.urls_shortener.utils;

import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static java.util.Objects.isNull;

@UtilityClass
public class IpExtractor {
    private final String[] IP_HEADER_CANDIDATES = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    public static String extractIpFromCurrentRequest() {
        if (isNull(RequestContextHolder.getRequestAttributes())) return "0.0.0.0";
        var request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        for (var header : IP_HEADER_CANDIDATES) {
            var ipList = request.getHeader(header);
            if (ipList != null && !ipList.isEmpty() && !"unknown".equalsIgnoreCase(ipList)) {
                return ipList.split(",")[0];
            }
        }
        return request.getRemoteAddr();
    }
}
