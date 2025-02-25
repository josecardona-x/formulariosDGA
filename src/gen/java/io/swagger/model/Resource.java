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

public class Resource   {
  
  private String id = null;
  private String service = null;
  private Boolean temporal = null;
  private String custom = null;
  private String endDate = null;
  private String data = null;
  private String status = null;
  private String type = null;

  /**
   * 
   **/
  public Resource id(String id) {
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
  public Resource service(String service) {
    this.service = service;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("service")
  public String getService() {
    return service;
  }
  public void setService(String service) {
    this.service = service;
  }

  /**
   * 
   **/
  public Resource temporal(Boolean temporal) {
    this.temporal = temporal;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("temporal")
  public Boolean isTemporal() {
    return temporal;
  }
  public void setTemporal(Boolean temporal) {
    this.temporal = temporal;
  }

  /**
   * 
   **/
  public Resource custom(String custom) {
    this.custom = custom;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("custom")
  public String getCustom() {
    return custom;
  }
  public void setCustom(String custom) {
    this.custom = custom;
  }

  /**
   * 
   **/
  public Resource endDate(String endDate) {
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
  public Resource data(String data) {
    this.data = data;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("data")
  public String getData() {
    return data;
  }
  public void setData(String data) {
    this.data = data;
  }

  /**
   * 
   **/
  public Resource status(String status) {
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
  public Resource type(String type) {
    this.type = type;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("type")
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Resource resource = (Resource) o;
    return Objects.equals(id, resource.id) &&
        Objects.equals(service, resource.service) &&
        Objects.equals(temporal, resource.temporal) &&
        Objects.equals(custom, resource.custom) &&
        Objects.equals(endDate, resource.endDate) &&
        Objects.equals(data, resource.data) &&
        Objects.equals(status, resource.status) &&
        Objects.equals(type, resource.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, service, temporal, custom, endDate, data, status, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Resource {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    service: ").append(toIndentedString(service)).append("\n");
    sb.append("    temporal: ").append(toIndentedString(temporal)).append("\n");
    sb.append("    custom: ").append(toIndentedString(custom)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

