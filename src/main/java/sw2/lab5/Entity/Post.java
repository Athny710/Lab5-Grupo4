package sw2.lab5.Entity;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;

@Entity
@Table(name = "post")
public class Post {
    @Id
    private int id_post;
    @Column(nullable = false)
    private String title;
    private String summary;
    @Column(nullable = false)
    private Boolean published;
    private String content;
    @ManyToOne
    @JoinColumn(name = "author_id")

    private Usuario usuario;


    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
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

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Usuario getAuthor() {
        return usuario;
    }

    public void setAuthor(Usuario author) {
        this.usuario = author;

    }
}
