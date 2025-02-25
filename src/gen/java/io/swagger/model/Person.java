package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.model.CatalogAttribute;
import io.swagger.model.CatalogLevelFour;
import io.swagger.model.CatalogLevelOne;
import io.swagger.model.CatalogLevelThree;
import io.swagger.model.CatalogLevelTwo;
import io.swagger.model.CatalogPosition;
import io.swagger.model.CatalogTypeAFPA;
import javax.validation.constraints.*;
import javax.validation.Valid;

/**
 *
 *
 */
import io.swagger.annotations.*;
import java.util.Objects;
import javax.persistence.Column;

import javax.xml.bind.annotation.*;

@ApiModel(description = "")

public class Person {

    private String document = null;
    private String mail = null;
    private String uid = null;
    private CatalogPosition position = null;
    private CatalogLevelOne levelOne = null;
    private CatalogLevelTwo levelTwo = null;
    private CatalogLevelThree levelThree = null;
    private CatalogLevelFour levelFour = null;
    private String startDate = null;
    private String endDate = null;
    private CatalogAttribute attribute = null;
    private CatalogTypeAFPA typeAFPA = null;
    private Boolean isAFPA = null;
    private String resolution = null;
    private String codeDeclarant = null;
    private String id = null;
    private String approveAFPA = null;
    private String fullName = null;
    private String lastName = null;
    private String surName = null;
    private String userType = null;
    private String organizationCode = null;
    private String phoneNumber = null;
    private String alternativeMail = null;
    private String mobile = null;

    private String userSiduneaWorld;
    private String userSiduneaPlus;
    private String userCodDuca;
    private String userCodVPN;
    
    
    private String state;

    /**
     *
     *
     */
    public Person document(String document) {
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
     *
     */
    public Person mail(String mail) {
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
     *
     */
    public Person uid(String uid) {
        this.uid = uid;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     *
     *
     */
    public Person position(CatalogPosition position) {
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
     *
     */
    public Person levelOne(CatalogLevelOne levelOne) {
        this.levelOne = levelOne;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("levelOne")
    @Valid
    public CatalogLevelOne getLevelOne() {
        return levelOne;
    }

    public void setLevelOne(CatalogLevelOne levelOne) {
        this.levelOne = levelOne;
    }

    /**
     *
     *
     */
    public Person levelTwo(CatalogLevelTwo levelTwo) {
        this.levelTwo = levelTwo;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("levelTwo")
    @Valid
    public CatalogLevelTwo getLevelTwo() {
        return levelTwo;
    }

    public void setLevelTwo(CatalogLevelTwo levelTwo) {
        this.levelTwo = levelTwo;
    }

    /**
     *
     *
     */
    public Person levelThree(CatalogLevelThree levelThree) {
        this.levelThree = levelThree;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("levelThree")
    @Valid
    public CatalogLevelThree getLevelThree() {
        return levelThree;
    }

    public void setLevelThree(CatalogLevelThree levelThree) {
        this.levelThree = levelThree;
    }

    /**
     *
     *
     */
    public Person levelFour(CatalogLevelFour levelFour) {
        this.levelFour = levelFour;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("levelFour")
    @Valid
    public CatalogLevelFour getLevelFour() {
        return levelFour;
    }

    public void setLevelFour(CatalogLevelFour levelFour) {
        this.levelFour = levelFour;
    }

    /**
     *
     *
     */
    public Person startDate(String startDate) {
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
     *
     */
    public Person endDate(String endDate) {
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
     *
     */
    public Person attribute(CatalogAttribute attribute) {
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
     *
     */
    public Person typeAFPA(CatalogTypeAFPA typeAFPA) {
        this.typeAFPA = typeAFPA;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("typeAFPA")
    @Valid
    public CatalogTypeAFPA getTypeAFPA() {
        return typeAFPA;
    }

    public void setTypeAFPA(CatalogTypeAFPA typeAFPA) {
        this.typeAFPA = typeAFPA;
    }

    /**
     *
     *
     */
    public Person isAFPA(Boolean isAFPA) {
        this.isAFPA = isAFPA;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("isAFPA")
    public Boolean isIsAFPA() {
        return isAFPA;
    }

    public void setIsAFPA(Boolean isAFPA) {
        this.isAFPA = isAFPA;
    }

    /**
     *
     *
     */
    public Person resolution(String resolution) {
        this.resolution = resolution;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("resolution")
    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    /**
     *
     *
     */
    public Person codeDeclarant(String codeDeclarant) {
        this.codeDeclarant = codeDeclarant;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("codeDeclarant")
    public String getCodeDeclarant() {
        return codeDeclarant;
    }

    public void setCodeDeclarant(String codeDeclarant) {
        this.codeDeclarant = codeDeclarant;
    }

    /**
     *
     *
     */
    public Person id(String id) {
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
     *
     */
    public Person approveAFPA(String approveAFPA) {
        this.approveAFPA = approveAFPA;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("approveAFPA")
    public String getApproveAFPA() {
        return approveAFPA;
    }

    public void setApproveAFPA(String approveAFPA) {
        this.approveAFPA = approveAFPA;
    }

    /**
     *
     *
     */
    public Person fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("fullName")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     *
     *
     */
    public Person lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     *
     */
    public Person surName(String surName) {
        this.surName = surName;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("surName")
    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    /**
     * Iterno, externo
   *
     */
    public Person userType(String userType) {
        this.userType = userType;
        return this;
    }

    @ApiModelProperty(value = "Iterno, externo")
    @JsonProperty("userType")
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     *
     *
     */
    public Person organizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("OrganizationCode")
    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    /**
     *
     *
     */
    public Person phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     *
     */
    public Person alternativeMail(String alternativeMail) {
        this.alternativeMail = alternativeMail;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("alternativeMail")
    public String getAlternativeMail() {
        return alternativeMail;
    }

    public void setAlternativeMail(String alternativeMail) {
        this.alternativeMail = alternativeMail;
    }

    /**
     *
     *
     */
    public Person mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(document, person.document)
                && Objects.equals(mail, person.mail)
                && Objects.equals(uid, person.uid)
                && Objects.equals(position, person.position)
                && Objects.equals(levelOne, person.levelOne)
                && Objects.equals(levelTwo, person.levelTwo)
                && Objects.equals(levelThree, person.levelThree)
                && Objects.equals(levelFour, person.levelFour)
                && Objects.equals(startDate, person.startDate)
                && Objects.equals(endDate, person.endDate)
                && Objects.equals(attribute, person.attribute)
                && Objects.equals(typeAFPA, person.typeAFPA)
                && Objects.equals(isAFPA, person.isAFPA)
                && Objects.equals(resolution, person.resolution)
                && Objects.equals(codeDeclarant, person.codeDeclarant)
                && Objects.equals(id, person.id)
                && Objects.equals(approveAFPA, person.approveAFPA)
                && Objects.equals(fullName, person.fullName)
                && Objects.equals(lastName, person.lastName)
                && Objects.equals(surName, person.surName)
                && Objects.equals(userType, person.userType)
                && Objects.equals(organizationCode, person.organizationCode)
                && Objects.equals(phoneNumber, person.phoneNumber)
                && Objects.equals(alternativeMail, person.alternativeMail)
                && Objects.equals(mobile, person.mobile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(document, mail, uid, position, levelOne, levelTwo, levelThree, levelFour, startDate, endDate, attribute, typeAFPA, isAFPA, resolution, codeDeclarant, id, approveAFPA, fullName, lastName, surName, userType, organizationCode, phoneNumber, alternativeMail, mobile);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Person {\n");

        sb.append("    document: ").append(toIndentedString(document)).append("\n");
        sb.append("    mail: ").append(toIndentedString(mail)).append("\n");
        sb.append("    uid: ").append(toIndentedString(uid)).append("\n");
        sb.append("    position: ").append(toIndentedString(position)).append("\n");
        sb.append("    levelOne: ").append(toIndentedString(levelOne)).append("\n");
        sb.append("    levelTwo: ").append(toIndentedString(levelTwo)).append("\n");
        sb.append("    levelThree: ").append(toIndentedString(levelThree)).append("\n");
        sb.append("    levelFour: ").append(toIndentedString(levelFour)).append("\n");
        sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
        sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
        sb.append("    attribute: ").append(toIndentedString(attribute)).append("\n");
        sb.append("    typeAFPA: ").append(toIndentedString(typeAFPA)).append("\n");
        sb.append("    isAFPA: ").append(toIndentedString(isAFPA)).append("\n");
        sb.append("    resolution: ").append(toIndentedString(resolution)).append("\n");
        sb.append("    codeDeclarant: ").append(toIndentedString(codeDeclarant)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    approveAFPA: ").append(toIndentedString(approveAFPA)).append("\n");
        sb.append("    fullName: ").append(toIndentedString(fullName)).append("\n");
        sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
        sb.append("    surName: ").append(toIndentedString(surName)).append("\n");
        sb.append("    userType: ").append(toIndentedString(userType)).append("\n");
        sb.append("    organizationCode: ").append(toIndentedString(organizationCode)).append("\n");
        sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
        sb.append("    alternativeMail: ").append(toIndentedString(alternativeMail)).append("\n");
        sb.append("    mobile: ").append(toIndentedString(mobile)).append("\n");
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

    public String getUserSiduneaWorld() {
        return userSiduneaWorld;
    }

    public void setUserSiduneaWorld(String userSiduneaWorld) {
        this.userSiduneaWorld = userSiduneaWorld;
    }

    public String getUserSiduneaPlus() {
        return userSiduneaPlus;
    }

    public void setUserSiduneaPlus(String userSiduneaPlus) {
        this.userSiduneaPlus = userSiduneaPlus;
    }

    public String getUserCodDuca() {
        return userCodDuca;
    }

    public void setUserCodDuca(String userCodDuca) {
        this.userCodDuca = userCodDuca;
    }

    public String getUserCodVPN() {
        return userCodVPN;
    }

    public void setUserCodVPN(String userCodVPN) {
        this.userCodVPN = userCodVPN;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    
    
    
    
}
