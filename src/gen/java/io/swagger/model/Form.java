package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.model.Applicant;
import io.swagger.model.Request;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

/**
 * 
 **/

import io.swagger.annotations.*;
import java.util.Objects;

import javax.xml.bind.annotation.*;

@ApiModel(description = "")

public class Form   {
  
  private String id = null;
  private String createdOn = null;
  private String createdBy = null;
  private String modifiedOn = null;
  private String modifiedBy = null;
  private Boolean closed = null;
  private String step = null;
  private String comment = null;
  private List<Request> requests = new ArrayList<Request>();
  private Applicant applicant = null;
  private String applicantViewer = null;
  private String file1 = null;
  private String file2 = null;
  private String file3 = null;
  private String file4 = null;
  private String file5 = null;
  private String file6 = null;
  private String status = null;
  private String formType = null;
  private String createdName = null;
  private String roleStep = null;

  /**
   * DGAF-Sequence
   **/
  public Form id(String id) {
    this.id = id;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "DGAF-Sequence")
  @JsonProperty("id")
  @NotNull
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Start date 
   **/
  public Form createdOn(String createdOn) {
    this.createdOn = createdOn;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "Start date ")
  @JsonProperty("createdOn")
  @NotNull
  public String getCreatedOn() {
    return createdOn;
  }
  public void setCreatedOn(String createdOn) {
    this.createdOn = createdOn;
  }

  /**
   * created by 
   **/
  public Form createdBy(String createdBy) {
    this.createdBy = createdBy;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "created by ")
  @JsonProperty("createdBy")
  @NotNull
  public String getCreatedBy() {
    return createdBy;
  }
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  /**
   * Start date 
   **/
  public Form modifiedOn(String modifiedOn) {
    this.modifiedOn = modifiedOn;
    return this;
  }

  
  @ApiModelProperty(value = "Start date ")
  @JsonProperty("modifiedOn")
  public String getModifiedOn() {
    return modifiedOn;
  }
  public void setModifiedOn(String modifiedOn) {
    this.modifiedOn = modifiedOn;
  }

  /**
   * created by 
   **/
  public Form modifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
    return this;
  }

  
  @ApiModelProperty(value = "created by ")
  @JsonProperty("modifiedBy")
  public String getModifiedBy() {
    return modifiedBy;
  }
  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  /**
   * Closed &#x3D; 1 Open &#x3D; 0
   **/
  public Form closed(Boolean closed) {
    this.closed = closed;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "Closed = 1 Open = 0")
  @JsonProperty("closed")
  @NotNull
  public Boolean isClosed() {
    return closed;
  }
  public void setClosed(Boolean closed) {
    this.closed = closed;
  }

  /**
   * ID Step to follow
   **/
  public Form step(String step) {
    this.step = step;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "ID Step to follow")
  @JsonProperty("step")
  @NotNull
  public String getStep() {
    return step;
  }
  public void setStep(String step) {
    this.step = step;
  }

  /**
   * 
   **/
  public Form comment(String comment) {
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
  public Form requests(List<Request> requests) {
    this.requests = requests;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("requests")
  @Valid
  public List<Request> getRequests() {
    return requests;
  }
  public void setRequests(List<Request> requests) {
    this.requests = requests;
  }

  /**
   * 
   **/
  public Form applicant(Applicant applicant) {
    this.applicant = applicant;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("applicant")
  @Valid
  public Applicant getApplicant() {
    return applicant;
  }
  public void setApplicant(Applicant applicant) {
    this.applicant = applicant;
  }

  /**
   * Role viewer after finish
   **/
  public Form applicantViewer(String applicantViewer) {
    this.applicantViewer = applicantViewer;
    return this;
  }

  
  @ApiModelProperty(value = "Role viewer after finish")
  @JsonProperty("applicantViewer")
  public String getApplicantViewer() {
    return applicantViewer;
  }
  public void setApplicantViewer(String applicantViewer) {
    this.applicantViewer = applicantViewer;
  }

  /**
   * Role viewer after finish
   **/
  public Form file1(String file1) {
    this.file1 = file1;
    return this;
  }

  
  @ApiModelProperty(value = "Role viewer after finish")
  @JsonProperty("file1")
  public String getFile1() {
    return file1;
  }
  public void setFile1(String file1) {
    this.file1 = file1;
  }

  /**
   * Role viewer after finish
   **/
  public Form file2(String file2) {
    this.file2 = file2;
    return this;
  }

  
  @ApiModelProperty(value = "Role viewer after finish")
  @JsonProperty("file2")
  public String getFile2() {
    return file2;
  }
  public void setFile2(String file2) {
    this.file2 = file2;
  }

  /**
   * Role viewer after finish
   **/
  public Form file3(String file3) {
    this.file3 = file3;
    return this;
  }

  
  @ApiModelProperty(value = "Role viewer after finish")
  @JsonProperty("file3")
  public String getFile3() {
    return file3;
  }
  public void setFile3(String file3) {
    this.file3 = file3;
  }

  /**
   * Role viewer after finish
   **/
  public Form file4(String file4) {
    this.file4 = file4;
    return this;
  }

  
  @ApiModelProperty(value = "Role viewer after finish")
  @JsonProperty("file4")
  public String getFile4() {
    return file4;
  }
  public void setFile4(String file4) {
    this.file4 = file4;
  }

  /**
   * Role viewer after finish
   **/
  public Form file5(String file5) {
    this.file5 = file5;
    return this;
  }

  
  @ApiModelProperty(value = "Role viewer after finish")
  @JsonProperty("file5")
  public String getFile5() {
    return file5;
  }
  public void setFile5(String file5) {
    this.file5 = file5;
  }

  /**
   * Role viewer after finish
   **/
  public Form file6(String file6) {
    this.file6 = file6;
    return this;
  }

  
  @ApiModelProperty(value = "Role viewer after finish")
  @JsonProperty("file6")
  public String getFile6() {
    return file6;
  }
  public void setFile6(String file6) {
    this.file6 = file6;
  }

  /**
   * 
   **/
  public Form status(String status) {
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
  public Form formType(String formType) {
    this.formType = formType;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("formType")
  @NotNull
  public String getFormType() {
    return formType;
  }
  public void setFormType(String formType) {
    this.formType = formType;
  }

  /**
   * 
   **/
  public Form createdName(String createdName) {
    this.createdName = createdName;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("createdName")
  public String getCreatedName() {
    return createdName;
  }
  public void setCreatedName(String createdName) {
    this.createdName = createdName;
  }

  /**
   * 
   **/
  public Form roleStep(String roleStep) {
    this.roleStep = roleStep;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("roleStep")
  public String getRoleStep() {
    return roleStep;
  }
  public void setRoleStep(String roleStep) {
    this.roleStep = roleStep;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Form form = (Form) o;
    return Objects.equals(id, form.id) &&
        Objects.equals(createdOn, form.createdOn) &&
        Objects.equals(createdBy, form.createdBy) &&
        Objects.equals(modifiedOn, form.modifiedOn) &&
        Objects.equals(modifiedBy, form.modifiedBy) &&
        Objects.equals(closed, form.closed) &&
        Objects.equals(step, form.step) &&
        Objects.equals(comment, form.comment) &&
        Objects.equals(requests, form.requests) &&
        Objects.equals(applicant, form.applicant) &&
        Objects.equals(applicantViewer, form.applicantViewer) &&
        Objects.equals(file1, form.file1) &&
        Objects.equals(file2, form.file2) &&
        Objects.equals(file3, form.file3) &&
        Objects.equals(file4, form.file4) &&
        Objects.equals(file5, form.file5) &&
        Objects.equals(file6, form.file6) &&
        Objects.equals(status, form.status) &&
        Objects.equals(formType, form.formType) &&
        Objects.equals(createdName, form.createdName) &&
        Objects.equals(roleStep, form.roleStep);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdOn, createdBy, modifiedOn, modifiedBy, closed, step, comment, requests, applicant, applicantViewer, file1, file2, file3, file4, file5, file6, status, formType, createdName, roleStep);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Form {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    createdOn: ").append(toIndentedString(createdOn)).append("\n");
    sb.append("    createdBy: ").append(toIndentedString(createdBy)).append("\n");
    sb.append("    modifiedOn: ").append(toIndentedString(modifiedOn)).append("\n");
    sb.append("    modifiedBy: ").append(toIndentedString(modifiedBy)).append("\n");
    sb.append("    closed: ").append(toIndentedString(closed)).append("\n");
    sb.append("    step: ").append(toIndentedString(step)).append("\n");
    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
    sb.append("    requests: ").append(toIndentedString(requests)).append("\n");
    sb.append("    applicant: ").append(toIndentedString(applicant)).append("\n");
    sb.append("    applicantViewer: ").append(toIndentedString(applicantViewer)).append("\n");
    sb.append("    file1: ").append(toIndentedString(file1)).append("\n");
    sb.append("    file2: ").append(toIndentedString(file2)).append("\n");
    sb.append("    file3: ").append(toIndentedString(file3)).append("\n");
    sb.append("    file4: ").append(toIndentedString(file4)).append("\n");
    sb.append("    file5: ").append(toIndentedString(file5)).append("\n");
    sb.append("    file6: ").append(toIndentedString(file6)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    formType: ").append(toIndentedString(formType)).append("\n");
    sb.append("    createdName: ").append(toIndentedString(createdName)).append("\n");
    sb.append("    roleStep: ").append(toIndentedString(roleStep)).append("\n");
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

