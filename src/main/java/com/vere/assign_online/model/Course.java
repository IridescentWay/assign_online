package com.vere.assign_online.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Course implements Serializable {
    private Integer id;

    private String name;

    private Byte credit;

    private String classTime;

    private String classroom;

    private Integer teacherId;

    private String type;

    private Byte availableCount;

    private LocalDateTime registrationStartTime;

    private LocalDateTime registrationEndTime;

    private String requireType;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getCredit() {
        return credit;
    }

    public void setCredit(Byte credit) {
        this.credit = credit;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime == null ? null : classTime.trim();
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom == null ? null : classroom.trim();
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Byte getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(Byte availableCount) {
        this.availableCount = availableCount;
    }

    public LocalDateTime getRegistrationStartTime() {
        return registrationStartTime;
    }

    public void setRegistrationStartTime(LocalDateTime registrationStartTime) {
        this.registrationStartTime = registrationStartTime;
    }

    public LocalDateTime getRegistrationEndTime() {
        return registrationEndTime;
    }

    public void setRegistrationEndTime(LocalDateTime registrationEndTime) {
        this.registrationEndTime = registrationEndTime;
    }

    public String getRequireType() {
        return requireType;
    }

    public void setRequireType(String requireType) {
        this.requireType = requireType == null ? null : requireType.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", credit=").append(credit);
        sb.append(", classTime=").append(classTime);
        sb.append(", classroom=").append(classroom);
        sb.append(", teacherId=").append(teacherId);
        sb.append(", type=").append(type);
        sb.append(", availableCount=").append(availableCount);
        sb.append(", registrationStartTime=").append(registrationStartTime);
        sb.append(", registrationEndTime=").append(registrationEndTime);
        sb.append(", requireType=").append(requireType);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Course other = (Course) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getCredit() == null ? other.getCredit() == null : this.getCredit().equals(other.getCredit()))
            && (this.getClassTime() == null ? other.getClassTime() == null : this.getClassTime().equals(other.getClassTime()))
            && (this.getClassroom() == null ? other.getClassroom() == null : this.getClassroom().equals(other.getClassroom()))
            && (this.getTeacherId() == null ? other.getTeacherId() == null : this.getTeacherId().equals(other.getTeacherId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getAvailableCount() == null ? other.getAvailableCount() == null : this.getAvailableCount().equals(other.getAvailableCount()))
            && (this.getRegistrationStartTime() == null ? other.getRegistrationStartTime() == null : this.getRegistrationStartTime().equals(other.getRegistrationStartTime()))
            && (this.getRegistrationEndTime() == null ? other.getRegistrationEndTime() == null : this.getRegistrationEndTime().equals(other.getRegistrationEndTime()))
            && (this.getRequireType() == null ? other.getRequireType() == null : this.getRequireType().equals(other.getRequireType()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCredit() == null) ? 0 : getCredit().hashCode());
        result = prime * result + ((getClassTime() == null) ? 0 : getClassTime().hashCode());
        result = prime * result + ((getClassroom() == null) ? 0 : getClassroom().hashCode());
        result = prime * result + ((getTeacherId() == null) ? 0 : getTeacherId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getAvailableCount() == null) ? 0 : getAvailableCount().hashCode());
        result = prime * result + ((getRegistrationStartTime() == null) ? 0 : getRegistrationStartTime().hashCode());
        result = prime * result + ((getRegistrationEndTime() == null) ? 0 : getRegistrationEndTime().hashCode());
        result = prime * result + ((getRequireType() == null) ? 0 : getRequireType().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}