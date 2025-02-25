package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.model.CatalogCustoms;
import io.swagger.model.CatalogProfile;
import javax.validation.constraints.*;
import javax.validation.Valid;

/**
 * 
 **/

import io.swagger.annotations.*;
import java.util.Objects;

import javax.xml.bind.annotation.*;

@ApiModel(description = "")

public class Profile   {
  
  private String id = null;
  private CatalogProfile profile = null;
  private String status = null;
  private CatalogCustoms custom = null;
  private String endDate = null;
  private Boolean temporal = null;
  private String type = null;
  private String startDate = null;

  /**
   * 
   **/
  public Profile id(String id) {
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
  public Profile profile(CatalogProfile profile) {
    this.profile = profile;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("profile")
  @Valid
  public CatalogProfile getProfile() {
    return profile;
  }
  public void setProfile(CatalogProfile profile) {
    this.profile = profile;
  }

  /**
   * 
   **/
  public Profile status(String status) {
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
  public Profile custom(CatalogCustoms custom) {
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
  public Profile endDate(String endDate) {
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
  public Profile temporal(Boolean temporal) {
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
   * Externo/Interno
   **/
  public Profile type(String type) {
    this.type = type;
    return this;
  }

  
  @ApiModelProperty(value = "Externo/Interno")
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
    Profile profile = (Profile) o;
    return Objects.equals(id, profile.id) &&
        Objects.equals(profile, profile.profile) &&
        Objects.equals(status, profile.status) &&
        Objects.equals(custom, profile.custom) &&
        Objects.equals(endDate, profile.endDate) &&
        Objects.equals(temporal, profile.temporal) &&
        Objects.equals(type, profile.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, profile, status, custom, endDate, temporal, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Profile {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    profile: ").append(toIndentedString(profile)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    custom: ").append(toIndentedString(custom)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    temporal: ").append(toIndentedString(temporal)).append("\n");
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

