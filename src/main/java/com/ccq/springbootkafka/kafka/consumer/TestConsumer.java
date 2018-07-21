package com.ccq.springbootkafka.kafka.consumer;

import com.alibaba.fastjson.JSON;
import com.ccq.springbootkafka.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/********************************
 *** kafka消费者
 ***@Author chengchuanqiang
 ***@Date 2018/7/16 9:55
 ***@Version 1.0.0
 ********************************/

@Component
@Slf4j
public class TestConsumer {

    @KafkaListener(topics = "test_topic")
    public void listen(ConsumerRecord<?, ?> record) {
        Message message = JSON.parseObject(record.value().toString(), Message.class);
        String msg = JSON.toJSONString(message);
        log.info("topic = " + record.topic() + " , offset = " + record.offset() + ", value = " + msg + "\n");
    }

    @KafkaListener(topics = "kafka_log4j2_topic")
    public void kafkaLog(ConsumerRecord<?, ?> record) {
        log.info("topic = " + record.topic() + " , offset = " + record.offset() + ", value = " + record.value() + "\n");
    }
}
