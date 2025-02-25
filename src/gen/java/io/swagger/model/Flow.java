package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import javax.validation.Valid;

/**
 * 
 **/

import io.swagger.annotations.*;
import java.util.Objects;

import javax.xml.bind.annotation.*;

@ApiModel(description = "")

public class Flow   {
  
  private String id = null;
  private String status = null;
  private String comment = null;
  private String startDate = null;
  private String endDate = null;
  private String result = null;
  private String user = null;
  private String flowId = null;

  /**
   * 
   **/
  public Flow id(String id) {
    this.id = id;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("id")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  /**
   * 
   **/
  public Flow status(String status) {
    this.status = status;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("status")
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * 
   **/
  public Flow comment(String comment) {
    this.comment = comment;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("comment")
  public String getComment() {
    return comment;
  }
  public void setComment(String comment) {
    this.comment = comment;
  }

  /**
   * 
   **/
  public Flow startDate(String startDate) {
    this.startDate = startDate;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("startDate")
  public String getStartDate() {
    return startDate;
  }
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  /**
   * 
   **/
  public Flow endDate(String endDate) {
    this.endDate = endDate;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("endDate")
  public String getEndDate() {
    return endDate;
  }
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  /**
   * 
   **/
  public Flow result(String result) {
    this.result = result;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("result")
  public String getResult() {
    return result;
  }
  public void setResult(String result) {
    this.result = result;
  }

  /**
   * 
   **/
  public Flow user(String user) {
    this.user = user;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("user")
  public String getUser() {
    return user;
  }
  public void setUser(String user) {
    this.user = user;
  }

  /**
   * 
   **/
  public Flow flowId(String flowId) {
    this.flowId = flowId;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("flowId")
  public String getFlowId() {
    return flowId;
  }
  public void setFlowId(String flowId) {
    this.flowId = flowId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Flow flow = (Flow) o;
    return Objects.equals(id, flow.id) &&
        Objects.equals(status, flow.status) &&
        Objects.equals(comment, flow.comment) &&
        Objects.equals(startDate, flow.startDate) &&
        Objects.equals(endDate, flow.endDate) &&
        Objects.equals(result, flow.result) &&
        Objects.equals(user, flow.user) &&
        Objects.equals(flowId, flow.flowId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, status, comment, startDate, endDate, result, user, flowId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Flow {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    flowId: ").append(toIndentedString(flowId)).append("\n");
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
}

