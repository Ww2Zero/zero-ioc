package com.zero.ioc;

import com.zero.ioc.entity01.Test01;
import com.zero.ioc.entity02.Test02;
import com.zero.ioc.entity03.Test03;
import com.zero.ioc.entity04.Test04;
import com.zero.ioc.entity05.Test05;
import com.zero.ioc.entity06.Test06;
import com.zero.ioc.entity07.Test07;
import com.zero.ioc.test.InstanceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({InstanceTest.class,
        Test01.class, Test02.class, Test03.class, Test04.class, Test05.class, Test06.class, Test07.class})
public class AllTest {
}
