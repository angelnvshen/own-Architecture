package com.lawcloud.lawper.model;

import javax.persistence.*;

@Table(name = "group_members")
public class GroupMembers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(name = "group_id")
    private Long groupId;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

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
}