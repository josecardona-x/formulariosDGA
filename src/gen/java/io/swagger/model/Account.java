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

public class Account   {
  
  private String id = null;
  private String name = null;
  private String type = null;
  private String request = null;
  private String login = null;
  private String resource = null;
  private String requestId = null;
  private String resourceId = null;
  private Boolean isNew = null;

  /**
   * 
   **/
  public Account id(String id) {
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
  public Account name(String name) {
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
  public Account type(String type) {
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
  public Account request(String request) {
    this.request = request;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("request")
  public String getRequest() {
    return request;
  }
  public void setRequest(String request) {
    this.request = request;
  }

  /**
   * 
   **/
  public Account login(String login) {
    this.login = login;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("login")
  public String getLogin() {
    return login;
  }
  public void setLogin(String login) {
    this.login = login;
  }

  /**
   * 
   **/
  public Account resource(String resource) {
    this.resource = resource;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("resource")
  public String getResource() {
    return resource;
  }
  public void setResource(String resource) {
    this.resource = resource;
  }

  /**
   * 
   **/
  public Account requestId(String requestId) {
    this.requestId = requestId;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("requestId")
  public String getRequestId() {
    return requestId;
  }
  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  /**
   * 
   **/
  public Account resourceId(String resourceId) {
    this.resourceId = resourceId;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("resourceId")
  public String getResourceId() {
    return resourceId;
  }
  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  /**
   * 
   **/
  public Account isNew(Boolean isNew) {
    this.isNew = isNew;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("isNew")
  public Boolean isIsNew() {
    return isNew;
  }
  public void setIsNew(Boolean isNew) {
    this.isNew = isNew;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(id, account.id) &&
        Objects.equals(name, account.name) &&
        Objects.equals(type, account.type) &&
        Objects.equals(request, account.request) &&
        Objects.equals(login, account.login) &&
        Objects.equals(resource, account.resource) &&
        Objects.equals(requestId, account.requestId) &&
        Objects.equals(resourceId, account.resourceId) &&
        Objects.equals(isNew, account.isNew);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, type, request, login, resource, requestId, resourceId, isNew);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    request: ").append(toIndentedString(request)).append("\n");
    sb.append("    login: ").append(toIndentedString(login)).append("\n");
    sb.append("    resource: ").append(toIndentedString(resource)).append("\n");
    sb.append("    requestId: ").append(toIndentedString(requestId)).append("\n");
    sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
    sb.append("    isNew: ").append(toIndentedString(isNew)).append("\n");
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

