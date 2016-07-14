package com.winson.tutorial.drools.impl;

import com.winson.tutorial.drools.service.PointRuleEngine;
import com.winson.tutorial.drools.bean.PointDomain;
import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.StatefulSession;
import org.drools.compiler.DroolsParserException;
import org.drools.compiler.PackageBuilder;
import org.drools.spi.Activation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-7-14.
 */
public class PointRuleEngineImpl implements PointRuleEngine {

    private static RuleBase ruleBase;

    @Override
    public void initEngine() {
        System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");

        ruleBase = RuleBaseFactory.newRuleBase();
        try {
            PackageBuilder packageBuilder = getPackageBuilderFromDrlFile();
            ruleBase.addPackages(packageBuilder.getPackages());
        } catch (DroolsParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refreshEngineRule() {
        if (ruleBase != null) {
            org.drools.rule.Package[] packages = ruleBase.getPackages();
            for (org.drools.rule.Package pg : packages) {
                ruleBase.removePackage(pg.getName());
            }
            ruleBase = null;
        }
        initEngine();
    }

    @Override
    public void executeRuleEngine(PointDomain pointDomain) {
        if (null == ruleBase.getPackages() || 0 == ruleBase.getPackages().length) {
            return;
        }

        StatefulSession statefulSession = ruleBase.newStatefulSession();
        statefulSession.insert(pointDomain);
        // fire
        statefulSession.fireAllRules(new org.drools.spi.AgendaFilter() {
            public boolean accept(Activation activation) {
                return !activation.getRule().getName().contains("_test");
            }
        });
        statefulSession.dispose();
    }

    private PackageBuilder getPackageBuilderFromDrlFile() throws Exception {
        // 获取测试脚本文件
        List<String> drlFilePath = getTestDrlFile();
        // 装载测试脚本文件
        List<Reader> readers = readRuleFromDrlFile(drlFilePath);
        PackageBuilder packageBuilder = new PackageBuilder();
        for (Reader r : readers) {
            packageBuilder.addPackageFromDrl(r);
            r.close();
        }
        // 检查脚本是否有问题
        if (packageBuilder.hasErrors()) {
            throw new Exception(packageBuilder.getErrors().toString());
        }
        return packageBuilder;
    }

    private List<Reader> readRuleFromDrlFile(List<String> drlFilePath) throws FileNotFoundException {
        if (null == drlFilePath || 0 == drlFilePath.size()) {
            return null;
        }

        List<Reader> readers = new ArrayList<>();

        for (String ruleFilePath : drlFilePath) {
            readers.add(new FileReader(new File(ruleFilePath)));
        }
        return readers;
    }

    private List<String> getTestDrlFile() {
        List<String> drlFilePath = new ArrayList<String>();
        drlFilePath.add("./addpoint.drl");
        drlFilePath.add("./subpoint.drl");
        return drlFilePath;
    }

    public static void main(String[] args) throws IOException {
        PointRuleEngine pointRuleEngine = new PointRuleEngineImpl();
        while (true) {
            System.out.println("请输入命令：");

            InputStream is = System.in;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String input = br.readLine();

            if (null != input && "s".equals(input)) {
                System.out.println("初始化规则引擎...");
                pointRuleEngine.initEngine();
                System.out.println("初始化规则引擎结束.");
            } else if ("e".equals(input)) {
                final PointDomain pointDomain = new PointDomain();
                pointDomain.setUserName("Winson");
                pointDomain.setBackMondy(100d);
                pointDomain.setBuyMoney(500d);
                pointDomain.setBackNums(1);
                pointDomain.setBuyNums(5);
                pointDomain.setBillThisMonth(5);
                pointDomain.setBirthDay(true);
                pointDomain.setPoint(9);

                System.out.println("初始化规则引擎...");
                pointRuleEngine.initEngine();
                System.out.println("初始化规则引擎结束.");

                System.out.println("用户"+pointDomain.getUserName()+"执行规则引擎前的积分："+pointDomain.getPoint());

                pointRuleEngine.executeRuleEngine(pointDomain);
                System.out.println("执行完毕BillThisMonth：" + pointDomain.getBillThisMonth());
                System.out.println("执行完毕BuyMoney：" + pointDomain.getBuyMoney());
                System.out.println("执行完毕BuyNums：" + pointDomain.getBuyNums());
                System.out.println("执行完毕规则引擎决定发送积分：" + pointDomain.getPoint());
            } else if ("r".equals(input)) {
                System.out.println("刷新规则文件...");
                pointRuleEngine.refreshEngineRule();
                System.out.println("刷新规则文件结束.");
            } else if ("q".equalsIgnoreCase(input) ){
                System.out.println("退出...");
                return;
            }
        }
    }
}
