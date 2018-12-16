package net.myspring.wangzm.basic.common.utils;

import net.myspring.wangzm.util.json.ObjectMapperUtils;
import net.myspring.wangzm.util.text.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.Map;

public class SecurityUtils {

    public static String getAccountId() {
        return StringUtils.toString(getAdditionalInformation().get("accountId"));
    }

    public static String getCompanyId() {
        return StringUtils.toString(getAdditionalInformation().get("companyId"));
    }

    public static String getPositionId() {
        return StringUtils.toString(getAdditionalInformation().get("positionId"));
    }

    public static String getOfficeId() {
        return StringUtils.toString(getAdditionalInformation().get("officeId"));
    }

    public static String getEmployeeId() {
        return StringUtils.toString(getAdditionalInformation().get("employeeId"));
    }

    private  static Map<String, Object> getAdditionalInformation() {
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails)details;
        Jwt jwt = JwtHelper.decode(oAuth2AuthenticationDetails.getTokenValue());
        Map<String,Object> map = ObjectMapperUtils.readValue(jwt.getClaims(),Map.class);
        return map;
    }
}
