package com.vipgp.doraemon.core.id.generator.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: linshangdou@gmail.com
 * @date: 2021/1/21 10:47
 */
@Data
public class FlickerIdDO implements Serializable {

    private Long id;

    private Date gmtCreate;
}
