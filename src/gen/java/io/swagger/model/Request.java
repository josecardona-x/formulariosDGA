package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.model.CatalogRequestType;
import io.swagger.model.Flow;
import io.swagger.model.Other;
import io.swagger.model.Person;
import io.swagger.model.Profile;
import io.swagger.model.Resource;
import io.swagger.model.System;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

/**
 * Request
 *
 */
import io.swagger.annotations.*;
import java.util.Objects;

import javax.xml.bind.annotation.*;

@ApiModel(description = "Request")

public class Request {

    private String id = null;
    private CatalogRequestType typeRequest = null;
    private String state = null;
    private String createBy = null;
    private String createOn = null;
    private String approveDate = null;
    private List<Profile> profiles = new ArrayList<Profile>();
    private List<Resource> resources = new ArrayList<Resource>();
    private List<System> systems = new ArrayList<System>();
    private List<Other> others = new ArrayList<Other>();
    private List<Flow> flow = new ArrayList<Flow>();
    private Person person = null;
    private String hashCode = null;
    private Boolean deleteAllGroups = null;
    private Boolean moveToDesactive = null;

    private String helpDeskId = null;
    private String password = null;

    /**
     *
     *
     */
    public Request id(String id) {
        this.id = id;
        return this;
    }

    @ApiModelProperty(required = true, value = "")
    @JsonProperty("id")
    @NotNull
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("helpDeskId")
    @Valid
    public String getHelpDeskId() {
        return helpDeskId;
    }

    public void setHelpDeskId(String helpDeskId) {
        this.helpDeskId = helpDeskId;
    }

    /**
     *
     *
     */
    public Request typeRequest(CatalogRequestType typeRequest) {
        this.typeRequest = typeRequest;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("typeRequest")
    @Valid
    public CatalogRequestType getTypeRequest() {
        return typeRequest;
    }

    public void setTypeRequest(CatalogRequestType typeRequest) {
        this.typeRequest = typeRequest;
    }

    /**
     *
     *
     */
    public Request state(String state) {
        this.state = state;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     *
     */
    public Request createBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("createBy")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     *
     *
     */
    public Request createOn(String createOn) {
        this.createOn = createOn;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("createOn")
    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }

    /**
     *
     *
     */
    public Request approveDate(String approveDate) {
        this.approveDate = approveDate;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("approveDate")
    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate;
    }

    /**
     *
     *
     */
    public Request profiles(List<Profile> profiles) {
        this.profiles = profiles;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("profiles")
    @Valid
    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    /**
     *
     *
     */
    public Request resources(List<Resource> resources) {
        this.resources = resources;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("resources")
    @Valid
    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    /**
     *
     *
     */
    public Request systems(List<System> systems) {
        this.systems = systems;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("systems")
    @Valid
    public List<System> getSystems() {
        return systems;
    }

    public void setSystems(List<System> systems) {
        this.systems = systems;
    }

    /**
     *
     *
     */
    public Request others(List<Other> others) {
        this.others = others;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("others")
    @Valid
    public List<Other> getOthers() {
        return others;
    }

    public void setOthers(List<Other> others) {
        this.others = others;
    }

    /**
     *
     *
     */
    public Request flow(List<Flow> flow) {
        this.flow = flow;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("flow")
    @Valid
    public List<Flow> getFlow() {
        return flow;
    }

    public void setFlow(List<Flow> flow) {
        this.flow = flow;
    }

    /**
     *
     *
     */
    public Request person(Person person) {
        this.person = person;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("person")
    @Valid
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     *
     *
     */
    public Request hashCode(String hashCode) {
        this.hashCode = hashCode;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("hashCode")
    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    /**
     *
     *
     */
    public Request deleteAllGroups(Boolean deleteAllGroups) {
        this.deleteAllGroups = deleteAllGroups;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("deleteAllGroups")
    public Boolean isDeleteAllGroups() {
        return deleteAllGroups;
    }

    public void setDeleteAllGroups(Boolean deleteAllGroups) {
        this.deleteAllGroups = deleteAllGroups;
    }

    /**
     *
     *
     */
    public Request moveToDesactive(Boolean moveToDesactive) {
        this.moveToDesactive = moveToDesactive;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("moveToDesactive")
    public Boolean isMoveToDesactive() {
        return moveToDesactive;
    }

    public void setMoveToDesactive(Boolean moveToDesactive) {
        this.moveToDesactive = moveToDesactive;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Request request = (Request) o;
        return Objects.equals(id, request.id)
                && Objects.equals(typeRequest, request.typeRequest)
                && Objects.equals(state, request.state)
                && Objects.equals(createBy, request.createBy)
                && Objects.equals(createOn, request.createOn)
                && Objects.equals(approveDate, request.approveDate)
                && Objects.equals(profiles, request.profiles)
                && Objects.equals(resources, request.resources)
                && Objects.equals(systems, request.systems)
                && Objects.equals(others, request.others)
                && Objects.equals(flow, request.flow)
                && Objects.equals(person, request.person)
                && Objects.equals(hashCode, request.hashCode)
                && Objects.equals(deleteAllGroups, request.deleteAllGroups)
                && Objects.equals(moveToDesactive, request.moveToDesactive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeRequest, state, createBy, createOn, approveDate, profiles, resources, systems, others, flow, person, hashCode, deleteAllGroups, moveToDesactive);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Request {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    typeRequest: ").append(toIndentedString(typeRequest)).append("\n");
        sb.append("    state: ").append(toIndentedString(state)).append("\n");
        sb.append("    createBy: ").append(toIndentedString(createBy)).append("\n");
        sb.append("    createOn: ").append(toIndentedString(createOn)).append("\n");
        sb.append("    approveDate: ").append(toIndentedString(approveDate)).append("\n");
        sb.append("    profiles: ").append(toIndentedString(profiles)).append("\n");
        sb.append("    resources: ").append(toIndentedString(resources)).append("\n");
        sb.append("    systems: ").append(toIndentedString(systems)).append("\n");
        sb.append("    others: ").append(toIndentedString(others)).append("\n");
        sb.append("    flow: ").append(toIndentedString(flow)).append("\n");
        sb.append("    person: ").append(toIndentedString(person)).append("\n");
        sb.append("    hashCode: ").append(toIndentedString(hashCode)).append("\n");
        sb.append("    deleteAllGroups: ").append(toIndentedString(deleteAllGroups)).append("\n");
        sb.append("    moveToDesactive: ").append(toIndentedString(moveToDesactive)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
