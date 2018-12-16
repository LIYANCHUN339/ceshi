package net.myspring.wangzm.common.dto;


import org.apache.commons.lang3.StringUtils;

/**
 * Created by wangzm on 2017/4/7.
 */
public class IdDto extends BaseDto {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean isCreate(){
        return StringUtils.isBlank(id);
    }
}
