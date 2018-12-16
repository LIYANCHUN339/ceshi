package net.myspring.wangzm.common.domain;

import javax.persistence.MappedSuperclass;

/**
 * Created by wangzm on 2017 2017-07-25.
 */
@MappedSuperclass
public class TreeEntity<T> extends DataEntity<T> {
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
