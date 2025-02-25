package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.model.CatalogAttribute;
import io.swagger.model.CatalogExternalApplicantType;
import io.swagger.model.CatalogPosition;
import javax.validation.constraints.*;
import javax.validation.Valid;

/**
 * 
 **/

import io.swagger.annotations.*;
import java.util.Objects;

import javax.xml.bind.annotation.*;

@ApiModel(description = "")

public class Applicant   {
  
  private String document = null;
  private CatalogPosition position = null;
  private CatalogAttribute attribute = null;
  private CatalogExternalApplicantType externalType = null;
  private String name = null;
  private String externalName = null;
  private String externalRepLegal = null;
  private String externalCodeDeclarant = null;
  private String mail = null;
  private String id = null;

  /**
   * 
   **/
  public Applicant document(String document) {
    this.document = document;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("document")
  public String getDocument() {
    return document;
  }
  public void setDocument(String document) {
    this.document = document;
  }

  /**
   * 
   **/
  public Applicant position(CatalogPosition position) {
    this.position = position;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("position")
  @Valid
  public CatalogPosition getPosition() {
    return position;
  }
  public void setPosition(CatalogPosition position) {
    this.position = position;
  }

  /**
   * 
   **/
  public Applicant attribute(CatalogAttribute attribute) {
    this.attribute = attribute;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("attribute")
  @Valid
  public CatalogAttribute getAttribute() {
    return attribute;
  }
  public void setAttribute(CatalogAttribute attribute) {
    this.attribute = attribute;
  }

  /**
   * 
   **/
  public Applicant externalType(CatalogExternalApplicantType externalType) {
    this.externalType = externalType;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("externalType")
  @Valid
  public CatalogExternalApplicantType getExternalType() {
    return externalType;
  }
  public void setExternalType(CatalogExternalApplicantType externalType) {
    this.externalType = externalType;
  }

  /**
   * 
   **/
  public Applicant name(String name) {
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
  public Applicant externalName(String externalName) {
    this.externalName = externalName;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("externalName")
  public String getExternalName() {
    return externalName;
  }
  public void setExternalName(String externalName) {
    this.externalName = externalName;
  }

  /**
   * 
   **/
  public Applicant externalRepLegal(String externalRepLegal) {
    this.externalRepLegal = externalRepLegal;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("externalRepLegal")
  public String getExternalRepLegal() {
    return externalRepLegal;
  }
  public void setExternalRepLegal(String externalRepLegal) {
    this.externalRepLegal = externalRepLegal;
  }

  /**
   * 
   **/
  public Applicant externalCodeDeclarant(String externalCodeDeclarant) {
    this.externalCodeDeclarant = externalCodeDeclarant;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("externalCodeDeclarant")
  public String getExternalCodeDeclarant() {
    return externalCodeDeclarant;
  }
  public void setExternalCodeDeclarant(String externalCodeDeclarant) {
    this.externalCodeDeclarant = externalCodeDeclarant;
  }

  /**
   * 
   **/
  public Applicant mail(String mail) {
    this.mail = mail;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("mail")
  public String getMail() {
    return mail;
  }
  public void setMail(String mail) {
    this.mail = mail;
  }

  /**
   * 
   **/
  public Applicant id(String id) {
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
    Applicant applicant = (Applicant) o;
    return Objects.equals(document, applicant.document) &&
        Objects.equals(position, applicant.position) &&
        Objects.equals(attribute, applicant.attribute) &&
        Objects.equals(externalType, applicant.externalType) &&
        Objects.equals(name, applicant.name) &&
        Objects.equals(externalName, applicant.externalName) &&
        Objects.equals(externalRepLegal, applicant.externalRepLegal) &&
        Objects.equals(externalCodeDeclarant, applicant.externalCodeDeclarant) &&
        Objects.equals(mail, applicant.mail) &&
        Objects.equals(id, applicant.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(document, position, attribute, externalType, name, externalName, externalRepLegal, externalCodeDeclarant, mail, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Applicant {\n");
    
    sb.append("    document: ").append(toIndentedString(document)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    attribute: ").append(toIndentedString(attribute)).append("\n");
    sb.append("    externalType: ").append(toIndentedString(externalType)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    externalName: ").append(toIndentedString(externalName)).append("\n");
    sb.append("    externalRepLegal: ").append(toIndentedString(externalRepLegal)).append("\n");
    sb.append("    externalCodeDeclarant: ").append(toIndentedString(externalCodeDeclarant)).append("\n");
    sb.append("    mail: ").append(toIndentedString(mail)).append("\n");
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
}

