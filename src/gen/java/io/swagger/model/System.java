package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.model.CatalogCustoms;
import io.swagger.model.CatalogGroup;
import javax.validation.constraints.*;
import javax.validation.Valid;

/**
 * 
 **/

import io.swagger.annotations.*;
import java.util.Objects;

import javax.xml.bind.annotation.*;

@ApiModel(description = "")

public class System   {
  
  private String id = null;
  private String status = null;
  private CatalogGroup group = null;
  private Boolean temporal = null;
  private CatalogCustoms custom = null;
  private String endDate = null;
  private String type = null;
  private String startDate = null;

  /**
   * 
   **/
  public System id(String id) {
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
  public System status(String status) {
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
  public System group(CatalogGroup group) {
    this.group = group;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("group")
  @Valid
  public CatalogGroup getGroup() {
    return group;
  }
  public void setGroup(CatalogGroup group) {
    this.group = group;
  }

  /**
   * 
   **/
  public System temporal(Boolean temporal) {
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
  public System custom(CatalogCustoms custom) {
    this.custom = custom;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("custom")
  @Valid
  public CatalogCustoms getCustom() {
    return custom;
  }
  public void setCustom(CatalogCustoms custom) {
    this.custom = custom;
  }

  /**
   * 
   **/
  public System endDate(String endDate) {
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
  public System type(String type) {
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
    System system = (System) o;
    return Objects.equals(id, system.id) &&
        Objects.equals(status, system.status) &&
        Objects.equals(group, system.group) &&
        Objects.equals(temporal, system.temporal) &&
        Objects.equals(custom, system.custom) &&
        Objects.equals(endDate, system.endDate) &&
        Objects.equals(type, system.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, status, group, temporal, custom, endDate, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class System {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    group: ").append(toIndentedString(group)).append("\n");
    sb.append("    temporal: ").append(toIndentedString(temporal)).append("\n");
    sb.append("    custom: ").append(toIndentedString(custom)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
  
  
}

