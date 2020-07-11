package rpc.service;

import lab30.api.DemoRpcService;
import org.springframework.stereotype.Service;

@Service
public class DemoRpcServiceImpl implements DemoRpcService {
    @Override
    public String getName() {
        return System.getenv().toString();
    }
}
