package com.example.demo;

import com.example.demo.bean.ProjectMsg;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@PropertySource("classpath:config/application.properties")
@RunWith(SpringRunner.class)
class YupingguApplicationTests {

    @Autowired
    private ProjectMsgService projectMsgService;

    @Test
    void contextLoads() {

    }

    @Test
    public void test01(){

        ProjectMsg projectMsg = new ProjectMsg();

        projectMsg.setProjectName("项目1");
        projectMsg.setBudget("1");
        projectMsg.setBuildingGoal("1");
        projectMsg.setBusinessLogic("1");
        projectMsg.setBusinessPlan("1");
        projectMsg.setDemandAnalysis("1");
        projectMsg.setDepartment("bumen");
        projectMsg.setEnviromentInfluence("1");
        projectMsg.setImplementation("1");
        projectMsg.setInvestment(11d);
        projectMsg.setMoney(11d);
        projectMsg.setPerformance(1);
        projectMsg.setState(1);
        projectMsg.setProgramme("1");
        projectMsg.setScheme("1");
        projectMsg.setStatusAnalysis("1");
        projectMsg.setCreateTime(new Date());
        projectMsg.setCreatorId(1L);
        projectMsg.setProjectPerformence("1");

        projectMsgService.addProjectMsg(projectMsg);

    }

}
