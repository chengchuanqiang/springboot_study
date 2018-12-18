package com.ccq.springbootkafka.kafka;

import com.alibaba.fastjson.JSON;
import com.ccq.springbootkafka.domain.Message;
import com.ccq.springbootkafka.dto.enums.ResponseInfoType;
import com.ccq.springbootkafka.dto.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

/********************************
 *** 测试kafka生成者，生成提供对外访问接口'
 ***@Author chengchuanqiang
 ***@Date 2018/7/16 9:51
 ***@Version 1.0.0
 ********************************/

@RestController
@RequestMapping("kafka")
@Api(value = "kafka_api", description = "kafka 相关api")
public class ProducerController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final Logger LOGGER_KAFKA = LoggerFactory.getLogger("kafka_logger");

    /**
     * 使用kafkaTemplate发送消息
     *
     * @param message 消息内容
     * @return success
     */
    @ApiImplicitParams(
            @ApiImplicitParam(name = "message", value = "消息内容", paramType = "body", dataType = "Message", required = true)
    )
    @ApiOperation(value = "使用kafkaTemplate发送消息", notes = "使用kafkaTemplate发送消息")
    @PostMapping(value = "send")
    public BaseResponse send(@RequestBody Message message) {
//        message.setDate(new Date());
        kafkaTemplate.send("test_topic", JSON.toJSONString(message));
        return new BaseResponse<>(ResponseInfoType.SUCCESS.getMsg(), ResponseInfoType.SUCCESS.getCode(), true);
    }

    /**
     * 使用Sl4j2整合kafka发送消息
     *
     * @param msg 消息内容
     * @return success
     */
    @ApiImplicitParam(name = "msg", value = "消息内容", paramType = "path", required = true, dataType = "string", example = "hello kafka!")
    @ApiOperation(value = "使用Sl4j2整合kafka发送消息", notes = "使用Sl4j2整合kafka发送消息")
    @GetMapping(value = "sendSl4j2Log/{msg}")
    public BaseResponse sendLog(@PathVariable(name = "msg") String msg) {
        LOGGER_KAFKA.info(msg);
        return new BaseResponse<>(ResponseInfoType.SUCCESS.getMsg(), ResponseInfoType.SUCCESS.getCode(), true);
    }

}
