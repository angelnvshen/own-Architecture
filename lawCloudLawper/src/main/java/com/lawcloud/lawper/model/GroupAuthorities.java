package com.lawcloud.lawper.model;

import javax.persistence.*;

@Table(name = "group_authorities")
public class GroupAuthorities {
    @Column(name = "group_id")
    private Long groupId;

    private String authority;

    /**
     * @return group_id
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * @param groupId
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * @return authority
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * @param authority
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }
}