package org.crazyboy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: kevin
 * @date: 2020/9/25
 * @time: 下午7:55
 * @description:
 **/

@Slf4j
@SpringBootApplication
public class LandCrowdfundingApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(LandCrowdfundingApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}