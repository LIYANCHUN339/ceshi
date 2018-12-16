package net.myspring.wangzm.common.dto;

/**
 * Created by wangzm on 2017 2017-07-25.
 */
public class TreeDto extends DataDto {
    private String parentId;
    private String parentIds;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }
}
