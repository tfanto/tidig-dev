package se.consid.applications.tidig.api.dto;

import java.io.Serializable;
import java.util.Objects;

public class ArticleDto implements Serializable {
    Long articleId;
    String name;

    public ArticleDto() {
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleDto that = (ArticleDto) o;
        return Objects.equals(articleId, that.articleId) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, name);
    }
}
