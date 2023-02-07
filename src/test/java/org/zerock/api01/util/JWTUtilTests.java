package org.zerock.api01.util;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
@Log4j2
public class JWTUtilTests {

    @Autowired
    private JWTUtil jwtUtil;

    @Test
    public void testGenerate() {

        Map<String, Object> claimMap = Map.of("mid", "ABCDE");

        String jwtStr = jwtUtil.generateToken(claimMap, 1);

        log.info(jwtStr);
    }

    @Test
    public void testValidate() {

        String jwtStr = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NzU3NDk1MTksIm1pZCI6IkFCQ0RFIiwiaWF0IjoxNjc1NzQ5NDU5fQ.6VYfAfRdPxuY17QoE-3UXb2NSYKwodBuh-IRYfjIs_I";

        Map<String, Object> claim = jwtUtil.validateToken(jwtStr);

        log.info(claim);
    }
}
