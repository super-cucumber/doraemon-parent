package com.vipgp.doraemon.server.id.generator.leaf;

import com.vipgp.doraemon.server.DoraemonServerApplication;
import com.vipgp.doraemon.service.dubbo.IDGenerateService;
import com.vipgp.doraemon.service.dubbo.LeafSegment;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DoraemonServerApplication.class)
public  class DoraemonServerApplicationTests {

	@Autowired
	IDGenerateService idGenerateService;

	@Test
	public void contextLoads() {
		Long id = idGenerateService.getFlickerId();
		log.info("flicker id=" + id);

		id=idGenerateService.getLeafSegmentId("tinyuurl");
		log.info("segment id=" + id);

		LeafSegment segment=idGenerateService.getSegment("tinyuurl");
		log.info("default segment maxId =" + segment.getMaxId()+", step="+segment.getStep());

		LeafSegment params=new LeafSegment();
		params.setKey("tinyuurl");
		params.setStep(2000);
		segment=idGenerateService.getSegment(params);
		log.info("custom segment maxId =" + segment.getMaxId()+", step="+segment.getStep());

		id=idGenerateService.getLeafSnowflakeId();
		log.info("snowflake id=" + id);

		id=idGenerateService.getLeafSegmentId("tinyuurl");
		log.info("segment id=" + id);

		String[] temp=new String[5];
		temp[2]="hello";
	}

}
