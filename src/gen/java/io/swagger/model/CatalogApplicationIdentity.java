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

public class CatalogApplicationIdentity   {
  
  private String id = null;
  private String name = null;
  private String revokeCode = null;
  private String itResourceCode = null;
  private String groupTable = null;
  private String type = null;
  private String codeName = null;

  /**
   * 
   **/
  public CatalogApplicationIdentity id(String id) {
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
  public CatalogApplicationIdentity name(String name) {
    this.name = name;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("name")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  /**
   * 
   **/
  public CatalogApplicationIdentity revokeCode(String revokeCode) {
    this.revokeCode = revokeCode;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("revokeCode")
  public String getRevokeCode() {
    return revokeCode;
  }
  public void setRevokeCode(String revokeCode) {
    this.revokeCode = revokeCode;
  }

  /**
   * 
   **/
  public CatalogApplicationIdentity itResourceCode(String itResourceCode) {
    this.itResourceCode = itResourceCode;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("itResourceCode")
  public String getItResourceCode() {
    return itResourceCode;
  }
  public void setItResourceCode(String itResourceCode) {
    this.itResourceCode = itResourceCode;
  }

  /**
   * 
   **/
  public CatalogApplicationIdentity groupTable(String groupTable) {
    this.groupTable = groupTable;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("groupTable")
  public String getGroupTable() {
    return groupTable;
  }
  public void setGroupTable(String groupTable) {
    this.groupTable = groupTable;
  }

  /**
   * 
   **/
  public CatalogApplicationIdentity type(String type) {
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
  public CatalogApplicationIdentity codeName(String codeName) {
    this.codeName = codeName;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("codeName")
  public String getCodeName() {
    return codeName;
  }
  public void setCodeName(String codeName) {
    this.codeName = codeName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CatalogApplicationIdentity catalogApplicationIdentity = (CatalogApplicationIdentity) o;
    return Objects.equals(id, catalogApplicationIdentity.id) &&
        Objects.equals(name, catalogApplicationIdentity.name) &&
        Objects.equals(revokeCode, catalogApplicationIdentity.revokeCode) &&
        Objects.equals(itResourceCode, catalogApplicationIdentity.itResourceCode) &&
        Objects.equals(groupTable, catalogApplicationIdentity.groupTable) &&
        Objects.equals(type, catalogApplicationIdentity.type) &&
        Objects.equals(codeName, catalogApplicationIdentity.codeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, revokeCode, itResourceCode, groupTable, type, codeName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CatalogApplicationIdentity {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    revokeCode: ").append(toIndentedString(revokeCode)).append("\n");
    sb.append("    itResourceCode: ").append(toIndentedString(itResourceCode)).append("\n");
    sb.append("    groupTable: ").append(toIndentedString(groupTable)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    codeName: ").append(toIndentedString(codeName)).append("\n");
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

