import com.ruoyi.electric.domain.ElectricityConfig;
import com.ruoyi.electric.service.IElectricityConfigService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class FunTest {
    private final String HOST = "http://df.acdt.edu.cn/";
    @Resource
    private IElectricityConfigService electricityConfigService;

    @Test
    void appTest() {
        ElectricityConfig electricityConfig = electricityConfigService.selectElectricityConfig();
        System.out.println(electricityConfig);
    }
}
