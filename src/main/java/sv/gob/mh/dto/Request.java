/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mh.dto;

/**
 *
 * @author andres
 */
public class Request {
    private String subject;
    private String description;
    private Requester requester;
    private UdfFields udf_fields;
    private Template template;
    private Site site;
    private Group group;
    private ServiceCategory service_category;
    private Category category;
    private Subcategory subcategory;
    private Item item;
    private Status status;

    public Request() {
    }

    public Request(String subject, String description, Requester requester, UdfFields udf_fields, Template template, Site site, Group group, ServiceCategory service_category, Category category, Subcategory subcategory, Item item, Status status) {
        this.subject = subject;
        this.description = description;
        this.requester = requester;
        this.udf_fields = udf_fields;
        this.template = template;
        this.site = site;
        this.group = group;
        this.service_category = service_category;
        this.category = category;
        this.subcategory = subcategory;
        this.item = item;
        this.status = status;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Requester getRequester() {
        return requester;
    }

    public void setRequester(Requester requester) {
        this.requester = requester;
    }

    public UdfFields getUdf_fields() {
        return udf_fields;
    }

    public void setUdf_fields(UdfFields udf_fields) {
        this.udf_fields = udf_fields;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public ServiceCategory getService_category() {
        return service_category;
    }

    public void setService_category(ServiceCategory service_category) {
        this.service_category = service_category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Request{" + "subject=" + subject + ", description=" + description + ", requester=" + requester + ", udf_fields=" + udf_fields + ", template=" + template + ", site=" + site + ", group=" + group + ", service_category=" + service_category + ", category=" + category + ", subcategory=" + subcategory + ", item=" + item + ", status=" + status + '}';
    }

    
    
    
    
}
