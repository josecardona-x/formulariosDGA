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

public class Other   {
  
  private String platform = null;
  private String status = null;
  private Boolean temporal = null;
  private String data = null;
  private String endDate = null;
  private String helpDesk = null;
  private String sendTo = null;
  private String type = null;
  private String id = null;
  private String startDate = null;

  /**
   * 
   **/
  public Other platform(String platform) {
    this.platform = platform;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("platform")
  public String getPlatform() {
    return platform;
  }
  public void setPlatform(String platform) {
    this.platform = platform;
  }

  /**
   * 
   **/
  public Other status(String status) {
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
  public Other temporal(Boolean temporal) {
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
  public Other data(String data) {
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
  public Other endDate(String endDate) {
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
  public Other helpDesk(String helpDesk) {
    this.helpDesk = helpDesk;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("helpDesk")
  public String getHelpDesk() {
    return helpDesk;
  }
  public void setHelpDesk(String helpDesk) {
    this.helpDesk = helpDesk;
  }

  /**
   * 
   **/
  public Other sendTo(String sendTo) {
    this.sendTo = sendTo;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("sendTo")
  public String getSendTo() {
    return sendTo;
  }
  public void setSendTo(String sendTo) {
    this.sendTo = sendTo;
  }

  /**
   * 
   **/
  public Other type(String type) {
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

  /**
   * 
   **/
  public Other id(String id) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Other other = (Other) o;
    return Objects.equals(platform, other.platform) &&
        Objects.equals(status, other.status) &&
        Objects.equals(temporal, other.temporal) &&
        Objects.equals(data, other.data) &&
        Objects.equals(endDate, other.endDate) &&
        Objects.equals(helpDesk, other.helpDesk) &&
        Objects.equals(sendTo, other.sendTo) &&
        Objects.equals(type, other.type) &&
        Objects.equals(id, other.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(platform, status, temporal, data, endDate, helpDesk, sendTo, type, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Other {\n");
    
    sb.append("    platform: ").append(toIndentedString(platform)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    temporal: ").append(toIndentedString(temporal)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    helpDesk: ").append(toIndentedString(helpDesk)).append("\n");
    sb.append("    sendTo: ").append(toIndentedString(sendTo)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

