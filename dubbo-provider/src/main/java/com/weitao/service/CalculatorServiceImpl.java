package com.weitao.service;

import com.weitao.annotation.ServiceLog;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Service
@Component
public class CalculatorServiceImpl implements CalculatorService {

    @ServiceLog
    @Override
    public int div(int i, int j) {
        return i / j;
    }
}
