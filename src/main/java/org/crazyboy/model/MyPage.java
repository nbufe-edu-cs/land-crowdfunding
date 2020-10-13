package org.crazyboy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: kevin
 * @date: 2020/10/13
 * @time: 下午3:09
 * @description:
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPage<T> implements Serializable {
    private T records;
    private Integer index;
    private Long pages;
    private Long total;
}
