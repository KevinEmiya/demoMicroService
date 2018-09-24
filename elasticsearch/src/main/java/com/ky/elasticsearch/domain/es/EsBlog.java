package com.ky.elasticsearch.domain.es;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Document(indexName = "blog", type = "blog")
public class EsBlog implements Serializable {

    @Id
    private String id;

    @Field(analyzer = "ik_smart", searchAnalyzer = "ik_smart", type = FieldType.Text, fielddata = true)
    private String title;

    @Field(analyzer = "ik_smart", searchAnalyzer = "ik_smart", type = FieldType.Text, fielddata = true)
    private String summary;

    @Field(analyzer = "ik_smart", searchAnalyzer = "ik_smart", type = FieldType.Text, fielddata = true)
    private String content;

    protected EsBlog(){
    }

    public EsBlog(String title, String summary, String content) {
        this.title = title;
        this.summary = summary;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "EsBlog{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
